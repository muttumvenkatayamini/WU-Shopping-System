package com.wu.productservice.controller;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.productservice.dto.APIResponse;
import com.wu.productservice.dto.ProductDto;
import com.wu.productservice.dto.ProductSearchDto;
import com.wu.productservice.entity.Product;
import com.wu.productservice.exception.BadRequestException;
import com.wu.productservice.exception.ResourceNotFoundException;
import com.wu.productservice.service.ProductService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

//    @Autowired
//    private UserService userService;

//    @GetMapping(value = "/products")
    @GetMapping(value = "/get")
    public APIResponse<List<Product>> getProduct() {
        APIResponse<List<Product>> response = null;
        List<Product> productList = Collections.EMPTY_LIST;
        try {
            productList = productService.getProducts();
            response = APIResponse.createSuccessResponse();
            response.setObjectList(productList);
        }
        catch (Exception e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(500);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during fetching product details", e);
        }
       return response;
    }

//    @PostMapping(value = "/products/search")
    @PostMapping(value = "/search")
    public APIResponse<List<Product>> productSearch(@Valid @RequestBody ProductSearchDto reqParams) {
        APIResponse<List<Product>> response = null;
        List<Product> productList = Collections.EMPTY_LIST;
        try {
            productList = productService.getProductsBySearchParams(reqParams);
            response = APIResponse.createSuccessResponse();
            response.setObjectList(productList);
        }
        catch (BadRequestException e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(400);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during searching products", e);
        }
        catch (ResourceNotFoundException e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(404);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during searching products", e);
        }
        catch (Exception e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(500);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during searching products", e);
        }
        return response;
    }

//    @PostMapping("/products")
    @PostMapping("/add")
    public APIResponse<Product> saveProduct(@Valid @RequestBody ProductDto product, HttpServletRequest httpServletRequest) {
       APIResponse<Product> response = null;
       Product p = null;
        try {
            p = productService.saveProduct(product);
            response = APIResponse.createSuccessResponse();
            response.setObjectList(p);
        } catch (BadRequestException e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(400);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during save product details", e);
        } catch (Exception e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(500);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during searching products", e);
        }
        return response;
    }

//    @PutMapping("/products/{product_id}")
    @PutMapping("/update/{product_id}")
    public APIResponse<Product> editProduct(@PathVariable Integer product_id, @Valid @RequestBody ProductDto product, HttpServletRequest httpServletRequest) {
     //   Integer userId = userService.getUserId(httpServletRequest);
        APIResponse<Product> response = null;
        Product productN = null;
        try {
            productN = productService.editProduct(product_id, product);
            response = APIResponse.createSuccessResponse();
            response.setObjectList(productN);
        } catch (ResourceNotFoundException e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(404);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during update product details", e);
        } catch (Exception e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(500);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during searching products", e);
        }
        return response;
    }

//    @DeleteMapping("/products/{product_id}")
    @DeleteMapping("/delete/{product_id}")
    public APIResponse editProduct(@PathVariable Integer product_id, HttpServletRequest httpServletRequest) {
        APIResponse response = null;
        try {
            productService.deleteProduct(product_id);
            response = APIResponse.createSuccessResponse();
            response.getResponseMessage().setResponseDescription("Product delete successfully");
        } catch (ResourceNotFoundException e) {
            response = APIResponse.createFailureResponse();
            response.getResponseMessage().setResponseCode(404);
            response.getResponseMessage().setResponseDescription(e.getLocalizedMessage());
            log.error("Error occurred during delete product details", e);
        }
        return response;
    }

}
