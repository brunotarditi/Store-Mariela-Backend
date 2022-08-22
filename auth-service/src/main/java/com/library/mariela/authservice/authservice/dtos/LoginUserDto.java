package com.library.mariela.authservice.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginUserDto {
    private String userName;
    private String password;
}
