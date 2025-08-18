package com.example.stemshop.services.catalog;

import com.example.stemshop.data.enums.SortType;
import com.example.stemshop.dto.response.catalog.CatalogItem;
import com.example.stemshop.dto.response.catalog.CatalogResponse;
import com.example.stemshop.models.Product;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.util.CatalogMapper;
import com.example.stemshop.util.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CatalogService {
    private final CatalogMapper catalogMapper;
    private ProductRepository productRepository;

    public CatalogResponse getFilteredProducts(
            String brand,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Boolean onlyAvailable,
            String sortBy
    ) {
        Specification<Product> spec = (root, query, cb) -> cb.conjunction();

        if (brand != null && !brand.isEmpty()) {
            spec = spec.and(ProductSpecification.hasBrand(brand));
        }
        if (minPrice != null || maxPrice != null) {
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }
        if (onlyAvailable != null && onlyAvailable) {
            spec = spec.and(ProductSpecification.inStock());
        }

        SortType sortType = SortType.fromString(sortBy);

        List<Product> products = new ArrayList<>();

        switch (sortType) {
            case NEWEST -> products = productRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "createdAt"));
            case PRICE_ASC -> products = productRepository.findAll(spec, Sort.by(Sort.Direction.ASC, "price"));
            case PRICE_DESC -> products = productRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "price"));
            case RATING -> {
                products = productRepository.findAll(spec);
                double C = products.stream()
                        .filter(p -> p.getRating() != null)
                        .mapToDouble(Product::getRating)
                        .average()
                        .orElse(0.0);
                int m = 10; // минимальное число отзывов

                products.sort((p1, p2) -> {
                    double wr1 = weightedRating(p1, m, C);
                    double wr2 = weightedRating(p2, m, C);
                    return Double.compare(wr2, wr1);
                });
            }
            case DEFAULT -> products = productRepository.findAll(spec);
        }

        List<CatalogItem> items = new ArrayList<>();
        for (Product product : products) {
            items.add(catalogMapper.toCatalogItem(product));
        }

        CatalogResponse response = new CatalogResponse();
        response.setItems(items);
        return response;
    }

    private double weightedRating(Product p, int m, double C) {
        double R = p.getRating() != null ? p.getRating() : 0.0;
        int v = p.getRatingCount() != null ? p.getRatingCount() : 0;
        return (v / (double) (v + m)) * R + (m / (double) (v + m)) * C;
    }

}
