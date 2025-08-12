package com.example.stemshop.data;

import java.util.Set;

public enum Role {
    STORE_ADMIN(Set.of(
            "ROLE_STORE_ADMIN",
            "PERM_MANAGE_STORE",
            "PERM_MANAGE_INTEGRATIONS",
            "PERM_MANAGE_USERS"
    )),
    CONTENT_MANAGER(Set.of(
            "ROLE_CONTENT_MANAGER",
            "PERM_ADD_PRODUCTS",
            "PERM_MANAGE_CATEGORIES",
            "PERM_MANAGE_PROMOTIONS"
    )),
    CUSTOMER(Set.of(
            "ROLE_CUSTOMER",
            "PERM_SEARCH_PRODUCTS",
            "PERM_PLACE_ORDERS",
            "PERM_TRACK_ORDERS"
    )),
    SUPPORT(Set.of(
            "ROLE_SUPPORT",
            "PERM_UPDATE_ORDER_STATUS",
            "PERM_HANDLE_RETURNS",
            "PERM_MANAGE_DELIVERY"
    ));

    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }
}
