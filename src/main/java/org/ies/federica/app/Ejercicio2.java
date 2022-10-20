package org.ies.federica.app;

import org.ies.federica.service.FileService;

import java.io.IOException;

public class Ejercicio2 {
    private final static String PATH = "src/main/resources/";
    public static void main(String[] args) throws IOException {

        FileService fileService = new FileService();
        fileService.modifyFile(PATH);
    }
}
