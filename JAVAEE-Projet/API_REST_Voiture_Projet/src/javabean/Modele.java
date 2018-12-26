package javabean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="modele")
public class Modele {

	//Variable
	private String nom;
	private int nbPorte;
	private int volumeCoffre;
	private Marque marque;
	private Categorie categorie;
	
	//Getter et Setteur
	@XmlElement(name="nom_modele")
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@XmlElement(name="nbporte_modele")
	public int getNbPorte() {
		return nbPorte;
	}
	
	public void setNbPorte(int nbPorte) {
		this.nbPorte = nbPorte;
	}
	
	@XmlElement(name="volume_modele")
	public int getVolumeCoffre() {
		return volumeCoffre;
	}
	
	public void setVolumeCoffre(int volumeCoffre) {
		this.volumeCoffre = volumeCoffre;
	}
	
	@XmlElement(name="marque_modele")
	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	@XmlElement(name="categorie_modele")
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	//Constructeur
	public Modele() {}
	
	public Modele(String nom, int nbPorte, int volumeCoffre, Marque marque, Categorie categorie)
	{
		this.nom = nom;
		this.nbPorte = nbPorte;
		this.volumeCoffre = volumeCoffre;
		this.marque = marque;
		this.categorie = categorie;
	}
}
