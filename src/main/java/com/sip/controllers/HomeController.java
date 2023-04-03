package com.sip.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	 @RequestMapping(value={"/","h**"})
	    //@ResponseBody
	    public String home() {
	        return "front/index.html";
	    }

	    public String login() {
	        return "";
	    }

	    public String registration() {
	        return "";
	    }

	    public String forgotpassword() {
	        return "";
	    }


	   @RequestMapping("/actualite")
	   public String actualite() {
	        return "front/actualite.html";
	    }
	   
	   

}
