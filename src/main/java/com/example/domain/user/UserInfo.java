package com.example.domain.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInfo {

    @NotNull
    private Long id;
    @NotBlank
    private String name;

    @Min(1)
    @Max(150)
    private int age;
}