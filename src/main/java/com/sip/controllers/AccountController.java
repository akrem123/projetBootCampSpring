package com.sip.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

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

import com.sip.entities.User;
import com.sip.repositories.UserRepository;
import com.sip.repositories.RoleRepository;
import com.sip.entities.Actualite;
import com.sip.entities.Fournisseur;
import com.sip.entities.Role;

@Controller
@RequestMapping("/accounts/")

public class AccountController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	static String trouve=null;
	
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
    public AccountController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
	
	@GetMapping("list")
    public String listUsers(Model model) {
    	
    	List<User> users = (List<User>) userRepository.findAll();
    	long nbr =  userRepository.count();
    	if(users.size()==0)
    		users = null;
        model.addAttribute("users", users);
        model.addAttribute("nbr", nbr);
        return "user/listUsers";
    }
	
	
	
	@GetMapping("enable/{id}")
	//@ResponseBody
    public String enableUserAcount(@PathVariable ("id") int id) {
    	
		
		 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
	     user.setActive(1);
	     userRepository.save(user);
    	return "redirect:../list";
    }
	
	@GetMapping("disable/{id}")
	//@ResponseBody
	public String disableUserAcount(@PathVariable ("id") int id) {
    	
		 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
	     user.setActive(0);
	     userRepository.save(user);
    	return "redirect:../list";
    }

	@PostMapping("updateRole")
	//@ResponseBody
	public String UpdateUserRole(@RequestParam ("id") int id,
			@RequestParam ("newrole")String newRole
			) {
    	
		 User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
	     
		 Role userRole = roleRepository.findByRole(newRole);
		 
	     user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	     
	     userRepository.save(user);
    	return "redirect:list";
    }
	
	
	
	  
    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
       	
    	
    	
        Optional<User>user = userRepository.findById(id);
        
        if(user.isPresent())
        {
        	
        	userRepository.delete(user.get());
        	 trouve="existe";
        }
        else {  // le problème
        	trouve="inexiste";
        	
        }
        return "redirect:../list";
    
}
}