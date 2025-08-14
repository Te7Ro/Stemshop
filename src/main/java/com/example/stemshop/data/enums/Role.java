package com.example.stemshop.data.enums;

import java.util.List;

public enum Role {
    STORE_ADMIN(
            List.of(
                    "product:add",
                    "product:update",
                    "product:delete",
                    "order:view",
                    "order:update"
            )
    ),
    CONTENT_MANAGER(
            List.of(
                    "product:add",
                    "product:update",
                    "product:delete"
            )
    ),
    SUPPORT(
            List.of(
                    "order:view",
                    "order:update"
            )
    ),
    CUSTOMER(
            List.of(
                    "cart:view",
                    "cart:update",
                    "favourites:view",
                    "favourites:update"
            )
    );

    private final List<String> permissions;

    Role(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
