package com.auth.produit.Service.Interface;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;

import java.util.List;

public interface ICategorieService {
    List<CategorieResponseDTO> getAllCategories();

    CategorieResponseDTO getCategorieById(Long id);

    CategorieResponseDTO createCategorie(CategorieRequestDTO requestDTO);

    CategorieResponseDTO updateCategorie(Long id, CategorieRequestDTO requestDTO);

    void deleteCategorie(Long id);
}
