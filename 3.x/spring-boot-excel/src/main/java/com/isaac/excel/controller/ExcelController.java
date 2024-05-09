package com.isaac.excel.controller;

import com.isaac.excel.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExcelController {
    private final ExcelService excelService;

    @RequestMapping({"/", "/index"})
    public String pageIndex() {
        log.info("pageIndex");
        return "/index.html";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String pageUploadCompletion(@RequestParam("excel") MultipartFile file) {
        log.info("pageUploadCompletion");

        Optional<MultipartFile> multipartFile = Optional.of(file);
        if (multipartFile.isPresent()) {
            excelService.readExcel(multipartFile.get());
        }
        return "/uploadCompletion.html";
    }

    @RequestMapping(value = "/upload-ajax", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, String>> uploadAjaxExcel(
            @RequestPart(value = "excel",required = false) MultipartFile excel
    ) {
        log.info("uploadAjaxExcel");

        Map<String, String> map = new HashMap();
        if (excel == null) {
            map.put("result", "failure");
            map.put("message", "No file uploaded");
            return ResponseEntity.badRequest().body(map);
        }

        boolean result = excelService.readExcel(excel);
        map.put("result", result ? "success" : "failure");
        return ResponseEntity.ok().body(map);
    }
}
