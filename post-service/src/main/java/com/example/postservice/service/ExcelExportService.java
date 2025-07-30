package com.example.postservice.service;

import java.io.IOException;
import java.nio.file.Path;

public interface ExcelExportService {
    void exportToExcel(Path outputDir) throws IOException;
    void exportToSheetExcel(Path outputDir) throws IOException;
}
