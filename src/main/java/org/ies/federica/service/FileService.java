package org.ies.federica.service;

import org.ies.federica.dao.FileDAO;
import org.ies.federica.dao.FileDAOImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FileService {
    FileDAO fileDAO = new FileDAOImpl();

    public void fileSplit(String path) throws IOException {

        File fileResult = null;
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().contains(".csv")) {
                fileResult = new File(file.getPath());
            }
        }
        String replaceExtension = fileResult.getName().replace(".csv", ".txt");
        List<String> lines = fileDAO.getLinesInFiles(fileResult);

        for (int i = 1; i < lines.size(); i++) {
            String[] splitLine = lines.get(i).split(";");
            fileDAO.replaceCaracters(splitLine);
            double totalCost = Double.parseDouble(splitLine[4]) +
                    Double.parseDouble(splitLine[5]) +
                    Double.parseDouble(splitLine[6]);
            double benefit = Double.parseDouble(splitLine[3]) - totalCost;
            BigDecimal formatNumber = new BigDecimal(benefit);
            formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
            fileDAO.showInfoFile(splitLine, totalCost, formatNumber.doubleValue());
            fileDAO.writeInFile(replaceExtension, fileResult, splitLine, totalCost, formatNumber.doubleValue());
        }
        System.out.println("Fichero " + replaceExtension + " generado correctamente");
    }

    public void modifyFile(String path) throws IOException {

        File firstFile = null;
        File secondFile = null;
        File[] files = new File(path).listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().contains(".txt")) {
                secondFile = new File(path + file.getName());
            }
            if (file.isFile() && file.getName().contains(".csv")) {
                firstFile = new File(file.getName());
            }
        }
        String onvoiceName = firstFile.getName();
        String nameSecondFile = secondFile.getName();
        String pathSecondFile = secondFile.getPath();
        double sizeSecondFile = secondFile.length();

        List<String> lines = fileDAO.getLinesInFiles(secondFile);

        int articleAmount = 0;
        double totalBenefit = 0;
        for (int i = 0; i < lines.size(); i++) {
            String[] splitLine = lines.get(i).split(":");
            if (splitLine[0].equals("Articulo")) {

                articleAmount++;
            }
            if (splitLine[0].equals("Beneficio")) {
                totalBenefit += Double.parseDouble(splitLine[1]);


            }
            BigDecimal formatNumber = new BigDecimal(totalBenefit);
            formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
            fileDAO.writeInResultFile(onvoiceName, pathSecondFile, nameSecondFile, sizeSecondFile, articleAmount, formatNumber.doubleValue());
        }
        System.out.println("Fichero " + "result_" + nameSecondFile + " generado correctamente");
    }
}


