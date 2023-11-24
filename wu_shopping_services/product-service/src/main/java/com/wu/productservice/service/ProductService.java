package com.wu.productservice.service;



import java.util.List;

import com.wu.productservice.dto.ProductDto;
import com.wu.productservice.dto.ProductSearchDto;
import com.wu.productservice.entity.Product;
import com.wu.productservice.exception.BadRequestException;
import com.wu.productservice.exception.ResourceNotFoundException;

public interface ProductService {

    Product saveProduct(ProductDto product) throws BadRequestException;

    List<Product> getProducts();

    List<Product> getProductsBySearchParams(ProductSearchDto productName) throws ResourceNotFoundException, BadRequestException;

    Product editProduct(Integer product_id, ProductDto product) throws ResourceNotFoundException;

    void deleteProduct(Integer productId) throws ResourceNotFoundException;
}
