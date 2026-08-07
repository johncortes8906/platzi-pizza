package com.platzi.pizza.persistence.entity;

import java.util.Objects;

public class UserRoleId {

    private String username;
    private String role;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId that)) return false;
        return Objects.equals(username, that.username) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
