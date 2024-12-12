package com.auth.produit.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    private Double prix;
    private Integer quantite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


}
