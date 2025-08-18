package com.example.stemshop.data.enums;

public enum SortType {
    NEWEST,
    PRICE_ASC,
    PRICE_DESC,
    RATING,
    DEFAULT;

    public static SortType fromString(String value) {
        if (value == null) return DEFAULT;
        return switch (value.toLowerCase()) {
            case "newest" -> NEWEST;
            case "priceasc" -> PRICE_ASC;
            case "pricedesc" -> PRICE_DESC;
            case "rating" -> RATING;
            default -> DEFAULT;
        };
    }
}