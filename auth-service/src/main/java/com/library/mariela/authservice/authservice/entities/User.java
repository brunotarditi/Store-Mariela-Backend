package com.library.mariela.authservice.authservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "Users")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String role;
}
