package com.sip.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sip.entities.Fournisseur;
import com.sip.entities.Produit;


@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
    @Query("FROM Produit p WHERE p.fournisseur.id = ?1")
    List<Produit> findProduitsByFournisseur(long id);
	
}

