package com.example.handlecare.entity.enums;

public enum Permission {
    ORDER_POST("order:post"),
    ORDER_CONFIRM("order:confirm"),
    ORDER_TAKE("order:take"),
    USER_CHANGE("user:change");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
