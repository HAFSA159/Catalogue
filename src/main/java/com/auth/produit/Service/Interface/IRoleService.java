package com.auth.produit.Service.Interface;

import com.auth.produit.DTO.ReponceDTO.RoleResponseDTO;
import com.auth.produit.DTO.RequesteDTO.RoleRequestDTO;

import java.util.List;

public interface IRoleService {
    List<RoleResponseDTO> getAllRoles();

    RoleResponseDTO getRoleById(Long id);

    RoleResponseDTO getRoleByName(String name);

    RoleResponseDTO createRole(RoleRequestDTO requestDTO);

    RoleResponseDTO updateRole(Long id, RoleRequestDTO requestDTO);

    void deleteRole(Long id);
}
