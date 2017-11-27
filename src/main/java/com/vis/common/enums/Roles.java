package com.vis.common.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Roles implements Serializable{
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String roleString;
    private static final Map<String, Roles> lookup = new HashMap<String, Roles>();

    static {
        Arrays.stream(Roles.values()).forEach(role -> lookup.put(role.getRoleString(),role));
    }

    Roles(String roleString) {
        this.roleString = roleString;
    }

    public String getRoleString() {
        return roleString;
    }

    public static Roles getRole(String roleString) {
        return lookup.get(roleString);
    }

}
