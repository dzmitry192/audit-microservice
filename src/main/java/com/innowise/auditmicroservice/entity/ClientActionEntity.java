package com.innowise.auditmicroservice.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collation = "client_actions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientActionEntity {
    @Id
    private String id;
    @Email
    private String email;
    @NotBlank
    private String action;
    @NotBlank
    private String actionType;
    @NotNull
    private Date date;
}
