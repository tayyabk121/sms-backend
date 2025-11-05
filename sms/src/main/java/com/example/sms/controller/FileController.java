package com.example.sms.controller;

import com.example.sms.entity.FileEntity;
import com.example.sms.service.FileService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    // ✅ Upload (Create)
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        String id = service.uploadFile(file);
        return ResponseEntity.ok("File uploaded successfully. ID: " + id);
    }

    // ✅ Download (Read)
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id) throws Exception {
        GridFSFile file = service.getFile(id);
        InputStream stream = service.downloadFile(id);

        String contentType = file.getMetadata() != null ?
                file.getMetadata().getString("contentType") : "application/octet-stream";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(stream.readAllBytes());
    }

    // ✅ Get All Files
    @GetMapping
    public ResponseEntity<List<FileEntity>> getAllFiles() {
        return ResponseEntity.ok(service.getAllFiles());
    }

    // ✅ Update
    @PutMapping("/update/{id}")
    public ResponseEntity<FileEntity> update(@PathVariable String id, @RequestParam("file") MultipartFile newFile) throws Exception {
        FileEntity updated = service.updateFile(id, newFile);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
        String message = service.deleteFile(id);
        return ResponseEntity.ok(message);
    }

    // ✅ View File (Browser view: image/pdf inline, others download)
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable String id) throws Exception {
        GridFSFile file = service.getFile(id);
        byte[] data = service.viewFile(id);
        String contentType = service.getFileContentType(id);

        boolean inlineSupported = contentType.startsWith("image/") || contentType.equals("application/pdf");

        String disposition = inlineSupported
                ? "inline; filename=\"" + file.getFilename() + "\""
                : "attachment; filename=\"" + file.getFilename() + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition)
                .body(data);
    }
}

