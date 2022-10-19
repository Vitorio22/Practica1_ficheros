package org.ies.federica.app;

import org.ies.federica.service.FileService;

import java.io.IOException;

public class Ejercicio1 {
    private final static String PATH = "src/main/resources/invoice_202009.csv";
    public static void main(String[] args) throws IOException {

        FileService fileService = new FileService();
        fileService.fileSplit(PATH);

    }
}
