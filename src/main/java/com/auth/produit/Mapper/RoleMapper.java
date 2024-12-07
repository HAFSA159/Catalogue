package com.auth.produit.Mapper;

import com.auth.produit.DTO.ReponceDTO.RoleResponseDTO;
import com.auth.produit.DTO.RequesteDTO.RoleRequestDTO;
import com.auth.produit.Entity.Role;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDTO toResponseDTO(Role role);

    Role toEntity(RoleRequestDTO roleRequestDTO);
}
