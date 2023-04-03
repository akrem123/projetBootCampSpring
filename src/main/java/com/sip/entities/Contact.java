package com.sip.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Contact {

	@Id  //clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	
	
	  @NotBlank(message = "Telephone is mandatory")
	    @Column(name = "telephone")
	    private String telephone;
	  
	  @NotBlank(message = "Fax is mandatory")
	    @Column(name = "fax")
	    private String fax;
   
    
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;
    
    
    @NotBlank(message = "Position is mandatory")
    @Column(name = "position", nullable=false)
    private String position;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public Contact(long id, @NotBlank(message = "Telephone is mandatory") String telephone,
			@NotBlank(message = "Fax is mandatory") String fax, @NotBlank(message = "Email is mandatory") String email,
			@NotBlank(message = "Position is mandatory") String position) {
		super();
		this.id = id;
		this.telephone = telephone;
		this.fax = fax;
		this.email = email;
		this.position = position;
	}


	public Contact() {
		super();
	}


	@Override
	public String toString() {
		return "Contact [id=" + id + ", telephone=" + telephone + ", fax=" + fax + ", email=" + email + ", position="
				+ position + "]";
	}
    
    
    
    
    
    
}