package com.auth.produit.Controller;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;
import com.auth.produit.Service.Implementation.CategorieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategorieController {
    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public ResponseEntity<List<CategorieResponseDTO>> getAllCategories() {
        List<CategorieResponseDTO> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieResponseDTO> getCategorieById(@PathVariable Long id) {
        CategorieResponseDTO categorie = categorieService.getCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    @PostMapping
    public ResponseEntity<CategorieResponseDTO> createCategorie(@Valid @RequestBody CategorieRequestDTO requestDTO) {
        CategorieResponseDTO categorie = categorieService.createCategorie(requestDTO);
        return ResponseEntity.ok(categorie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieResponseDTO> updateCategorie(
            @PathVariable Long id,
            @Valid @RequestBody CategorieRequestDTO requestDTO) {
        CategorieResponseDTO updatedCategorie = categorieService.updateCategorie(id, requestDTO);
        return ResponseEntity.ok(updatedCategorie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}
