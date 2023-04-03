package com.sip.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Actualite {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@NotBlank(message = "Titre is mandatory")
	@Column(name = "titre")
	private String titre;
	
	@NotBlank(message = "Titre is mandatory")
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "image")
    private String image;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Actualite() {
		
	}


	public Actualite(long id, @NotBlank(message = "Titre is mandatory") String titre,
			@NotBlank(message = "Titre is mandatory") String description, String image) {

		this.id = id;
		this.titre = titre;
		this.description = description;
		this.image = image;
	}


	@Override
	public String toString() {
		return "Actualite [id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + "]";
	}

	




}
