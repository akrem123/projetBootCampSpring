package com.sip.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import com.sip.entities.Fournisseur;
import com.sip.entities.Produit;
import com.sip.entities.User;
import com.sip.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sip.entities.Fournisseur;
import com.sip.repositories.FournisseurRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
private final FournisseurRepository fournisseurRepository;
    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurRepository fournisseurRepository, FournisseurService fournisseurService) {
        this.fournisseurRepository = fournisseurRepository;
        this.fournisseurService = fournisseurService;
    }


    @GetMapping("list")
    //@ResponseBody
    public String listFournisseurs(Model model) {
    	List<Fournisseur> lf = this.fournisseurService.getAllFournisseur();

        model.addAttribute("fournisseurs", lf);
        
        return "fournisseur/listFournisseurs";

    }
    
    @GetMapping("add")
    public String showAddFournisseurForm(Model model) {
    	Fournisseur fournisseur = new Fournisseur();// object dont la valeur des attributs par defaut
    	model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/addFournisseur";
    }
    
    @PostMapping("add")
    public String addFournisseur(@Valid Fournisseur fournisseur, BindingResult result,  @RequestParam("files") MultipartFile[] files) {
    	
       /* if (result.hasErrors()) {
            return "fournisseur/addFournisseur";
        }
        if(fournisseur.getNom()=="")
        	fournisseur.setNom(null);
        //providerRepository.save(provider);
        this.fournisseurService.persistFournisseur(fournisseur);*/
        
        
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
        fournisseur.setLogo(fileName.toString());

        System.out.println(fournisseur);


        fournisseurRepository.save(fournisseur);
    	 return "redirect:list";
    }


    
    @GetMapping("delete/{id}")
    public String deleteFournisseur(@PathVariable("id") long id, Model model) {
    	
    	
    	//long id2 = 100L;
    	
    	Fournisseur fournisseur = fournisseurRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid fournisseur Id:" + id));
        
        //System.out.println("suite du programme...");
        
    	fournisseurRepository.delete(fournisseur);
        
        //model.addAttribute("providers", providerRepository.findAll());
        //return "provider/listProviders";
        return "redirect:../list";
    }
    
    
    @GetMapping("edit/{id}")
    public String showFournisseurFormToUpdate(@PathVariable("id") long id, Model model) {
    	Fournisseur fournisseur = fournisseurRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid fournisseur Id:" + id));
        
        model.addAttribute("fournisseur", fournisseur);
        
        return "fournisseur/updateFournisseur";
    }


    
    @PostMapping("update")
    public String updateFournisseur(@Valid Fournisseur fournisseur, BindingResult result, Model model, @RequestParam("files") MultipartFile[] files) {
    	
    	if (result.hasErrors()) {
    		Fournisseur fournisseurToUpdate = fournisseurRepository.findById(fournisseur.getId())
    		            .orElseThrow(()->new IllegalArgumentException("Invalid fournisseur Id:" + fournisseur.getId()));
    		        
    		        model.addAttribute("fournisseur", fournisseurToUpdate);
            return "fournisseur/updateFournisseur";
        }
    	
    	
    	
    	
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
		  fournisseur.setLogo(fileName.toString());
    	}
		
    	
    	// Fin Upload file
     
       
        
    	
    	
    	
    	
    	
    	
    	fournisseurRepository.save(fournisseur);
    	return"redirect:list";
    	
    }
    
    @GetMapping("show/{id}")
    public String showFournisseur(@PathVariable("id") long id, Model model) {
    	Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fournisseur Id:" + id));
        List<Produit> produits = fournisseurRepository.findProduitsByFournisseur(id);
        for (Produit p : produits)
           

        model.addAttribute("produits", produits);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/showFournisseur";
    }

    
    
    
    
    
    
    
}