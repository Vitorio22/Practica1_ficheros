package org.ies.federica.app;

import org.ies.federica.service.ExcelService;

public class Ejercicio3 {

    private static final String PATH = "src/main/resources/ShopInfo.xlsx";

    private static final String PATH_EXERCISE_TWO = "src/main/resources/result_invoice_202009.txt";

    public static void main(String[] args) {
        ExcelService excelService = new ExcelService();
        excelService.createExcelFile(PATH, PATH_EXERCISE_TWO);

    }
}
