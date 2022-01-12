package com.example.handlecare.dto;

import com.example.handlecare.entity.enums.Progression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Integer id;

    @NotNull
    @NotEmpty(message = "Напишите товары")
    private String item;
    @NotNull
    @NotEmpty(message = "Укажите торговую точку")
    private String market;
    private Float amount;
    private Progression progression;
}
