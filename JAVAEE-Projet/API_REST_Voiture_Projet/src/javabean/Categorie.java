package javabean;

public class Categorie {

	//Variable
	private int id;
	private String nom;
	private String description;
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	//Constructeur
	public Categorie() {}
	
	public Categorie(int id, String nom, String description)
	{
		this.id = id;
		this.nom = nom;
		this.description = description;
	}
}
