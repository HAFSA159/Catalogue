package com.auth.produit.Controller;

import com.auth.produit.DTO.ReponceDTO.UserResponseDTO;
import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.User;
import com.auth.produit.Service.Implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.login(userRequestDTO);
            log.info("Authenticated user: {}", SecurityContextHolder.getContext().getAuthentication());
            return ResponseEntity.ok(userResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PutMapping("/{userId}/promote-to-admin")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> promoteUserToAdmin(@PathVariable Long userId) {
        try {
            User updatedUser = userService.updateRoleToAdmin(userId);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
