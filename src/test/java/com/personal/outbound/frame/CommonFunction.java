package com.personal.outbound.frame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CommonFunction {

    public List<String> getValueHeaderFileImport(String pathFile) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> valueHeader = new ArrayList<>();
        File file = new File(pathFile);
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            valueHeader.add(br.readLine());
        } catch (Exception e) {
        }
        return valueHeader;
    }

    public List<String> getValueColumnOfFile(String pathFile, String nameColumn) {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<String> columnData = new ArrayList<>();
        File file = new File(pathFile);
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            List<String> headerData = List.of(br.readLine().split(","));
            int index = headerData.indexOf(nameColumn);
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                columnData.add(data[index]);
            }
        } catch (Exception e) {
        }
        return columnData;
    }
}
