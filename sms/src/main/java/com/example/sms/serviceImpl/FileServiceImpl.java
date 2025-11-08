package com.example.sms.serviceimpl;


import com.example.sms.entity.FileEntity;
import com.example.sms.repository.mongo.FileRepositoryMongo;
import com.example.sms.service.FileService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final GridFSBucket gridFSBucket;
    private final FileRepositoryMongo fileRepository;

    public FileServiceImpl(MongoDatabaseFactory dbFactory, FileRepositoryMongo fileRepository) {
        this.gridFSBucket = com.mongodb.client.gridfs.GridFSBuckets.create(dbFactory.getMongoDatabase());
        this.fileRepository = fileRepository;
    }

    // ✅ CREATE
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();

        GridFSUploadOptions options = new GridFSUploadOptions()
                .metadata(new Document("contentType", file.getContentType()));

        ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), is, options);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setId(fileId.toHexString());
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileSize(file.getSize());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setUploadDate(LocalDateTime.now().toString());

        fileRepository.save(fileEntity);

        return fileId.toHexString();
    }

    // ✅ VIEW
    @Override
    public byte[] viewFile(String id) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(new ObjectId(id), baos);
        return baos.toByteArray();
    }

    @Override
    public String getFileContentType(String id) {
        GridFSFile file = getFile(id);
        if (file != null && file.getMetadata() != null && file.getMetadata().getString("contentType") != null) {
            return file.getMetadata().getString("contentType");
        }
        return "application/octet-stream";
    }

    // ✅ READ
    @Override
    public GridFSFile getFile(String id) {
        return gridFSBucket.find(new Document("_id", new ObjectId(id))).first();
    }

    @Override
    public InputStream downloadFile(String id) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(new ObjectId(id), outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @Override
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    // ✅ UPDATE
    @Override
    public FileEntity updateFile(String id, MultipartFile newFile) throws Exception {
        deleteFile(id); // old file delete
        uploadFile(newFile); // new upload

        FileEntity updated = new FileEntity();
        updated.setId(id);
        updated.setFileName(newFile.getOriginalFilename());
        updated.setFileSize(newFile.getSize());
        updated.setContentType(newFile.getContentType());
        updated.setUploadDate(LocalDateTime.now().toString());
        return fileRepository.save(updated);
    }

    // ✅ DELETE
    @Override
    public String deleteFile(String id) throws Exception {
        gridFSBucket.delete(new ObjectId(id));
        fileRepository.deleteById(id);
        return "File deleted successfully with id: " + id;
    }
}
