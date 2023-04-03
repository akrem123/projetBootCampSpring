package com.sip.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


@Entity
public class Fournisseur {

	@Id  //clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "logo")
    private String logo;
	

    @NotBlank(message = "nom is mandatory")
    @Column(name = "nom", nullable=false)
   // @Column(name = "nom")
    private String nom;
    
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;
    
    @NotBlank(message = "Telephone is mandatory")
    @Column(name = "telephone")
    private String telephone;

    
    @NotBlank(message = "Adresse is mandatory")
    @Column(name = "adresse", nullable=false)
    private String adresse;
    
    public Fournisseur() {
    	
	}

    
    public Fournisseur(long id, String logo, String nom, String email,String telephone,String adresse) {
	
		this.id = id;
		this.logo = logo;
		this.nom = nom;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
	}
    
 
    
    
    @Override
	public String toString() {
		return "Fournisseur [id=" + id + ", logo=" + logo + ", nom=" + nom + ", email=" + email + ", telephone="
				+ telephone + ", adresse=" + adresse + "]";
	}

 
    
    

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


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	 @OneToMany(cascade=CascadeType.ALL, mappedBy = "fournisseur")
	    private List<Produit> produits;

	    public List<Produit> getProduits() {
	        return produits;
	    }

	    public void setProduits(List<Produit> produits) {
	        this.produits = produits;
	    }

	
   
   
}