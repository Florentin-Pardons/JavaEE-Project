package javabean;

import java.util.Date;

public class Commentaire {

	//Variable
	private int id;
	private String texte;
	private Date dateCrea;
	private Voiture voiture;
	
	//Getter et Setteur
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public Date getDateCrea() {
		return dateCrea;
	}
	
	public void setDateCrea(Date dateCrea) {
		this.dateCrea = dateCrea;
	}
	
	public Voiture getVoiture() {
		return voiture;
	}
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	//Constructeur
	public Commentaire() {}
	
	public Commentaire(String texte, Date dateCrea, Voiture voiture) 
	{
		this.texte = texte;
		this.dateCrea = dateCrea;
		this.voiture = voiture;
	}	
}
