package com.pranav.BankBackend.dto;

public class UserResponseDTO {
    private String username;
    private String role;
    private boolean enabled;

    public UserResponseDTO(String username, String role, boolean enabled) {
        this.username = username;
        this.role = role;
        this.enabled = enabled;
    }

    // Getters and Setters...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}