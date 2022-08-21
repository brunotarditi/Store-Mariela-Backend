package com.library.categoryservice.dtos;

import com.library.commonsservice.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto extends BaseDto {
    @NotBlank
    private String name;
}
