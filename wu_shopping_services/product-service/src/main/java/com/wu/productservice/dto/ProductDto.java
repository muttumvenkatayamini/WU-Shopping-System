package com.wu.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    @NotBlank(message = "The product name is required.")
    @Size(min = 3, max = 20, message = "The product name must be from 3 to 20 characters.")
    private String name;
    @NotBlank(message = "The product description is required.")
    @Size(min = 3, max = 50, message = "The product description must be from 3 to 50 characters.")
    private String description;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal price;
    @NotBlank(message = "The product category is required.")
    @Size(min = 3, max = 15, message = "The product category must be from 3 to 15 characters.")
    private String category;
    @Min(value = 0, message = "quantity should not be less than 0")
    @Max(value = 150, message = "quantity should not be greater than 150")
    private Integer stockQuantity;
}
