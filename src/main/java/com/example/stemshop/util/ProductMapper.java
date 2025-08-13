package com.example.stemshop.util;

import com.example.stemshop.dto.request.product.ProductAddRequest;
import com.example.stemshop.dto.request.product.ProductUpdateRequest;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.exceptions.ProductException;
import com.example.stemshop.models.Product;
import com.example.stemshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ProductRepository productRepository;

    public ProductResponse toResponse(Product product) {
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
}
