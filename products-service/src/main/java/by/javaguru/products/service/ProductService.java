package by.javaguru.products.service;

import by.javaguru.core.dto.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> findAll();
    Product reserve(Product desiredProduct, UUID orderId);
    void cancelReservation(Product productToCancel, UUID orderId);
    void cancelReservation(UUID productId, int quantity);
    Product save(Product product);
}
