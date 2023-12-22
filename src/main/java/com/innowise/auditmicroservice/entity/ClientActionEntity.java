package com.innowise.auditmicroservice.entity;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "client_actions")
@Data
@NoArgsConstructor
public class ClientActionEntity {
    @Id
    private String id;
    @Email
    private String email;
    private String action;
    private String actionType;
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss.ms")
    private LocalDateTime date;

    public ClientActionEntity(String email, String action, String actionType, LocalDateTime date) {
        this.email = email;
        this.action = action;
        this.actionType = actionType;
        this.date = date;
    }
}
