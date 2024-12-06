package com.auth.produit.Mapper;


import com.auth.produit.DTO.ReponceDTO.UserResponseDTO;
import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);
}
