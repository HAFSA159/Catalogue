package com.auth.produit.DTO.RequesteDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CategorieRequestDTO {

    @NotNull(message = "Le nom est obligatoire")
    private String nom;

    private String description;
}
