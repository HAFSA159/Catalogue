package com.auth.produit.Repository;

import com.auth.produit.Entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    @Query("SELECT p FROM Produit p WHERE " +
            "(:designation IS NULL OR LOWER(p.designation) LIKE LOWER(CONCAT('%', :designation, '%'))) AND " +
            "(:categorieId IS NULL OR p.categorie.id = :categorieId)")
    Page<Produit> findByDesignationAndCategorie(@Param("designation") String designation,
                                                @Param("categorieId") Long categorieId,
                                                Pageable pageable);



}
