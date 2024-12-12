package com.auth.produit.Controller;

import com.auth.produit.DTO.ReponceDTO.CategorieResponseDTO;
import com.auth.produit.DTO.RequesteDTO.CategorieRequestDTO;
import com.auth.produit.Service.Implementation.CategorieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping("/admin/categories/create")
    public ResponseEntity<CategorieResponseDTO> createCategorie(@Valid @RequestBody CategorieRequestDTO requestDTO) {
        CategorieResponseDTO categorie = categorieService.createCategorie(requestDTO);
        log.info("User roles hello:  {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return ResponseEntity.ok(categorie);
    }

    @GetMapping({"/admin/categories", "/user/categories"})
    public ResponseEntity<List<CategorieResponseDTO>> getAllCategories() {
        List<CategorieResponseDTO> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping({"/admin/categories/search", "/user/categories/search"})
    public ResponseEntity<CategorieResponseDTO> getCategorieByName(@RequestParam String name) {
        CategorieResponseDTO categorie = categorieService.getCategorieByName(name);
        return ResponseEntity.ok(categorie);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategorieResponseDTO> getCategorieById(@PathVariable Long id) {
        CategorieResponseDTO categorie = categorieService.getCategorieById(id);
        return ResponseEntity.ok(categorie);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategorieResponseDTO> updateCategorie(
            @PathVariable Long id,
            @Valid @RequestBody CategorieRequestDTO requestDTO) {
        CategorieResponseDTO updatedCategorie = categorieService.updateCategorie(id, requestDTO);
        return ResponseEntity.ok(updatedCategorie);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}
