package com.auth.produit.Service.Implementation;

import com.auth.produit.DTO.ReponceDTO.UserResponseDTO;
import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.Role;
import com.auth.produit.Entity.Roles;
import com.auth.produit.Entity.User;
import com.auth.produit.Mapper.UserMapper;
import com.auth.produit.Repository.RoleRepository;
import com.auth.produit.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    public User createUser(UserRequestDTO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User userEntity = userMapper.toEntity(user);
        userEntity.setRole(Roles.USER);

        return userRepository.save(userEntity);
    }

    public User updateRoleToAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + userId));

        user.setRole(Roles.ADMIN);


        return userRepository.save(user);
    }


    public UserResponseDTO login(UserRequestDTO userRequestDto) {


        Optional<User> user = userRepository.findByLogin(userRequestDto.getLogin());


        if (user.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur introuvable avec le login : " + userRequestDto.getLogin());
        }

        User authenticatedUser = user.get();


        log.info("user: {}", authenticatedUser, new BCryptPasswordEncoder().matches(userRequestDto.getPassword(),user.get().getPassword()));

        if(new BCryptPasswordEncoder().matches(userRequestDto.getPassword(),user.get().getPassword())) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userRequestDto.getLogin(),
                                userRequestDto.getPassword()
                        )
                );

                UserResponseDTO userResponseDto = new UserResponseDTO();
                userResponseDto.setId(authenticatedUser.getId());
                userResponseDto.setLogin(authenticatedUser.getLogin());
                userResponseDto.setRoles(Collections.singleton(authenticatedUser.getRole().toString()));

                return userResponseDto;
            } catch (Exception e) {
                throw new IllegalArgumentException("Échec de la connexion : Identifiants incorrects");
            }
        }

        throw new IllegalArgumentException("Password d'utilisateur est incorrect");


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
                    existingUser.setRole(updatedUser.getRole());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + userId));
    }
}
