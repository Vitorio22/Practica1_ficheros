package org.ies.federica.dao;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl implements FileDAO {

    @Override
    public List<String> getLinesInFiles(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (Reader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                lines.add(row);
            }
        }
        return lines;
    }

    @Override
    public void replaceCaracters(String[] splitLine) {
        splitLine[3] = splitLine[3].replace(",", ".");
        splitLine[4] = splitLine[4].replace(",", ".");
        splitLine[5] = splitLine[5].replace(",", ".");
        splitLine[6] = splitLine[6].replace(",", ".");
    }

    @Override
    public void showInfoFile(String[] splitLine, double totalCost, double benefit) {

        System.out.println("Articulo: " + splitLine[0]);
        System.out.println("Tipo: " + splitLine[1]);
        System.out.println("Precio de venta: " + splitLine[3]);
        System.out.println("Coste: " + totalCost);
        System.out.println("Beneficio: " + benefit);
        System.out.println("--------------------------------------------------" + '\n');
    }

    @Override
    public void writeInFile(String replaceExtension, File fileResult, String[] splitLine, double totalCost, double benefit) {

        File saveFile = new File("src/main/resources/" + replaceExtension);
        try (Writer writer = new FileWriter(saveFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(
                    "Articulo: " + splitLine[0] + '\n' +
                            "Tipo: " + splitLine[1] + '\n' +
                            "Precio de venta: " + splitLine[3] + '\n' +
                            "Coste: " + totalCost + '\n' +
                            "Beneficio: " + benefit + '\n' +
                            "--------------------------------------------------" + '\n');
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void writeInResultFile(String onvoiceName, String pathSecondFile, String nameSecondFile, double sizeSecondFile,
                                  int articleAmount, double totalBenefit) {

        File finalFile = new File("src/main/resources/result_" + nameSecondFile);

        try (Writer writer = new FileWriter(finalFile);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(
                    "Factura: " + onvoiceName + '\n' +
                            "Numero de articulos: " + articleAmount + '\n' +
                            "Beneficio total: " + totalBenefit + '\n' +
                            "Ruta del fichero: " + pathSecondFile + '\n' +
                            "Nombre del fichero: " + nameSecondFile + '\n' +
                            "Tamaño del fichero: " + sizeSecondFile + " bytes" + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createExcelInDisk(Workbook workbook, String nameExcelFile, String pathSecondFile) throws IOException {

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            workbook.write(byteArrayOutputStream);
            workbook.write(new FileOutputStream("src/main/resources/" + nameExcelFile));
            System.out.println("Excel " + nameExcelFile + " creado correctamente");

            double excelSize = byteArrayOutputStream.size();

            File file = new File(pathSecondFile);
            List<String> lines = new ArrayList<>();
            try (Reader reader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                String row;
                while ((row = bufferedReader.readLine()) != null) {
                    lines.add(row);
                }
            }
            String excelPath = "src/main/resources/" + nameExcelFile;
            lines.add("Ruta del fichero excel: " + excelPath);
            lines.add("Tamaño del fichero excel: " + excelSize + " bytes");
            try (Writer writer = new FileWriter(file);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                for (String line : lines) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Fichero " + pathSecondFile + " modificado correctamente");
        }
    }
}

