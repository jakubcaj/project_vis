package com.vis.common.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Role implements Serializable{
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    PROFILES("ROLE_PROFILES"),
    RELEASE_PUBLIC("ROLE_RELEASE_PUBLIC");

    private final String roleString;
    private static final Map<String, Role> lookup = new HashMap<>();

    static {
        Arrays.stream(Role.values()).forEach(role -> lookup.put(role.getRoleString(),role));
    }

    Role(String roleString) {
        this.roleString = roleString;
    }

    public String getRoleString() {
        return roleString;
    }

    public static Role getRole(String roleString) {
        return lookup.get(roleString);
    }

}
