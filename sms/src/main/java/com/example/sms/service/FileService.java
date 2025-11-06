package com.example.sms.service;

import com.example.sms.entity.FileEntity;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileService {

    // Create
    String uploadFile(MultipartFile file) throws Exception;

    // Read
    GridFSFile getFile(String id);
    InputStream downloadFile(String id) throws Exception;
    List<FileEntity> getAllFiles();

    // View
    byte[] viewFile(String id) throws Exception;
    String getFileContentType(String id);

    // Update
    FileEntity updateFile(String id, MultipartFile newFile) throws Exception;

    // Delete
    String deleteFile(String id) throws Exception;
}
