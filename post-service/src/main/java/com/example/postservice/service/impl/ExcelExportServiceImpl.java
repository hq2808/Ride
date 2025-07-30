package com.example.postservice.service.impl;

import com.example.postservice.entity.Post;
import com.example.postservice.repository.PostRepository;
import com.example.postservice.service.ExcelExportService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelExportServiceImpl implements ExcelExportService {

    private final PostRepository postRepository;

    private static final int PAGE_SIZE = 100_000;
    private static final int MAX_ROWS_PER_SHEET = 1_000_000;
    private static final int EXCEL_CELL_LIMIT = 32_767;

    @Override
    public void exportToExcel(Path outputDir) throws IOException {
        long startTime = System.nanoTime();
        int totalRows = (int) postRepository.count();
        int totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);
        int fileIndex = 1;
        int currentRow = 0;

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Posts");
        writeHeader(sheet.createRow(currentRow++));

        for (int page = 0; page < totalPages; page++) {
            Pageable pageable = PageRequest.of(page, PAGE_SIZE);
            List<Post> posts = postRepository.findAllByOrderById(pageable);
            for (Post post : posts) {
                if (currentRow >= MAX_ROWS_PER_SHEET) {
                    writeToFile(workbook, outputDir, fileIndex++);
                    workbook.dispose();
                    workbook = new SXSSFWorkbook();
                    sheet = workbook.createSheet("Posts");
                    currentRow = 0;
                    writeHeader(sheet.createRow(currentRow++));
                }

                Row row = sheet.createRow(currentRow++);
                writePostRow(row, post);
            }
        }

        writeToFile(workbook, outputDir, fileIndex);
        workbook.dispose();

        long endTime = System.nanoTime(); // 👈 Kết thúc
        double seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("✅ Hoàn tất xuất Excel trong %.2f giây.%n", seconds);
    }

    @Override
    public void exportToSheetExcel(Path outputDir) throws IOException {
        long startTime = System.currentTimeMillis();

        int totalRows = (int) postRepository.count();
        int totalPages = (int) Math.ceil((double) totalRows / PAGE_SIZE);

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        int currentRow = 0;
        int sheetIndex = 1;

        Sheet sheet = workbook.createSheet("Posts_" + sheetIndex);
        writeHeader(sheet.createRow(currentRow++));

        for (int page = 0; page < totalPages; page++) {
            List<Post> posts = postRepository.findAllByOrderById(PageRequest.of(page, PAGE_SIZE));
            for (Post post : posts) {
                if (currentRow >= MAX_ROWS_PER_SHEET) {
                    sheetIndex++;
                    sheet = workbook.createSheet("Posts_" + sheetIndex);
                    currentRow = 0;
                    writeHeader(sheet.createRow(currentRow++));
                }

                Row row = sheet.createRow(currentRow++);
                writePostRow(row, post);
            }
        }

        Path filePath = outputDir.resolve("posts.xlsx");
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            workbook.write(fos);
        }
        workbook.dispose();

        long endTime = System.currentTimeMillis();
        double seconds = (endTime - startTime) / 1000.0;
        System.out.printf("✅ Xuất thành công: %s (%.2f giây)%n", filePath.toAbsolutePath(), seconds);
    }

    private void writeToFile(SXSSFWorkbook workbook, Path outputDir, int index) throws IOException {
        String filename = "posts_part_" + index + ".xlsx";
        try (FileOutputStream fos = new FileOutputStream(outputDir.resolve(filename).toFile())) {
            workbook.write(fos);
        }
        System.out.println("✅ Ghi file: " + filename);
    }

    private void writeHeader(Row row) {
        String[] headers = {
                "Id", "AcceptedAnswerId", "AnswerCount", "Body", "ClosedDate", "CommentCount", "CommunityOwnedDate",
                "CreationDate", "FavoriteCount", "LastActivityDate", "LastEditDate", "LastEditorDisplayName",
                "LastEditorUserId", "OwnerUserId", "ParentId", "PostTypeId", "Score", "Tags", "Title", "ViewCount"
        };
        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
    }

    private void writePostRow(Row row, Post post) {
        int i = 0;
        row.createCell(i++).setCellValue(post.getId());
        row.createCell(i++).setCellValue(safe(post.getAcceptedAnswerId()));
        row.createCell(i++).setCellValue(safe(post.getAnswerCount()));
        row.createCell(i++).setCellValue(truncateForExcel(post.getBody()));
        row.createCell(i++).setCellValue(safe(post.getClosedDate()));
        row.createCell(i++).setCellValue(safe(post.getCommentCount()));
        row.createCell(i++).setCellValue(safe(post.getCommunityOwnedDate()));
        row.createCell(i++).setCellValue(safe(post.getCreationDate()));
        row.createCell(i++).setCellValue(safe(post.getFavoriteCount()));
        row.createCell(i++).setCellValue(safe(post.getLastActivityDate()));
        row.createCell(i++).setCellValue(safe(post.getLastEditDate()));
        row.createCell(i++).setCellValue(safe(post.getLastEditorDisplayName()));
        row.createCell(i++).setCellValue(safe(post.getLastEditorUserId()));
        row.createCell(i++).setCellValue(safe(post.getOwnerUserId()));
        row.createCell(i++).setCellValue(safe(post.getParentId()));
        row.createCell(i++).setCellValue(safe(post.getPostTypeId()));
        row.createCell(i++).setCellValue(safe(post.getScore()));
        row.createCell(i++).setCellValue(safe(post.getTags()));
        row.createCell(i++).setCellValue(safe(post.getTitle()));
        row.createCell(i++).setCellValue(safe(post.getViewCount()));
    }

    private String safe(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    private String truncateForExcel(String text) {
        if (text == null) return "";
        return text.length() > EXCEL_CELL_LIMIT ? text.substring(0, EXCEL_CELL_LIMIT - 3) + "..." : text;
    }
}
