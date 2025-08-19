package com.example.stemshop.dto.response.catalog;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogResponse {

    @Schema(description = "Список товаров")
    private List<CatalogItem> items;
}
