package com.auth.produit.DTO.RequesteDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;



@Data
public class ProduitRequestDTO {

    @NotNull(message = "La désignation est obligatoire")
    private String designation;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être un nombre positif")
    private Double prix;

    @NotNull(message = "La quantité est obligatoire")
    @Positive(message = "La quantité doit être un nombre positif")
    private Integer quantite;

    @NotNull(message = "La catégorie est obligatoire")
    private Long categorieId;
}
