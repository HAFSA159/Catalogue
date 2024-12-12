package com.auth.produit.Entity;


import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

}
