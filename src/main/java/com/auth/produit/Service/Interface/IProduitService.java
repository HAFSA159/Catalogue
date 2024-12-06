package com.auth.produit.Service.Interface;

import com.auth.produit.DTO.ReponceDTO.ProduitResponseDTO;
import com.auth.produit.DTO.RequesteDTO.ProduitRequestDTO;

import java.util.List;

public interface IProduitService {
    List<ProduitResponseDTO> getAllProduits();

    ProduitResponseDTO getProduitById(Long id);

    ProduitResponseDTO createProduit(ProduitRequestDTO requestDTO);

    ProduitResponseDTO updateProduit(Long id, ProduitRequestDTO requestDTO);

    void deleteProduit(Long id);
}
