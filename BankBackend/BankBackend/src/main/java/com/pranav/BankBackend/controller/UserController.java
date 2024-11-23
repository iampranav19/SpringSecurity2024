package com.pranav.BankBackend.controller;

import com.pranav.BankBackend.dto.UserRegistrationDTO;
import com.pranav.BankBackend.dto.UserUpdateDTO;
import com.pranav.BankBackend.dto.UserResponseDTO;
import com.pranav.BankBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        try {
            userService.registerUser(registrationDTO);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to register user: " + e.getMessage());
        }
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(
            @PathVariable String username,
            @RequestBody UserUpdateDTO updateDTO) {
        try {
            userService.updateUser(username, updateDTO);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user: " + e.getMessage());
        }
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete user: " + e.getMessage());
        }
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        try {
            UserResponseDTO user = userService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get user: " + e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/{username}/check-password")
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public ResponseEntity<Map<String, Boolean>> checkPassword(
            @PathVariable String username,
            @RequestBody Map<String, String> passwordMap) {
        boolean matches = userService.checkPassword(username, passwordMap.get("password"));
        return ResponseEntity.ok(Map.of("matches", matches));
    }

    @PatchMapping("/{username}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeUserStatus(
            @PathVariable String username,
            @RequestBody Map<String, Boolean> status) {
        try {
            userService.changeUserStatus(username, status.get("enabled"));
            return ResponseEntity.ok("User status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user status: " + e.getMessage());
        }
    }
}
/*

Register a new user:
POST /api/v1/users/register
{
    "username": "newuser",
    "password": "password123",
    "role": "ROLE_USER",
    "enabled": true
}

Update an existing user:
PUT /api/v1/users/{username}
{
    "password": "newpassword",
    "role": "ROLE_ADMIN",
    "enabled": false
}

Change user status:
PATCH /api/v1/users/{username}/status
{
    "enabled": true
}

Check password:
POST /api/v1/users/{username}/check-password
{
    "password": "userpassword"
}




New Endpoints:
GET /api/v1/users - Get all users (admin only)
GET /api/v1/users/{username} - Get specific user
PUT /api/v1/users/{username} - Update user
DELETE /api/v1/users/{username} - Delete user
POST /api/v1/users/{username}/check-password - Verify password
PATCH /api/v1/users/{username}/status - Enable/disable user


 */
