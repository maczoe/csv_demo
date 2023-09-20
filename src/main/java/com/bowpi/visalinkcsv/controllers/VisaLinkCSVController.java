package com.bowpi.visalinkcsv.controllers;

import com.bowpi.visalinkcsv.dto.ResponseDTO;
import com.bowpi.visalinkcsv.services.CSVService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/csv")
public class VisaLinkCSVController {
    final CSVService csvService;

    public VisaLinkCSVController(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/upload")
    public ResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        if (csvService.hasCSVFormat(file)) {
            try {
                return csvService.process(file);
            } catch (Exception e) {
                return ResponseDTO.builder().message(e.getMessage()).success(false).build();
            }
        }
        return ResponseDTO.builder().message("Invalid format").success(false).build();
    }

}
