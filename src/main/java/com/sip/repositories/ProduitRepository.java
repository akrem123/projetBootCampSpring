package com.sip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.sip.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {

}