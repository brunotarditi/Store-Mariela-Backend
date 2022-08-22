package com.library.mariela.authservice.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewUserDto {
    private String userName;
    private String password;
    private String role;
}
