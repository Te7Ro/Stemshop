package com.example.stemshop.util;

import com.example.stemshop.dto.response.catalog.CatalogItem;
import com.example.stemshop.models.Product;
import org.springframework.stereotype.Component;


@Component
public class CatalogMapper {

    public CatalogItem toCatalogItem(Product product) {
        CatalogItem ci = new CatalogItem();
        ci.setProductId(product.getId());
        ci.setName(product.getName());
        ci.setSku(product.getSku());
        ci.setPrice(product.getPrice());
        ci.setPhoto(product.getPhoto());
        ci.setDescription(product.getDescription());
        ci.setStock(product.getStock());
        ci.setBrand(product.getBrand());
        ci.setRating(product.getRating());
        return ci;
    }
}
