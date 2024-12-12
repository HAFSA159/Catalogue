package com.auth.produit.Controller;
import com.auth.produit.DTO.ReponceDTO.ProduitResponseDTO;
import com.auth.produit.DTO.RequesteDTO.ProduitRequestDTO;
import com.auth.produit.Service.Implementation.ProduitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping("/admin/produit/add")
    public ResponseEntity<ProduitResponseDTO> createProduit(@Valid @RequestBody ProduitRequestDTO requestDTO) {
        ProduitResponseDTO produit = produitService.createProduit(requestDTO);
        return ResponseEntity.ok(produit);
    }

    @GetMapping("/admin/produit/all")
    public ResponseEntity<List<ProduitResponseDTO>> getAllProduits() {
        List<ProduitResponseDTO> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    @GetMapping({"/admin/produit", "/user/produit"})
    public ResponseEntity<Page<ProduitResponseDTO>> filterProduits(
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) Long categorieId,
            @PageableDefault(size = 10, sort = "designation", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ProduitResponseDTO> produits = produitService.searchProduits(designation, categorieId, pageable);
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/produit/{id}")
    public ResponseEntity<ProduitResponseDTO> getProduitById(@PathVariable Long id) {
        ProduitResponseDTO produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }

    @PutMapping("/produit/{id}")
    public ResponseEntity<ProduitResponseDTO> updateProduit(
            @PathVariable Long id,
            @Valid @RequestBody ProduitRequestDTO requestDTO) {
        ProduitResponseDTO updatedProduit = produitService.updateProduit(id, requestDTO);
        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/produit/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
