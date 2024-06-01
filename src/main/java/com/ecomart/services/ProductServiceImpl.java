package com.ecomart.services;

import com.ecomart.datas.dtos.request.CreateProductRequest;
import com.ecomart.datas.models.Category;
import com.ecomart.datas.models.Product;
import com.ecomart.exceptions.ProductNotFoundException;
import com.ecomart.exceptions.UserNotFoundException;
import com.ecomart.repositories.CustomerRepository;
import com.ecomart.repositories.ProductRepository;
import com.ecomart.repositories.StoreRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    @Override
    public Product createProduct(CreateProductRequest request) throws UserNotFoundException {
//        var customer = customerRepository.findCustomerById(request.getUserId());
//        if(customer == null){
//            throw new UserNotFoundException("User not found");
//        }
        var customerStore = storeRepository.findStoreByUserId(request.getUserId());

        if(customerStore == null){
            throw new UserNotFoundException("Store belonging to that customer not found");
        }
        Product product = modelMapper.map(request, Product.class);
        product.setPrice(BigDecimal.valueOf(request.getPrice()));
        product.setCategory(request.getCategory().toUpperCase());
        var savedProduct = productRepository.save(product);
        customerStore.getProducts().add(savedProduct);
        storeRepository.save(customerStore);
        return savedProduct;
    }

    @Override
    public void deleteProduct(String productId) throws ProductNotFoundException {
        var product = getProduct(productId);
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(String productId) throws ProductNotFoundException {
        var product =  productRepository.findProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        if(product.getQuantity() == 0){
            throw new ProductNotFoundException("Product out of stock");
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
