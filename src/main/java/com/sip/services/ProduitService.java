package com.sip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.entities.Produit;
import com.sip.repositories.ProduitRepository;

@Service
public class ProduitService {
    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }
    public List<Produit> getAllFournisseur(){
        return (List<Produit>)produitRepository.findAll();

    }

    public Produit persistFournisseur(Produit produit)
    {
        /// autres traiteement
        return  this.produitRepository.save(produit);
    }
}
