package com.ecomart.controllers;


import com.ecomart.datas.dtos.request.*;
import com.ecomart.datas.dtos.response.OrderResponse;
import com.ecomart.datas.models.AppUser;
import com.ecomart.exceptions.EmailAlreadyExistException;
import com.ecomart.exceptions.NotAdultException;
import com.ecomart.exceptions.ProductNotFoundException;
import com.ecomart.exceptions.UserNotFoundException;
import com.ecomart.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AppController {
    private final CartServiceImpl cartService;
    private final CustomerServiceImpl customerService;
    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;
    private final StoreServiceImp storeService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody CustomerRegisterRequest request) throws EmailAlreadyExistException {
        return ResponseEntity.ok(customerService.register(request));
    }


    @PostMapping("/store/create-store")
    public ResponseEntity<?> createStore(@RequestBody CreateStoreRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(storeService.createStore(request));
    }

    @PostMapping("/product/create-product")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/product/get-all-products")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/get-all-products/{productId}")
    public ResponseEntity<?> getAProduct(@PathVariable String productId) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping("/cart/add-to-cart")
    public ResponseEntity<?> addItemToCart(@RequestBody AddToCartRequest request) throws UserNotFoundException, ProductNotFoundException, NotAdultException {
        return ResponseEntity.ok(cartService.addProductToCart(request.getProductId(), request.getUserId(), request.getQuantity()));
    }

    @PostMapping("/order/make-order/{customerId}")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable String customerId) throws UserNotFoundException {
        return ResponseEntity.ok(orderService.makeOrder(customerId));
    }

}


