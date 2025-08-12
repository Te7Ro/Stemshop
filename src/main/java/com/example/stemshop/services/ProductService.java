package com.example.stemshop.services;

import com.example.stemshop.dto.request.ProductAddRequest;
import com.example.stemshop.dto.request.ProductUpdateRequest;
import com.example.stemshop.dto.response.ProductResponse;
import com.example.stemshop.exceptions.ProductException;
import com.example.stemshop.models.Product;
import com.example.stemshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getArticle(),
                product.getPrice(),
                product.getPhoto(),
                product.getDescription(),
                product.getTechnicalCharacteristics(),
                product.getStock(),
                product.getBrand(),
                product.getRating(),
                product.getRatingCount()
        );
    }

    public ProductResponse getProductByArticle(String article) {
        final Product product = productRepository.findByArticle(article)
                .orElseThrow(() -> new ProductException("Товар не найден"));
        return toResponse(product);
    }

    public ProductResponse addProduct(ProductAddRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setArticle(request.getArticle());
        product.setPrice(request.getPrice());
        product.setPhoto(request.getPhoto());
        product.setDescription(request.getDescription());
        product.setTechnicalCharacteristics(request.getTechnicalCharacteristics());
        product.setStock(request.getStock());
        product.setBrand(request.getBrand());
        productRepository.save(product);
        return toResponse(product);
    }

    public ProductResponse updateProduct(String article, ProductUpdateRequest request) {
        final Product product = productRepository.findByArticle(article)
                .orElseThrow(() -> new ProductException("Товар не найден"));
        if(request.getName() != null) {product.setName(request.getName());}
        if(request.getPrice() != null) {product.setPrice(request.getPrice());}
        if(request.getPhoto() != null) {product.setPhoto(request.getPhoto());}
        if(request.getDescription() != null) {product.setDescription(request.getDescription());}
        if(request.getTechnicalCharacteristics() != null) {product.setTechnicalCharacteristics(request.getTechnicalCharacteristics());}
        if(request.getStock() != null) {product.setStock(request.getStock());}
        if(request.getBrand() != null) {product.setBrand(request.getBrand());}
        productRepository.save(product);
        return toResponse(product);

    }

    public String deleteProduct(String article) {
        final Product product = productRepository.findByArticle(article)
                .orElseThrow(() -> new ProductException("Товар не найден"));
        productRepository.delete(product);
        return "Товар удален";
    }


}
