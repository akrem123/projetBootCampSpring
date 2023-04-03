package com.sip.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity

public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Libelle is mandatory")
	@Column(name = "libelle")
	private String libelle;
	
	@Column(name = "photoFace")
    private String photoFace;
	
	@Column(name = "photoProfil")
    private String photoProfil;

	
	@Column(name = "prix")
	private float prix;
	

	@Column(name = "description")
	private String description;
	
	
	
	@Column(name = "quantiteStock")
	private long quantiteStock;
	
	
	@Column(name = "prixPromotion")
	private float prixPromotion;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateExpiration")
	private Date dateExpiration;
	
	
	@Column(name = "idFournisseur")
	private long idFournisseur;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getPhotoFace() {
		return photoFace;
	}


	public void setPhotoFace(String photoFace) {
		this.photoFace = photoFace;
	}


	public String getPhotoProfil() {
		return photoProfil;
	}


	public void setPhotoProfil(String photoProfil) {
		this.photoProfil = photoProfil;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getQuantiteStock() {
		return quantiteStock;
	}


	public void setQuantiteStock(long quantiteStock) {
		this.quantiteStock = quantiteStock;
	}


	public float getPrixPromotion() {
		return prixPromotion;
	}


	public void setPrixPromotion(float prixPromotion) {
		this.prixPromotion = prixPromotion;
	}


	public Date getDateExpiration() {
		return dateExpiration;
	}


	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}


	public long getIdFournisseur() {
		return idFournisseur;
	}


	public void setIdFournisseur(long idFournisseur) {
		this.idFournisseur = idFournisseur;
	}


	public Produit(long id, @NotBlank(message = "Libelle is mandatory") String libelle, String photoFace,
			String photoProfil, @NotBlank(message = "prix is mandatory") float prix, String description,
			@NotBlank(message = "quantiteStock is mandatory") long quantiteStock, float prixPromotion,
			@NotBlank(message = "dateExpiration is mandatory") Date dateExpiration, long idFournisseur) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.photoFace = photoFace;
		this.photoProfil = photoProfil;
		this.prix = prix;
		this.description = description;
		this.quantiteStock = quantiteStock;
		this.prixPromotion = prixPromotion;
		this.dateExpiration = dateExpiration;
		this.idFournisseur = idFournisseur;
	}


	public Produit() {
		super();
	}


	@Override
	public String toString() {
		return "Produit [id=" + id + ", libelle=" + libelle + ", photoFace=" + photoFace + ", photoProfil="
				+ photoProfil + ", prix=" + prix + ", description=" + description + ", quantiteStock=" + quantiteStock
				+ ", prixPromotion=" + prixPromotion + ", dateExpiration=" + dateExpiration + ", idFournisseur="
				+ idFournisseur + "]";
	}
	
	
	
	
	/**** Many To One ****/
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "fournisseur_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Fournisseur fournisseur;

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	
	

}