package javabean;

import java.util.Date;

public class Marque {

	//Variable
	private int id;
	private String nom;
	private Date dateCrea;
	private String paysOrigine;
	
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
	
	public Date getDateCrea() {
		return dateCrea;
	}
	
	public void setDateCrea(Date dateCrea) {
		this.dateCrea = dateCrea;
	}
	
	public String getPaysOrigine() {
		return paysOrigine;
	}
	
	public void setPaysOrigine(String paysOrigine) {
		this.paysOrigine = paysOrigine;
	}
	
	//Constructeur
	public Marque() {}
	
	public Marque(String nom, Date dateCrea, String paysOrigine)
	{
		this.nom = nom;
		this.dateCrea = dateCrea;
		this.paysOrigine = paysOrigine;
	}
}
