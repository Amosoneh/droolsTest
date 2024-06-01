package com.ecomart.services;


import com.ecomart.datas.models.CartItem;
import com.ecomart.exceptions.NotAdultException;
import com.ecomart.exceptions.ProductNotFoundException;
import com.ecomart.exceptions.UserNotFoundException;
import com.ecomart.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service @AllArgsConstructor @Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final KieContainer kieContainer;


    @Override
    public String addProductToCart(String productId, String userId, int quantity) throws UserNotFoundException, ProductNotFoundException, NotAdultException {
        var customer = customerRepository.findCustomerById(userId);
        if(customer == null) throw new UserNotFoundException("User not found");
        log.info("Customer: {}", customer);
        var product = productService.getProduct(productId);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(product);
        kieSession.insert(customer);
        kieSession.fireAllRules();
        kieSession.dispose();

        if(product.isAdultOnly()){
            var cart = customer.getCart();

            CartItem cartItem = CartItem.builder()
                    .unitPrice(product.getPrice())
                    .productId(productId)
                    .productDescription(product.getDescription())
                    .quantity(quantity).productName(product.getName())
                    .productPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                    .build();

            cart.getCartItems().add(cartItem);
            cart.setSubTotal(cart.getSubTotal().add(cartItem.getProductPrice()));
            product.setQuantity(product.getQuantity() - quantity);

            productRepository.save(product);
            customerRepository.save(customer);
            cartRepository.save(cart);
            cartItemRepository.save(cartItem);
            return "Product added to cart successfully";
         }else {
            throw new NotAdultException("Product not available for you");
        }
    }

}
