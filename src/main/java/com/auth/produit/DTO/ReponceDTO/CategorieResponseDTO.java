package com.auth.produit.DTO.ReponceDTO;

import lombok.Data;

import java.util.List;

@Data
public class CategorieResponseDTO {

    private Long id;
    private String nom;
    private String description;
    private List<Long> produitsIds;
}
