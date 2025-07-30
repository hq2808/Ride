package com.example.postservice.controller;

import com.example.postservice.service.ExcelExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {

    private final ExcelExportService excelExportService;

    @GetMapping("/posts")
    public ResponseEntity<String> exportPosts() {
        new Thread(() -> {
            try {
                Path exportPath = Path.of("exports");
                Files.createDirectories(exportPath);
                excelExportService.exportToExcel(exportPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok("⏳ Export đang được xử lý nền. Vui lòng kiểm tra thư mục /exports sau vài phút.");
    }

    @GetMapping("/posts-sheet")
    public ResponseEntity<String> exportPostsSheet() {
        new Thread(() -> {
            try {
                Path exportPath = Path.of("exports");
                Files.createDirectories(exportPath);
                excelExportService.exportToSheetExcel(exportPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return ResponseEntity.ok("⏳ Export đang được xử lý nền. Vui lòng kiểm tra thư mục /exports sau vài phút.");
    }
}
