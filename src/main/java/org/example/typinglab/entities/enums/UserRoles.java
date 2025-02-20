package org.example.typinglab.entities.enums;

public enum UserRoles {
    ADMIN("admin"), USER("user");

    private String value;

    UserRoles(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

