package io.treez.orderservice.util;

import io.treez.orderservice.model.Product;

public class OrderServiceTestUtil {
    public static Product getMockProduct() {
        Product product = new Product();
        product.setProductID(1L);
        product.setDescription("Test Product");
        product.setPrice(25.50);
        product.setQuantity(100L);
        product.setProductName("Test");
        return product;
    }
}
