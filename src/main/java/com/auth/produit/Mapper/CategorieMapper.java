package com.auth.produit.Mapper;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;
import com.auth.produit.Entity.Categorie;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    CategorieResponseDTO toResponseDTO(Categorie categorie);
    Categorie toEntity(CategorieRequestDTO categorieRequestDTO);
}
