package com.school.main.entity.utils;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotBlank
    private String road;
    @NotBlank
    private String district;
    @NotBlank
    private Integer number;
    @NotBlank
    private String complement;
}
