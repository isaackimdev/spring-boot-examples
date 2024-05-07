package com.isaac.excel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
        String name = excel.getName();
        log.info("name : " + name);
        return "/uploadCompletion.html";
    }
}
