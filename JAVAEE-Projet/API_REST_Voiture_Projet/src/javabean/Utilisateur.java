package javabean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="utilisateur")
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
	@XmlElement(name="id_utilisateur")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="mail_utilisateur")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@XmlElement(name="mp_utilisateur")
	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	@XmlElement(name="nom_utilisateur")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlElement(name="prenom_utilisateur")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@XmlElement(name="datenaiss_utilisateur")
	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@XmlElement(name="adresse_utilisateur")
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@XmlElement(name="role_utilisateur")
	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	/*
	public List<Voiture> getListVoiture() {
		return listVoiture;
	}

	public void setListVoiture(List<Voiture> listVoiture) {
		this.listVoiture = listVoiture;
	}

	public List<Commentaire> getListVommentaire() {
		return listVommentaire;
	}

	public void setListVommentaire(List<Commentaire> listVommentaire) {
		this.listVommentaire = listVommentaire;
	}

	public List<Reservation> getListReservation() {
		return listReservation;
	}

	public void setListReservation(List<Reservation> listReservation) {
		this.listReservation = listReservation;
	}*/
	
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
		/*this.listVoiture = new ArrayList<Voiture>();
		this.listVommentaire = new ArrayList<Commentaire>();
		this.listReservation = new ArrayList<Reservation>();*/
	}
	
	public Utilisateur(String mail, String mp, String nom, String prenom, String adresse) 
	{
		this.mail = mail;
		this.mp = mp;
		this.nom = nom;
		this.prenom = prenom;
		//this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		//this.role = role;
		/*this.listVoiture = new ArrayList<Voiture>();
		this.listVommentaire = new ArrayList<Commentaire>();
		this.listReservation = new ArrayList<Reservation>();*/
	}
}
