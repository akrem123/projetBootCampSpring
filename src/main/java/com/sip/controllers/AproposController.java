package com.sip.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sip.entities.Apropos;
import com.sip.repositories.AproposRepository;

@Controller
@RequestMapping("/apropos/")
public class AproposController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	static String trouve=null;
	
	private final AproposRepository aproposRepository;
    @Autowired
    public AproposController(AproposRepository aproposRepository) {
        this.aproposRepository = aproposRepository;
    }
    
    
    @GetMapping("list")
    //@ResponseBody
    public String listApropos(Model model) {
    	
    	
        model.addAttribute("aproposs", aproposRepository.findAll());
        
        return "apropos/listAproposs";
        
      }

    
   
    
    @GetMapping("add")
    public String showAddAproposForm(Model model) {
    	Apropos apropos = new Apropos();// object dont la valeur des attributs par defaut
    	model.addAttribute("apropos", apropos);
        return "apropos/addApropos";
    }

    @PostMapping("add")
    public String addApropos(@Valid Apropos apropos, BindingResult result,  @RequestParam("files") MultipartFile[] files) {
    	
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
        apropos.setLogo(fileName.toString());

        System.out.println(apropos);


        aproposRepository.save(apropos);
    	 return "redirect:list";
    }
        
    

    
    @GetMapping("delete/{id}")
    public String deleteApropos(@PathVariable("id") long id, Model model) {
        /*Produit artice = ProduitRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid Fournisseur Id:" + id));*/
    	
    	
    	
        Optional<Apropos>apropos = aproposRepository.findById(id);
        
        if(apropos.isPresent())
        {
        	aproposRepository.delete(apropos.get());
        	 trouve="existe";
        }
        else {  // le problème
        	trouve="inexiste";
        	
        }
  
    
      return "redirect:../list";
    }
    
    @GetMapping("edit/{id}")
    public String showAproposFormToUpdate(@PathVariable("id") long id, Model model) {
    	Apropos apropos = aproposRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid apropos Id:" + id));
        
        model.addAttribute("apropos", apropos);
        
        return "apropos/updateApropos";
    }


    @PostMapping("update")
    public String updateApropos(@Valid Apropos apropos, BindingResult result, Model model) {
    	
    	
    	aproposRepository.save(apropos);
    	return"redirect:list";
    	
    }


}
