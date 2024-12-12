package com.auth.produit.Service.Implementation;

import com.auth.produit.DTO.ReponceDTO.ProduitResponseDTO;
import com.auth.produit.DTO.RequesteDTO.ProduitRequestDTO;
import com.auth.produit.Entity.Categorie;
import com.auth.produit.Entity.Produit;
import com.auth.produit.Exception.CategorieNotFoundException;
import com.auth.produit.Exception.ProduitNotFoundException;
import com.auth.produit.Mapper.ProduitMapper;
import com.auth.produit.Repository.CategorieRepository;
import com.auth.produit.Repository.ProduitRepository;

import com.auth.produit.Service.Interface.IProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService implements IProduitService {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final ProduitMapper produitMapper;


    @Override
    public List<ProduitResponseDTO> getAllProduits() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Page<ProduitResponseDTO> searchProduits(String designation, Long categorieId, Pageable pageable) {
        return produitRepository.findByDesignationAndCategorie(designation, categorieId, pageable)
                .map(produitMapper::toResponseDTO);
    }



    @Override
    public ProduitResponseDTO getProduitById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit avec ID " + id + " n'existe pas."));
        return produitMapper.toResponseDTO(produit);
    }

    @Override
    public ProduitResponseDTO createProduit(ProduitRequestDTO requestDTO) {
        Categorie categorie = categorieRepository.findById(requestDTO.getCategorieId())
                .orElseThrow(() -> new CategorieNotFoundException("Catégorie avec ID " + requestDTO.getCategorieId() + " n'existe pas."));

        Produit produit = produitMapper.toEntity(requestDTO);
        produit.setCategorie(categorie);

        Produit savedProduit = produitRepository.save(produit);
        return produitMapper.toResponseDTO(savedProduit);
    }

    @Override
    public ProduitResponseDTO updateProduit(Long id, ProduitRequestDTO requestDTO) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit avec ID " + id + " n'existe pas."));

        Categorie categorie = categorieRepository.findById(requestDTO.getCategorieId())
                .orElseThrow(() -> new CategorieNotFoundException("Catégorie avec ID " + requestDTO.getCategorieId() + " n'existe pas."));

        produit.setDesignation(requestDTO.getDesignation());
        produit.setPrix(requestDTO.getPrix());
        produit.setQuantite(requestDTO.getQuantite());
        produit.setCategorie(categorie);

        Produit updatedProduit = produitRepository.save(produit);
        return produitMapper.toResponseDTO(updatedProduit);
    }

    @Override
    public void deleteProduit(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("Produit avec ID " + id + " n'existe pas."));
        produitRepository.delete(produit);
    }
}
