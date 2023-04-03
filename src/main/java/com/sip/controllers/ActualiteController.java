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

import com.sip.entities.Actualite;
import com.sip.repositories.ActualiteRepository;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/actualite/")
public class ActualiteController {

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	static String trouve=null;
	
	private final ActualiteRepository actualiteRepository;
    @Autowired
    public ActualiteController(ActualiteRepository actualiteRepository) {
        this.actualiteRepository = actualiteRepository;
    }
    
    
    @GetMapping("list")
    //@ResponseBody
    public String listActualites(Model model) {
    	
    	
        model.addAttribute("actualites", actualiteRepository.findAll());
        
        return "actualite/listActualites";
        
      }
    

    
   
    
    @GetMapping("add")
    public String showAddActualiteForm(Model model) {
    	Actualite actualite = new Actualite();// object dont la valeur des attributs par defaut
    	model.addAttribute("actualite", actualite);
        return "actualite/addActualite";
    }

    @PostMapping("add")
    public String addActualite(@Valid Actualite actualite, BindingResult result,  @RequestParam("files") MultipartFile[] files) {
    
        
        
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
        actualite.setImage(fileName.toString());

        System.out.println(actualite);


        actualiteRepository.save(actualite);
    	 return "redirect:list";
    }
        
    

    
    @GetMapping("delete/{id}")
    public String deleteActualite(@PathVariable("id") long id, Model model) {
        /*Produit artice = ProduitRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid Fournisseur Id:" + id));*/
    	
    	
    	
        Optional<Actualite>actualite = actualiteRepository.findById(id);
        
        if(actualite.isPresent())
        {
        	actualiteRepository.delete(actualite.get());
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
    public String showActualiteFormToUpdate(@PathVariable("id") long id, Model model) {
    	Actualite actualite = actualiteRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid actualite Id:" + id));
        
        model.addAttribute("actualite", actualite);
        
        return "actualite/updateActualite";
    }


    @PostMapping("update")
    public String updateActualite(@Valid Actualite actualite, BindingResult result, Model model) {
    	
    	
    	actualiteRepository.save(actualite);
    	return"redirect:list";
    	
    }


}
