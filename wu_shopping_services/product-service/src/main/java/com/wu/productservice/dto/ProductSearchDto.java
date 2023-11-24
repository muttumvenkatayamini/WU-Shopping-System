package com.wu.productservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Data
@Getter
@Setter
@ToString
public class ProductSearchDto {

    private String name;
    private String category;
    private BigDecimal price;
    private int pageNumber;
    private int pageSize;
    private String orderBy;
    private String order;
}
