package com.auth.produit.DTO.RequesteDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.util.Set;

@Data
public class UserRequestDTO {

    @NotNull(message = "Le login est obligatoire")
    @Size(min = 3, max = 50, message = "Le login doit être compris entre 3 et 50 caractères")
    private String login;

    @NotNull(message = "Le mot de passe est obligatoire")
    private String password;

    private Boolean active;

    @NotNull(message = "Les rôles sont obligatoires")
    private Set<Long> rolesIds;
}
