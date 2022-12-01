package com.example.serverless.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "emails")
public class EmailEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    public EmailEntity(String email) {
        this.email = email;
    }
}
