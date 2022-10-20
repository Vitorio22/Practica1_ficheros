package org.ies.federica.app;

import org.ies.federica.service.ExcelService;

import java.io.IOException;

public class Ejercicio3 {
    private static final String PATH = "src/main/resources/";

    public static void main(String[] args) throws IOException {

        ExcelService excelService = new ExcelService();
        excelService.createExcelFile(PATH);
    }
}
