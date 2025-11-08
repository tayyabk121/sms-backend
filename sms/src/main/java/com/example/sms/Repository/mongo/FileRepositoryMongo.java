package com.example.sms.repository.mongo;

import com.example.sms.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepositoryMongo extends MongoRepository<FileEntity, String> {
}
