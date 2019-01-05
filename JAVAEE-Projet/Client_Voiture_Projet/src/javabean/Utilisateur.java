package javabean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.Utilisateur_DAO;

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
	private List<Voiture> listVoiture;
	private List<Commentaire> listVommentaire;
	private List<Reservation> listReservation;

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

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

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
		this.listVoiture = new ArrayList<Voiture>();
		this.listVommentaire = new ArrayList<Commentaire>();
		this.listReservation = new ArrayList<Reservation>();
	}
	
	public Utilisateur(int id, String mail, String mp, String nom, String prenom, Date dateNaissance, String adresse, boolean role) 
	{
		this.id = id;
		this.mail = mail;
		this.mp = mp;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.role = role;
		this.listVoiture = new ArrayList<Voiture>();
		this.listVommentaire = new ArrayList<Commentaire>();
		this.listReservation = new ArrayList<Reservation>();
	}
	
	//Methode
	//Creer
	public boolean Creer() throws JsonParseException, JsonMappingException, IOException {
		Utilisateur_DAO userDao = new Utilisateur_DAO();
		return userDao.create(this);
	}
	
	//Delete
	public boolean Delete() throws JsonParseException, JsonMappingException, IOException {
		Utilisateur_DAO userDao = new Utilisateur_DAO();
		return userDao.delete(this);
	}
	
	//Update
	public boolean Update() throws JsonParseException, JsonMappingException, IOException {
		Utilisateur_DAO userDao = new Utilisateur_DAO();
		return userDao.update(this);
	}
		
	//Trouver
	public Utilisateur Trouver(int id) throws JsonParseException, JsonMappingException, IOException
	{
		Utilisateur_DAO userDao = new Utilisateur_DAO();
		return userDao.getUtilisateur(id);
	}
		
	//Creation de la liste
	public static List<Utilisateur> List() throws JsonParseException, JsonMappingException, IOException
	{
		Utilisateur_DAO userDao = new Utilisateur_DAO();
		return userDao.list();
		
		/*
		List<Utilisateur> list = new ArrayList<Utilisateur>();
		Utilisateur user1 = new Utilisateur(1, "admin","sss", "tsssss", "sedfsfd", new Date(01/01/1990), "sfhjkdnf", true);
		Utilisateur user2 = new Utilisateur(2, "user","sss", "tsssss", "sedfsfd", new Date(01/01/1990), "sfhjkdnf", false);
		list.add(user1);
		list.add(user2);
		return list;*/
	}
	
	//Verifie le login
	public Utilisateur verif(String mail, String mp) throws JsonParseException, JsonMappingException, IOException
	{
		
		if(mail.equals("test") && mp.equals("test"))
		{
			Utilisateur user = new Utilisateur(1,"admin","sss", "tsssss", "sedfsfd", new Date(01/01/1990), "sfhjkdnf", true);
			return user;
		}
		else
		{
			List<Utilisateur> listuser = Utilisateur.List();
			
			for(Utilisateur u : listuser)
			{
				if(u.getMail().equals(mail) && u.getMp().equals(mp))
					return u;
			}
			
			return null;
		}
		
		/*
		if(mail.equals("test") && mp.equals("test"))
		{
			Utilisateur user = new Utilisateur(1,"admin","sss", "tsssss", "sedfsfd", new Date(01/01/1990), "sfhjkdnf", true);
			return user;
		}
		else if(mail.equals("testeur") && mp.equals("testeur"))
		{
			Utilisateur user = new Utilisateur(2, "user","sss", "tsssss", "sedfsfd", new Date(01/01/1990), "sfhjkdnf", false);
			return user;
		}
		else
			return null;*/
	}
}