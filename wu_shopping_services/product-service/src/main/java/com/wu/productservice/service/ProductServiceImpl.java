package com.wu.productservice.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wu.productservice.dto.ProductDto;
import com.wu.productservice.dto.ProductSearchDto;
import com.wu.productservice.entity.Product;
import com.wu.productservice.exception.BadRequestException;
import com.wu.productservice.exception.ResourceNotFoundException;
import com.wu.productservice.repository.ProductRepository;
import com.wu.productservice.repository.UsersRepository;

import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

   @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Autowired
//    private UserService userService;

    public static final String PERCENTAGE = "%";

    @Override
    public Product saveProduct(ProductDto product) throws BadRequestException {
        if (productRepository.existsByName(product.getName())) {
            log.error("Product Name existing");
            throw new BadRequestException("Product Name is already existing, Please try to use different name");
        }
        Product productN = modelMapper.map(product, Product.class);
        return productRepository.save(productN);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

/*

    public List<Product> getProductsBySearchParams(RequestParamDto params) throws ResourceNotFoundException, BadRequestException {
        List<Product> productList = Collections.EMPTY_LIST;
        if (StringUtils.hasText(params.getName())) {
            log.info("Product search for product name ========== ");
            productList = productRepository.findByName(params.getName());
        }
        else if (StringUtils.hasText(params.getCategory())) {
            log.info("Product search for product category : =================");
            productList = productRepository.findAllByCategory(params.getCategory());
        }
        else if (params.getPrice() != null) {
            log.info("Product search for product price : ===============");
            Sort sort;
            if ("desc".equalsIgnoreCase(params.getOrderBy())) {
                sort = Sort.by(Sort.Direction.DESC, "price");
            } else {
                sort = Sort.by(Sort.Direction.ASC, "price");
            }
            productList = productRepository.findAllByPrice(params.getPrice(), sort);
        }
        else {
            throw new BadRequestException("product search params are empty");
        }
        if (CollectionUtils.isEmpty(productList)) {
            log.error("Products are not available for this search");
            throw  new ResourceNotFoundException("Product for this name not found");
        }
        return productList;
    }*/

    @Override
    public Product editProduct(Integer id, ProductDto product) throws ResourceNotFoundException {
            log.info("product id :" + id);
            Optional<Product> productN = productRepository.findById(id);
            if (productN.isPresent()) {
                Product updateProduct = productN.get();
                updateProduct = modelMapper.map(product, Product.class);
                updateProduct.setId(id);
                return productRepository.save(updateProduct);
            } else {
                log.error("productId not found :" + id);
                throw new ResourceNotFoundException("productId not found: " + id);
            }
    }

    @Override
    public void deleteProduct(Integer id) throws ResourceNotFoundException {
        log.info("product id: "+id);
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                productRepository.deleteById(id);
            }
            else {
                throw new ResourceNotFoundException("provided Product id not found");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("provided Product id not found");
        }
    }

    @Override
    public List<Product> getProductsBySearchParams(ProductSearchDto productSearchDto) {
        int pageSize = 10;
        if (productSearchDto.getPageSize() > 0) {
            pageSize = productSearchDto.getPageSize();
        }
        Pageable pageable = PageRequest.of(getPageNumber(productSearchDto.getPageNumber()), pageSize, getSortingOrder(productSearchDto));
        Page<Product> productPage = productRepository.
                findAll(buildSpecification(productSearchDto), pageable);
        List<Product> recommendationCardDetails = productPage.getContent();
        log.info("result " + recommendationCardDetails);
        return recommendationCardDetails;
    }

    private Sort getSortingOrder(ProductSearchDto productSearchDto) {
        Sort order;
        if (StringUtils.hasLength(productSearchDto.getOrder()) && StringUtils.hasLength(productSearchDto.getOrderBy())) {
            if (productSearchDto.getOrder().equalsIgnoreCase("asc")) {
                order = Sort.by(productSearchDto.getOrderBy()).ascending();
            } else {
                order = Sort.by(productSearchDto.getOrderBy()).descending();
            }
        } else {
            order = Sort.by("price").ascending();
        }
        return order;
    }

    private Integer getPageNumber(Integer pageNumber) {
        if (pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        return pageNumber;
    }

    private Specification<Product> buildSpecification(ProductSearchDto productSearchDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Predicate category = null;
            Predicate predicateName = null;
            Predicate predicatePrice = null;
            Predicate defaultPredicate
                    = criteriaBuilder.isNotNull(root.get("name"));
            predicates.add(defaultPredicate);
            if (StringUtils.hasLength(productSearchDto.getCategory())) {
                category
                        = criteriaBuilder.like(criteriaBuilder.lower(root.get("category")),
                        PERCENTAGE + productSearchDto.getCategory().toLowerCase() + PERCENTAGE);
                predicates.add(category);
            }
            if (StringUtils.hasLength(productSearchDto.getName())) {
                predicateName
                        = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        PERCENTAGE + productSearchDto.getName().toLowerCase() + PERCENTAGE);
                predicates.add(predicateName);
            }
            if (null != productSearchDto.getPrice()) {
                predicatePrice
                        = criteriaBuilder.ge((root.get("price")),
                        productSearchDto.getPrice());
                predicates.add(predicatePrice);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}