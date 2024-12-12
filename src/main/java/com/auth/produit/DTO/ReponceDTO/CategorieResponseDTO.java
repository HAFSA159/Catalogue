package com.auth.produit.DTO.ReponceDTO;

import com.auth.produit.Entity.Produit;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorieResponseDTO {

    private Long id;
    private String nom;
    private String description;
    private List<ProduitResponseDTO> produitsIds;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProduitResponseDTO {
        private Long id;
    }

}
