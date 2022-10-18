package org.ies.federica.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ies.federica.dao.FileDAO;
import org.ies.federica.dao.FileDAOImpl;
import org.ies.federica.entity.InfoShopEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    FileDAO fileDAO = new FileDAOImpl();

    public void createExcelFile(String path, String pathExerciseTwo) {

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

    private List<InfoShopEntity> loadInfo() {
        List<InfoShopEntity> infoShopEntityList = new ArrayList<>();
        infoShopEntityList.add(new InfoShopEntity("Fiesta Glam", "Vestido", "29/08/2020",
                70, 2.1, 14.7, 31.3, 21.9));
        infoShopEntityList.add(new InfoShopEntity("Fiesta Glam", "Vestido", "30/08/2020",
                70, 2.1, 14.7, 31.3, 21.9));
        infoShopEntityList.add(new InfoShopEntity("Snoopy", "Camiseta", "29/08/2020",
                8, 1.9, 1.68, 2.32, 2.09));
        infoShopEntityList.add(new InfoShopEntity("Friends", "Camiseta", "30/08/2020",
                8, 1.9, 1.68, 2.32, 2.09));
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

        backgroundStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
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
        }
    }
}
