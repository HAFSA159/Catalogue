package com.auth.produit.DTO.ReponceDTO;

import lombok.Data;

@Data
public class ProduitResponseDTO {

    private Long id;
    private String designation;
    private Double prix;
    private Integer quantite;
    private Long categorieId;
    private String categorieNom;
}
