package javabean;


public class Modele {

	//Variable
	private int id;
	private String nom;
	private int nbPorte;
	private int volumeCoffre;
	private Marque marque;
	private Categorie categorie;
	
	//Getter et Setteur
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getNbPorte() {
		return nbPorte;
	}
	
	public void setNbPorte(int nbPorte) {
		this.nbPorte = nbPorte;
	}
	
	public int getVolumeCoffre() {
		return volumeCoffre;
	}
	
	public void setVolumeCoffre(int volumeCoffre) {
		this.volumeCoffre = volumeCoffre;
	}
	
	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

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
