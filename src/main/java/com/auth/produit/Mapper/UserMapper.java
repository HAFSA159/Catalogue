package com.auth.produit.Mapper;

import com.auth.produit.DTO.RequesteDTO.UserRequestDTO;
import com.auth.produit.Entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

     User toEntity(UserRequestDTO userRequestDTO);
}
