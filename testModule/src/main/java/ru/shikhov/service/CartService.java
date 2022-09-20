package ru.shikhov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.shikhov.model.Cart;
import ru.shikhov.model.Product;
import ru.shikhov.repository.ProductRepository;

import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;
    private final ProductRepository productRepository;

    public void addProductToCart(Long id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NullPointerException("Product was not founded"));
        cart.add(product);
    }

    public void removeProductFromCart(Long id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new NullPointerException("Product was not founded"));
        cart.remove(product);
    }

    public List<Product> getCartProducts() {
        return cart.getAllProducts();
    }

}
