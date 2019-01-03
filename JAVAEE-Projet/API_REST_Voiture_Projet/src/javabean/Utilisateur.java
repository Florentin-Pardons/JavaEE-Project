package javabean;

import java.util.Date;

public class Utilisateur {

	//Variable
	private int id;
	private String mail;
	private String mp;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String adresse;
	private boolean role;

	//Getter et Setteur
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	//Constructeur
	public Utilisateur(String mail, String mp)
	{}
	
	public Utilisateur() {}
	
	public Utilisateur(String mail, String mp, String nom, String prenom, Date dateNaissance, String adresse, boolean role) 
	{
		this.mail = mail;
		this.mp = mp;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.role = role;
	}
	
	public Utilisateur(String mail, String mp, String nom, String prenom, String adresse) 
	{
		this.mail = mail;
		this.mp = mp;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
}
