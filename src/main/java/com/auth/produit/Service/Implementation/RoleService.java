package com.auth.produit.Service.Implementation;

import com.auth.produit.DTO.ReponceDTO.RoleResponseDTO;
import com.auth.produit.DTO.RequesteDTO.RoleRequestDTO;
import com.auth.produit.Entity.Role;
import com.auth.produit.Exception.RoleNotFoundException;
import com.auth.produit.Mapper.RoleMapper;
import com.auth.produit.Repository.RoleRepository;
import com.auth.produit.Service.Interface.IRoleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Rôle avec ID " + id + " n'existe pas."));
        return roleMapper.toResponseDTO(role);
    }

    @Override
    public RoleResponseDTO getRoleByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Rôle avec le nom " + name + " n'existe pas."));
        return roleMapper.toResponseDTO(role);
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO requestDTO) {
        Role role = roleMapper.toEntity(requestDTO);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toResponseDTO(savedRole);
    }

    @Override
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO requestDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Rôle avec ID " + id + " n'existe pas."));

        role.setName(requestDTO.getName());
        Role updatedRole = roleRepository.save(role);

        return roleMapper.toResponseDTO(updatedRole);
    }


    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Rôle avec ID " + id + " n'existe pas."));
        roleRepository.delete(role);
    }
}
