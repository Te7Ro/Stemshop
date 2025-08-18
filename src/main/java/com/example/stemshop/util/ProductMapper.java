package com.example.stemshop.util;

import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSku(),
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
