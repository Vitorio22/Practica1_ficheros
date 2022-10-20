package org.ies.federica.dao;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileDAO {

    List<String> getLinesInFiles(File file) throws IOException;

    void replaceCaracters(String[] splitLine);

    void showInfoFile(String[] splitLine, double costeTotal, double beneficio);

    void writeInFile(String replaceExtension, File nombreFactura, String[] splitLine,
                     double totalCost, double benefit);

    void writeInResultFile(String onvoiceName, String pathSecondFile, String nameSecondFile,
                           double sizeSecondFile, int articleAmount, double totalBenefit);

    void createExcelInDisk(Workbook workbook, String nameExcelFile, String secondFilePath) throws IOException;
}


