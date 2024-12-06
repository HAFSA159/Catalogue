package com.auth.produit.Mapper;

import com.auth.produit.DTO.ReponceDTO.RoleResponseDTO;
import com.auth.produit.DTO.RequesteDTO.RoleRequestDTO;
import com.auth.produit.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleResponseDTO toResponseDTO(Role role);

    Role toEntity(RoleRequestDTO roleRequestDTO);
}
