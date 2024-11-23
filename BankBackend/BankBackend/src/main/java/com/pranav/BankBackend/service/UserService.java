package com.pranav.BankBackend.service;

import com.pranav.BankBackend.dto.UserRegistrationDTO;
import com.pranav.BankBackend.dto.UserResponseDTO;
import com.pranav.BankBackend.dto.UserUpdateDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcUserDetailsManager userDetailsManager,
                       PasswordEncoder passwordEncoder,
                       JdbcTemplate jdbcTemplate) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void registerUser(UserRegistrationDTO registrationDTO) {
        if (userDetailsManager.userExists(registrationDTO.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User.UserBuilder userBuilder = User.builder()
                .username(registrationDTO.getUsername())
                .password(passwordEncoder.encode(registrationDTO.getPassword()))
                .authorities(registrationDTO.getRole())
                .disabled(!registrationDTO.isEnabled());

        userDetailsManager.createUser(userBuilder.build());
    }

    @Transactional
    public void updateUser(String username, UserUpdateDTO updateDTO) {
        if (!userDetailsManager.userExists(username)) {
            throw new RuntimeException("User not found");
        }

        UserDetails existingUser = userDetailsManager.loadUserByUsername(username);
        User.UserBuilder userBuilder = User.builder()
                .username(username)
                .password(updateDTO.getPassword() != null ?
                        passwordEncoder.encode(updateDTO.getPassword()) :
                        existingUser.getPassword())
                .authorities(updateDTO.getRole() != null ?
                        updateDTO.getRole() :
                        existingUser.getAuthorities().iterator().next().getAuthority())
                .disabled(!updateDTO.isEnabled());

        userDetailsManager.updateUser(userBuilder.build());
    }

    @Transactional
    public void deleteUser(String username) {
        if (!userDetailsManager.userExists(username)) {
            throw new RuntimeException("User not found");
        }
        userDetailsManager.deleteUser(username);
    }

    public UserResponseDTO getUser(String username) {
        if (!userDetailsManager.userExists(username)) {
            throw new RuntimeException("User not found");
        }

        UserDetails user = userDetailsManager.loadUserByUsername(username);
        String role = user.getAuthorities().iterator().next().getAuthority();

        return new UserResponseDTO(
                user.getUsername(),
                role,
                user.isEnabled()
        );
    }

    public List<UserResponseDTO> getAllUsers() {
        return jdbcTemplate.query(
                "SELECT username, authority, enabled FROM users u JOIN authorities a ON u.username = a.username",
                (rs, rowNum) -> new UserResponseDTO(
                        rs.getString("username"),
                        rs.getString("authority"),
                        rs.getBoolean("enabled")
                )
        );
    }

    public boolean checkPassword(String username, String rawPassword) {
        UserDetails user = userDetailsManager.loadUserByUsername(username);
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public void changeUserStatus(String username, boolean enabled) {
        UserDetails user = userDetailsManager.loadUserByUsername(username);
        User.UserBuilder userBuilder = User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(user.getAuthorities().iterator().next().getAuthority())
                .disabled(!enabled);

        userDetailsManager.updateUser(userBuilder.build());
    }
}