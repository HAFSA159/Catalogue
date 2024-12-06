package com.auth.produit.Mapper;

import com.auth.produit.DTO.ReponceDTO.ProduitResponseDTO;
import com.auth.produit.DTO.RequesteDTO.ProduitRequestDTO;
import com.auth.produit.Entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "categorie.nom", target = "categorieNom")
    ProduitResponseDTO toResponseDTO(Produit produit);

    @Mapping(source = "categorieId", target = "categorie.id")
    Produit toEntity(ProduitRequestDTO produitRequestDTO);
}

