package org.ies.federica.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ies.federica.dao.FileDAO;
import org.ies.federica.dao.FileDAOImpl;
import org.ies.federica.entity.InfoShopEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    FileDAO fileDAO = new FileDAOImpl();

    public void createExcelFile(String path, String pathExerciseTwo) throws IOException {


        List<InfoShopEntity> infoShopEntityList = loadInfo();

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet();
            CellStyle backgroundStyle = workbook.createCellStyle();
            CellStyle fontStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            createHeader(sheet, fontStyle, font);
            createRows(infoShopEntityList, sheet, backgroundStyle, fontStyle, font);

            fileDAO.createExcelInDisk(workbook, path, pathExerciseTwo);

        } catch (IOException e) {
            System.err.println("Error en la creacion del workbook: " + e.getMessage());
        }
    }

    private List<InfoShopEntity> loadInfo() throws IOException {
        File fileInvoice = new File("src/main/resources/invoice_202009.csv");
        List<String> lines = fileDAO.getLinesInFiles(fileInvoice);
        List<InfoShopEntity> infoShopEntityList = new ArrayList<>();
        double totalCost;
        double benefit;
        for (int i = 1; i < lines.size(); i++) {
            String[] splitLine = lines.get(i).split(";");
            fileDAO.replaceCaracters(splitLine);
            totalCost = Double.parseDouble(splitLine[4]) + Double.parseDouble(splitLine[5]) +
                    Double.parseDouble(splitLine[6]);
            benefit = Double.parseDouble(splitLine[3]) - totalCost;

            infoShopEntityList.add(new InfoShopEntity(splitLine[0], splitLine[1], splitLine[2],
                    Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]),
                    Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), benefit));
        }
        return infoShopEntityList;
    }

    private void createHeader(Sheet sheet, CellStyle cellStyle, Font font) {

        font.setFontName("Courier New");
        font.setBold(true);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        RichTextString text = new XSSFRichTextString("Articulo");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        text = new XSSFRichTextString("Tipo");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        text = new XSSFRichTextString("Fecha de venta");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        text = new XSSFRichTextString("Precio venta");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        text = new XSSFRichTextString("Costes derivados");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        text = new XSSFRichTextString("Costes producción");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(6);
        text = new XSSFRichTextString("Impuestos");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(7);
        text = new XSSFRichTextString("Beneficio");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    private void createRows(List<InfoShopEntity> infoShopEntityList, Sheet sheet, CellStyle backgroundStyle, CellStyle fontStyle, Font font) {


        font.setFontName("Arial");
        font.setColor(IndexedColors.BLACK.getIndex());
        fontStyle.setFont(font);

        for (int i = 0; i < infoShopEntityList.size(); i++) {
            InfoShopEntity infoShopEntity = infoShopEntityList.get(i);

            Row row = sheet.createRow(i + 1);

            Cell cell = row.createCell(0);
            RichTextString text = new XSSFRichTextString(infoShopEntity.getArticle());
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(1);
            text = new XSSFRichTextString(infoShopEntity.getType());
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(2);
            text = new XSSFRichTextString(infoShopEntity.getSaleDate());
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(3);
            text = new XSSFRichTextString(String.valueOf(infoShopEntity.getSalePrice()));
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(4);
            text = new XSSFRichTextString(String.valueOf(infoShopEntity.getDerivedCosts()));
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(5);
            text = new XSSFRichTextString(String.valueOf(infoShopEntity.getProductionCosts()));
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(6);
            text = new XSSFRichTextString(String.valueOf(infoShopEntity.getTaxes()));
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            cell = row.createCell(7);
            text = new XSSFRichTextString(String.valueOf(infoShopEntity.getBenefit()));
            cell.setCellValue(text);
            cell.setCellStyle(fontStyle);
            cell.setCellStyle(backgroundStyle);

            ConditionalFormattingRule rule1 = sheet.getSheetConditionalFormatting().createConditionalFormattingRule("MOD(ROW() - 1, 2) = 1");
            PatternFormatting patternFmt = rule1.createPatternFormatting();

            patternFmt.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
            patternFmt.setFillPattern(PatternFormatting.SOLID_FOREGROUND);

            CellRangeAddress[] regions = {
                    CellRangeAddress.valueOf("A1:H" + infoShopEntityList.size())
            };

            sheet.getSheetConditionalFormatting().addConditionalFormatting(regions, rule1);
        }
    }
}
