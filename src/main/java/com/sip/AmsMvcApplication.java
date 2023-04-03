package com.sip;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sip.controllers.FournisseurController;

@SpringBootApplication
public class AmsMvcApplication {

	public static void main(String[] args) {
		new File(FournisseurController.uploadDirectory).mkdir();
		SpringApplication.run(AmsMvcApplication.class, args);
		
		System.out.println("ok");
	}

}
