package com.example.serverless.repo;

import com.example.serverless.entity.EmailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<EmailEntity, String> {

    EmailEntity deleteByEmail(String email);
}
