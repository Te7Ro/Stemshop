package com.example.stemshop.services;

import com.example.stemshop.models.Product;
import com.example.stemshop.repositories.ProductRepository;
import com.example.stemshop.util.ProductSpecification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class CatalogService {
    private ProductRepository productRepository;

    public List<Product> getFilteredProducts(
            String brand,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Boolean onlyAvailable,
            String sortBy
    ) {
        Specification<Product> spec = Specification.unrestricted();

        if (brand != null && !brand.isEmpty()) {
            spec = spec.and(ProductSpecification.hasBrand(brand));
        }
        if (minPrice != null || maxPrice != null) {
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }
        if (onlyAvailable != null && onlyAvailable) {
            spec = spec.and(ProductSpecification.inStock());
        }

        // Если нужна простая сортировка
        Sort sort = Sort.unsorted();
        if ("newest".equalsIgnoreCase(sortBy)) {
            sort = Sort.by(Sort.Direction.DESC, "createdAt");
            return productRepository.findAll(spec, sort);
        } else if ("priceAsc".equalsIgnoreCase(sortBy)) {
            sort = Sort.by(Sort.Direction.ASC, "price");
            return productRepository.findAll(spec, sort);
        } else if ("priceDesc".equalsIgnoreCase(sortBy)) {
            sort = Sort.by(Sort.Direction.DESC, "price");
            return productRepository.findAll(spec, sort);
        }

        // Если нужна сортировка по взвешенному рейтингу — считаем вручную
        if ("rating".equalsIgnoreCase(sortBy)) {
            List<Product> products = productRepository.findAll(spec);
            double C = products.stream()
                    .filter(p -> p.getRating() != null)
                    .mapToDouble(Product::getRating)
                    .average()
                    .orElse(0.0);
            int m = 10; // минимальное число отзывов

            products.sort((p1, p2) -> {
                double wr1 = weightedRating(p1, m, C);
                double wr2 = weightedRating(p2, m, C);
                return Double.compare(wr2, wr1); // по убыванию
            });

            return products;
        }

        return productRepository.findAll(spec);
    }

    private double weightedRating(Product p, int m, double C) {
        double R = p.getRating() != null ? p.getRating() : 0.0;
        int v = p.getRatingCount() != null ? p.getRatingCount() : 0;
        return (v / (double)(v + m)) * R + (m / (double)(v + m)) * C;
    }

}
