package com.ecomart.services;

import com.ecomart.datas.dtos.response.OrderResponse;
import com.ecomart.datas.models.CartItem;
import com.ecomart.datas.models.Order;
import com.ecomart.exceptions.CartIsEmptyException;
import com.ecomart.exceptions.UserNotFoundException;
import com.ecomart.repositories.CartItemRepository;
import com.ecomart.repositories.CartRepository;
import com.ecomart.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service @Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerServiceImpl customerService;
    private final KieContainer kieContainer;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public OrderResponse makeOrder(String customerId) throws UserNotFoundException {
        var customer = customerService.getCustomerById(customerId);
        var cart = customer.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        var ref = generateRef();
        if(cartItems == null || cartItems.isEmpty()) throw new CartIsEmptyException("Cart is empty, add items to your cart first");

        List<CartItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItem newItem = new CartItem();
            newItem.setProductId(cartItem.getProductId());
            newItem.setProductName(cartItem.getProductName());
            newItem.setProductDescription(cartItem.getProductDescription());
            newItem.setProductPrice(cartItem.getProductPrice());
            newItem.setQuantity(cartItem.getQuantity());
            newItem.setUnitPrice(cartItem.getUnitPrice());
            orderItems.add(newItem);
        }

        Order order = Order.builder().orderedBy(customer.getName()).orderedAt(LocalDateTime.now())
                .cartId(cart.getId())
                .totalAmount(cart.getSubTotal())
                .referenceCode(ref).status("Pending")
                .orderItems(orderItems).build();

        log.info("Order amount b4 update: {}", order.getTotalAmount());
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(cart);
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        var savedOrder = orderRepository.save(order);
        log.info("Order amount after update: {}", order.getTotalAmount());
        log.info("Order amount from db: {}", savedOrder.getTotalAmount());
        log.info("Cart items in Order1: {}", cartItems);
        log.info("Order items in Order1: {}", savedOrder.getOrderItems().toString());
        cart.setSubTotal(BigDecimal.ZERO);
        cart.getCartItems().clear();
        cartRepository.save(cart);
        log.info("Cart items in Order1: {}", cartItems);
        return OrderResponse.builder()
                .message("Your order placed successfully")
                .order(savedOrder)
                .build();
    }


    public static String generateRef() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder referenceCode = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i <= 10; i++) {
            int index = random.nextInt(characters.length());
            referenceCode.append(characters.charAt(index));
        }

        return referenceCode.toString();
    }
}
