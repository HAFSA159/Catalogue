package com.auth.produit.Service.Implementation;

import com.auth.produit.DTO.ReponceDTO.UserResponseDTO;
import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.User;
import com.auth.produit.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserResponseDTO login(UserRequestDTO userRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDto.getLogin(),
                            userRequestDto.getPassword()
                    )
            );

            User user = userRepository.findByLogin(userRequestDto.getLogin())
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec le login : " + userRequestDto.getLogin()));

            UserResponseDTO userResponseDto = new UserResponseDTO();
            userResponseDto.setId(user.getId());
            userResponseDto.setLogin(user.getLogin());
//            userResponseDto.setRoles(
//                    user.getRoles().stream()
//                            .map(role -> role.getName())
//                            .collect(Collectors.toList())
//            );

            return userResponseDto;
        } catch (Exception e) {
            throw new IllegalArgumentException("Échec de la connexion : Identifiants incorrects");
        }
    }


    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setLogin(updatedUser.getLogin());
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    existingUser.setActive(updatedUser.getActive());
                    existingUser.setRoles(updatedUser.getRoles());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + userId));
    }
}
