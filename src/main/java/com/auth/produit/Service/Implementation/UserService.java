package com.auth.produit.Service.Implementation;

import com.auth.produit.DTO.ReponceDTO.UserResponseDTO;
import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.Role;
import com.auth.produit.Entity.User;
import com.auth.produit.Mapper.UserMapper;
import com.auth.produit.Repository.RoleRepository;
import com.auth.produit.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    public User createUser(UserRequestDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userEntity = userMapper.toEntity(user);
        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        userEntity.setRoles(Collections.singletonList(defaultRole));

        return userRepository.save(userEntity);
    }


    public UserResponseDTO login(UserRequestDTO userRequestDto) {
        try {
            authenticationManager.authenticate(
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
            userResponseDto.setRoles(
                    user.getRoles().stream()
                            .map(Role::getName)
                            .collect(Collectors.toList())
            );

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
