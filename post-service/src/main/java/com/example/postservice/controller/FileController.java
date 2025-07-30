package com.example.postservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        Path uploadPath = Path.of("uploads", file.getOriginalFilename());
        Files.createDirectories(uploadPath.getParent());

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, uploadPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return ResponseEntity.ok("Upload thành công!");
    }

    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        Path file = Path.of("uploads", filename);
        if (!Files.exists(file)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        Files.copy(file, response.getOutputStream());
        response.flushBuffer();
    }

}
