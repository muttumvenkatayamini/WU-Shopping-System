package com.wu.cartservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.cartservice.dto.DeleteProductDto;
import com.wu.cartservice.dto.CartDto;
import com.wu.cartservice.dto.CartDtoResponse;
import com.wu.cartservice.entity.Cart;
import com.wu.cartservice.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	Logger logger = LoggerFactory.getLogger(CartController.class);

	@PostMapping("/addProduct")
	public ResponseEntity<CartDtoResponse> createUserchat(@RequestBody CartDto productDto) {
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

	@DeleteMapping("/deleteByProduct")
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

	@GetMapping("/fetchproducts/{cartId}")
	public ResponseEntity<List<Cart>> getallProductsByCartId(@PathVariable Long cartId) {
		logger.info("inside the getallProductsByCartId in CartController");
		List<Cart> requests = cartService.getAllProductsByCartId(cartId);

		return new ResponseEntity<List<Cart>>(requests, HttpStatus.OK);

	}

}
