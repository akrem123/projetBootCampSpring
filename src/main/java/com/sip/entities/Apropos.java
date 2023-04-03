package com.sip.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Apropos {

	@Id  //clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "logo")
    private String logo;
	

    @NotBlank(message = "Champ is mandatory")
    @Column(name = "champ", nullable=false)
    private String champ;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getChamp() {
		return champ;
	}


	public void setChamp(String champ) {
		this.champ = champ;
	}


	public Apropos(long id, String logo, @NotBlank(message = "Champ is mandatory") String champ) {
		super();
		this.id = id;
		this.logo = logo;
		this.champ = champ;
	}


	public Apropos() {
		super();
	}


	@Override
	public String toString() {
		return "Apropos [id=" + id + ", logo=" + logo + ", champ=" + champ + "]";
	}
    
    
    
    
    
}