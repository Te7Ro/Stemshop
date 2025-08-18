package com.example.stemshop.services.product;

import com.example.stemshop.dto.request.product.ProductAddRequest;
import com.example.stemshop.dto.request.product.ProductUpdateRequest;
import com.example.stemshop.dto.response.product.ProductResponse;
import com.example.stemshop.exceptions.ProductException;
import com.example.stemshop.models.Product;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse getProductByArticle(String sku) {
        final Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductException("Товар не найден"));
        return productMapper.toResponse(product);
    }

    public void addProduct(ProductAddRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setSku(request.getArticle());
        product.setPrice(request.getPrice());
        product.setPhoto(request.getPhoto());
        product.setDescription(request.getDescription());
        product.setTechnicalCharacteristics(request.getTechnicalCharacteristics());
        product.setStock(request.getStock());
        product.setBrand(request.getBrand());
        product.setRating(0.0);
        product.setRatingCount(0);
        productRepository.save(product);
    }

    public void updateProduct(String sku, ProductUpdateRequest request) {
        final Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductException("Товар не найден"));
        if(request.getName() != null) {product.setName(request.getName());}
        if(request.getPrice() != null) {product.setPrice(request.getPrice());}
        if(request.getPhoto() != null) {product.setPhoto(request.getPhoto());}
        if(request.getDescription() != null) {product.setDescription(request.getDescription());}
        if(request.getTechnicalCharacteristics() != null) {product.setTechnicalCharacteristics(request.getTechnicalCharacteristics());}
        if(request.getStock() != null) {product.setStock(request.getStock());}
        if(request.getBrand() != null) {product.setBrand(request.getBrand());}
        productRepository.save(product);

    }

    public void deleteProduct(String sku) {
        final Product product = productRepository.findBySku(sku)
                .orElseThrow(() -> new ProductException("Товар не найден"));
        productRepository.delete(product);
    }


}
