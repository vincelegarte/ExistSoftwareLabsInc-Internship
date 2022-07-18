package com.activity.four.security.authorities;

public enum ApplicationPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    TICKET_READ("ticket:read"),
    TICKET_WRITE("ticket:write");

    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
