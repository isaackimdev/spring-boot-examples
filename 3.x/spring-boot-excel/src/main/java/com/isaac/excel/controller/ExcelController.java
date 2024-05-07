package com.isaac.excel.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@Slf4j
public class ExcelController {

    @RequestMapping({"/", "/index"})
    public String pageIndex() {
        log.info("pageIndex");
        return "/index.html";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String pageUploadCompletion(@RequestParam("excel") MultipartFile file) {
        log.info("pageUploadCompletion");
        Optional<MultipartFile> multipartFile = Optional.of(file);
        // multipartFile.orElseThrow((() -> new RuntimeException("파일 없음")));
        MultipartFile excel = multipartFile.get();
        String fileName = excel.getName();
        long size = excel.getSize();
        log.info("fileName : " + fileName);
        log.info("size : " + size);

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "/uploadCompletion.html";
    }
}
