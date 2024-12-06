package com.auth.produit.Service.Implementation;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;
import com.auth.produit.Entity.Categorie;
import com.auth.produit.Exception.CategorieNotFoundException;
import com.auth.produit.Mapper.CategorieMapper;
import com.auth.produit.Repository.CategorieRepository;
import com.auth.produit.Service.Interface.ICategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieService implements ICategorieService {
    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    public CategorieService(CategorieRepository categorieRepository, CategorieMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @Override
    public List<CategorieResponseDTO> getAllCategories() {
        return categorieRepository.findAll()
                .stream()
                .map(categorieMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieResponseDTO getCategorieById(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieNotFoundException("Catégorie avec ID " + id + " n'existe pas."));
        return categorieMapper.toResponseDTO(categorie);
    }

    @Override
    public CategorieResponseDTO createCategorie(CategorieRequestDTO requestDTO) {
        Categorie categorie = categorieMapper.toEntity(requestDTO);
        Categorie savedCategorie = categorieRepository.save(categorie);
        return categorieMapper.toResponseDTO(savedCategorie);
    }

    @Override
    public CategorieResponseDTO updateCategorie(Long id, CategorieRequestDTO requestDTO) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieNotFoundException("Catégorie avec ID " + id + " n'existe pas."));

        categorie.setNom(requestDTO.getNom());
        categorie.setDescription(requestDTO.getDescription());
        Categorie updatedCategorie = categorieRepository.save(categorie);

        return categorieMapper.toResponseDTO(updatedCategorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new CategorieNotFoundException("Catégorie avec ID " + id + " n'existe pas."));
        categorieRepository.delete(categorie);
    }
}
