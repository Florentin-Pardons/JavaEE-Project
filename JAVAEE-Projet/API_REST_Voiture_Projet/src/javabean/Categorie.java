package javabean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="categorie")
public class Categorie {

	//Variable
	private String nom;
	private String description;
	
	//Getter et Setteur
	@XmlElement(name="nom_categorie")
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@XmlElement(name="description_categorie")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	//Constructeur
	public Categorie() {}
	
	public Categorie(String nom, String description)
	{
		this.nom = nom;
		this.description = description;
	}
}
