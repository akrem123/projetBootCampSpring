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

import com.sip.entities.Contact;
import com.sip.repositories.ContactRepository;

@Controller
@RequestMapping("/contact/")
public class ContactController {
	

	static String trouve=null;
	
	private final ContactRepository contactRepository;
    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    
    @GetMapping("list")
    //@ResponseBody
    public String listContact(Model model) {
    	
    	
        model.addAttribute("contacts", contactRepository.findAll());
        
        return "contact/listContacts";
        
      }

    
   
    
    @GetMapping("add")
    public String showAddContactForm(Model model) {
    	Contact contact = new Contact();// object dont la valeur des attributs par defaut
    	model.addAttribute("contact", contact);
        return "contact/addContact";
    }

 
    
    @PostMapping("add")
    public String addContact(@Valid Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "contact/addContact";
        }
        contactRepository.save(contact);
        return "redirect:list";
    }



    
    @GetMapping("delete/{id}")
    public String deleteContact(@PathVariable("id") long id, Model model) {
        	
    	
        Optional<Contact>contact = contactRepository.findById(id);
        
        if(contact.isPresent())
        {
        	contactRepository.delete(contact.get());
        	 trouve="existe";
        }
        else {  
        	trouve="inexiste";
        	
        }
   
    
    
      return "redirect:../list";
    }
    
    @GetMapping("edit/{id}")
    public String showContactFormToUpdate(@PathVariable("id") long id, Model model) {
    	Contact contact = contactRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid contact Id:" + id));
        
        model.addAttribute("contact", contact);
        
        return "contact/updateContact";
    }


    @PostMapping("update")
    public String updateContact(@Valid Contact contact, BindingResult result, Model model) {
    	
    	
    	contactRepository.save(contact);
    	return"redirect:list";
    	
    }


}
