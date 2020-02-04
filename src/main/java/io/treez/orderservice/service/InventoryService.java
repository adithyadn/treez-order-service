package io.treez.orderservice.service;

import io.treez.orderservice.exceptions.ProductNotFoundException;
import io.treez.orderservice.model.Product;
import io.treez.orderservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(long productId, Product product) {
        Product repoProduct = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        repoProduct.setProductName(product.getProductName());
        repoProduct.setPrice(product.getPrice());
        repoProduct.setDescription(product.getDescription());
        repoProduct.setQuantity(product.getQuantity());
        return productRepository.save(repoProduct);
    }

    public void deleteProduct(long productId) {
        Product repoProduct = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);

        productRepository.delete(repoProduct);
    }
}
