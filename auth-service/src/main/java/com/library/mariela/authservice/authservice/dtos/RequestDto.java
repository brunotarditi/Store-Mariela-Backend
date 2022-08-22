package com.library.mariela.authservice.authservice.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestDto {
    private String uri;
    private String method;
}
