package com.cartServicet.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author muttum.venkatayamini
 *
 */
import com.cartServicet.demo.CartDto.CartDtoResponse;
import com.cartServicet.demo.CartDto.Cartdto;
import com.cartServicet.demo.CartDto.DeleteProductDto;
import com.cartServicet.demo.CartDto.FetchCartDto;
import com.cartServicet.demo.CartDto.ProductDto;
import com.cartServicet.demo.CartDto.ProductResponseDto;
import com.cartServicet.demo.Entity.ProductEntity;
import com.cartServicet.demo.Service.CartService;



@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {
	@Autowired
	private CartService cartService;
	
	Logger logger = LoggerFactory.getLogger(CartController.class);
	
	
	@PostMapping("/create-cart")
	public ResponseEntity<CartDtoResponse> createcartRequest(@RequestBody Cartdto cartDto) {
		logger.info("inside the createcartRequest in CartController");
		
		return ResponseEntity.ok(cartService.createCart(cartDto));
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductResponseDto> createUserchat(@RequestBody ProductDto productDto) {
		logger.info("inside the createUserchat in CartController");
		return ResponseEntity.ok(cartService.addProductToCart(productDto));
	}
	
	
	@DeleteMapping("/{cartId}")

    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
		logger.info("inside the deleteCart in CartController");

        try {

            cartService.deleteCart(cartId);
            logger.info("inside the deletedCart in CartController");

            return ResponseEntity.ok("Cart deleted successfully");

        } catch (RuntimeException e) {
        	logger.error("Cart not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");

        }

    }
	
	@DeleteMapping("/delete/productId")

    public ResponseEntity<String> deleteProduct(@RequestBody DeleteProductDto deleteProductDto) {
		logger.info("inside the deleteProduct in CartController");

        try {

            cartService.deleteProduct(deleteProductDto);
            logger.info("inside the deletedProduct in CartController");

            return ResponseEntity.ok("Product deleted successfully");

        } catch (RuntimeException e) {
        	logger.error("Product not found");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

        }

    }
	
	@PostMapping("/fetchproducts")
	public ResponseEntity<List<ProductEntity>> getallProductsByCartId(@RequestBody FetchCartDto fetchCartDto) {
		logger.info("inside the getallProductsByCartId in CartController");
		List<ProductEntity> requests = cartService.getAllProductsByCartId(fetchCartDto.getCartId());

		return new ResponseEntity<List<ProductEntity>>(requests, HttpStatus.OK);

	}
	

	


	

	

}
