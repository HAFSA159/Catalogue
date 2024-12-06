package com.auth.produit.Mapper;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;
import com.auth.produit.Entity.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);


    CategorieResponseDTO toResponseDTO(Categorie categorie);


    Categorie toEntity(CategorieRequestDTO categorieRequestDTO);
}
