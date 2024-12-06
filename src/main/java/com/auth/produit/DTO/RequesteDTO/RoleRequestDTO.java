package com.auth.produit.DTO.RequesteDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RoleRequestDTO {

    @NotNull(message = "Le nom du rôle est obligatoire")
    private String name;
}
