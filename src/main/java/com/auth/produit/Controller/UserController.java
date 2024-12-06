package com.auth.produit.Controller;

import com.auth.produit.DTO.ReponceDTO.UserResponseDTO;
import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.User;
import com.auth.produit.Service.Implementation.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO userResponseDTO = userService.login(userRequestDTO);
            return ResponseEntity.ok(userResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
