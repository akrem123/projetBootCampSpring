package com.sip.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.entities.Produit;
import com.sip.entities.Fournisseur;
import com.sip.repositories.ProduitRepository;
import com.sip.repositories.FournisseurRepository;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/produit/")
public class ProduitController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	static String trouve=null;
	
	private final ProduitRepository produitRepository;
	private final FournisseurRepository fournisseurRepository;
    @Autowired
    public ProduitController(ProduitRepository produitRepository, FournisseurRepository fournisseurRepository) {
        this.produitRepository = produitRepository;
        this.fournisseurRepository = fournisseurRepository;
    }
    
    @GetMapping("list")
    public String listProduits(Model model) {
    	//model.addAttribute("Produits", null);
    	List<Produit> la = (List<Produit>)produitRepository.findAll();
    	if(la.size()==0)
    		la = null;
        model.addAttribute("produits", la);
        model.addAttribute("trouve", trouve); 
        return "produit/listProduits";
    }
    
    @GetMapping("add")
    public String showAddProduitForm(Model model) {
    	
    	model.addAttribute("fournisseurs", fournisseurRepository.findAll());
    	model.addAttribute("produit", new Produit());
        return "produit/addProduit";
    }
    
    @PostMapping("add")
    //@ResponseBody
    public String addProduit(@Valid Produit produit, BindingResult result, @RequestParam(name = "fournisseurId", required = true) Long p,
                             @RequestParam("files") MultipartFile[] files
    ) {
    	
    	Fournisseur fournisseur = fournisseurRepository.findById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid fournisseur Id:" + p));
    	produit.setFournisseur(fournisseur);

        /// part upload

        // upload du ficher
        MultipartFile file = files[0];
        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

        try {
            Files.write(fileNameAndPath, file.getBytes());
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Stockage du name du ficher dans la base
        StringBuilder fileName = new StringBuilder();
        fileName.append(file.getOriginalFilename());
        
        produit.setPhotoProfil(fileName.toString());
        System.out.println(produit);


        produitRepository.save(produit);
    	 return "redirect:list";
    	
    }
    
    @GetMapping("delete/{id}")
    public String deleteProduit(@PathVariable("id") long id, Model model) {
        /*Produit artice = ProduitRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid Fournisseur Id:" + id));*/
    	
    	
    	
        Optional<Produit>produit = produitRepository.findById(id);
        
        if(produit.isPresent())
        {
        	 produitRepository.delete(produit.get());
        	 trouve="existe";
        }
        else {  // le problème
        	trouve="inexiste";
        	
        }
     // model.addAttribute("trouve", trouve); 
      
  	//List<Produit> la = (List<Produit>)ProduitRepository.findAll();
	//if(la.size()==0)
	//	la = null;
    //model.addAttribute("Produits", la);
   // return "Produit/listProduits";
    
    
      return "redirect:../list";
    }
    
    @GetMapping("edit/{id}")
    public String showProduitFormToUpdate(@PathVariable("id") long id, Model model) {
    	Produit produit = produitRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid fournisseur Id:" + id));
    	
        model.addAttribute("produit", produit);
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        model.addAttribute("idFournisseur", produit.getFournisseur().getId());
        
        return "produit/updateProduit";
    }
    @PostMapping("edit")
    public String updateProduit( @Valid Produit produit, BindingResult result,
        Model model, @RequestParam(name = "fournisseurId", required = false) Long p  ,@RequestParam("files") MultipartFile[] files ) {
        if (result.hasErrors()) {
        	
            return "produit/updateProduit";
        }
        
        Fournisseur fournisseur = fournisseurRepository.findById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid fournisseur Id:" + p));
        
    	produit.setFournisseur(fournisseur);
    	
    	/// part upload file
        
		
    	StringBuilder fileName = new StringBuilder();
    	MultipartFile file = files[0];
    	//System.out.println(file.isEmpty());
    	if(!file.isEmpty()) {
    	Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    	
    	fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		  produit.setPhotoProfil(fileName.toString());
    	}
		
    	
    	// Fin Upload file
     
       
        produitRepository.save(produit);
    	 return "redirect:list";
    	
                
    	 
    	 
    }

    @GetMapping("show/{id}")
    public String showProduitDetails(@PathVariable("id") long id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid fournisseur Id:" + id));

        model.addAttribute("produit", produit);

        return "produit/showProduit";
    }


}
