package com.auth.produit.Mapper;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;
import com.auth.produit.Entity.Categorie;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    @Mapping(source = "nom", target = "nom")
    CategorieResponseDTO toResponseDTOForCreation(Categorie categorie);

    @Mapping(source = "produits", target = "produitsIds")
    @Mapping(source = "nom", target = "nom")
    CategorieResponseDTO toResponseDTO(Categorie categorie);

    @Mapping(source = "nom", target = "nom")
    Categorie toEntity(CategorieRequestDTO categorieRequestDTO);

}
