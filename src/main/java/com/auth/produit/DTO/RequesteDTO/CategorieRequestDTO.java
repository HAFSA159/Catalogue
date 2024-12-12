package com.auth.produit.DTO.RequesteDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CategorieRequestDTO {

    @NotNull(message = "Le nom est obligatoire")
    @JsonProperty("name")
    private String nom;

    private String description;
}
