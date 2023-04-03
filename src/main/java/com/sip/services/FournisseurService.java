package com.sip.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.entities.Fournisseur;
import com.sip.entities.User;
import com.sip.repositories.FournisseurRepository;





@Service
public class FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }
    public List<Fournisseur> getAllFournisseur(){
        return (List<Fournisseur>)fournisseurRepository.findAll();

    }

    public Fournisseur persistFournisseur(Fournisseur fournisseur)
    {
        /// autres traiteement
        return  this.fournisseurRepository.save(fournisseur);
    }
    
   
    
}


