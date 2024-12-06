package com.auth.produit.Controller;
import com.auth.produit.DTO.ReponceDTO.ProduitResponseDTO;
import com.auth.produit.DTO.RequesteDTO.ProduitRequestDTO;
import com.auth.produit.Service.Implementation.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {
    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public ResponseEntity<List<ProduitResponseDTO>> getAllProduits() {
        List<ProduitResponseDTO> produits = produitService.getAllProduits();
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> getProduitById(@PathVariable Long id) {
        ProduitResponseDTO produit = produitService.getProduitById(id);
        return ResponseEntity.ok(produit);
    }

    @PostMapping
    public ResponseEntity<ProduitResponseDTO> createProduit(@Valid @RequestBody ProduitRequestDTO requestDTO) {
        ProduitResponseDTO produit = produitService.createProduit(requestDTO);
        return ResponseEntity.ok(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> updateProduit(
            @PathVariable Long id,
            @Valid @RequestBody ProduitRequestDTO requestDTO) {
        ProduitResponseDTO updatedProduit = produitService.updateProduit(id, requestDTO);
        return ResponseEntity.ok(updatedProduit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }
}
