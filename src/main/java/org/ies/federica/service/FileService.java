package org.ies.federica.service;

import org.ies.federica.dao.FileDAO;
import org.ies.federica.dao.FileDAOImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileService {

    FileDAO fileDAO = new FileDAOImpl();

    public void fileSplit(String path) throws IOException {

        File file = new File(path);
        String invoiceName = file.getName().replace(".csv", ".txt");

        List<String> lines = fileDAO.getLinesInFiles(file);

        for (int i = 1; i < lines.size(); i++) {
            String[] splitLine = lines.get(i).split(";");
            fileDAO.replaceCaracters(splitLine);
            double totalCost = Double.parseDouble(splitLine[4]) +
                    Double.parseDouble(splitLine[5]) +
                    Double.parseDouble(splitLine[6]);
            double benefit = Double.parseDouble(splitLine[3]) - totalCost;
            fileDAO.showInfoFile(splitLine, totalCost, benefit);
            fileDAO.writeInFile(invoiceName, splitLine, totalCost, benefit);
        }
    }

    public void modifyFile(String path) throws IOException {

        File fileResult = new File(path);
        String invoiceName = fileResult.getName();

        List<String> lines = fileDAO.getLinesInFiles(fileResult);

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
            fileDAO.writeInResultFile(invoiceName, articleAmount, totalBenefit, splitLine);
        }

    }
}


