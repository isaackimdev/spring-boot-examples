package com.isaac.excel.service;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelService {

    public boolean readExcel(MultipartFile excel) {
        String fileName = excel.getName();
        long size = excel.getSize();

        boolean result = false;

        System.out.println("fileName : " + fileName);
        System.out.println("size : " + size);

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);

            for(int i = 0; i<sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);

                DataFormatter formatter = new DataFormatter();

                String name = formatter.formatCellValue(row.getCell(0));
                String age = formatter.formatCellValue(row.getCell(1));
                String phoneNumber = formatter.formatCellValue(row.getCell(2));

                System.out.printf(i+"row : [ %s, %s, %s ]\n", name, age, phoneNumber);
            }

            result = true;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return result;
        }
    }
}
