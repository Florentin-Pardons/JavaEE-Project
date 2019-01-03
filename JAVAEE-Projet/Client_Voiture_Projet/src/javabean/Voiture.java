package javabean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.Voiture_DAO;

public class Voiture {

	//Variable
	private int id;
	private String couleur;
	private String carburant;
	private String boiteVitesse;
	private int nbkm;
	private int age;
	private boolean dispo;
	private Modele modele;
	private Utilisateur user;
		
	//Getter et Setteur
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	public String getCarburant() {
		return carburant;
	}
	
	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}
	
	public String getBoiteVitesse() {
		return boiteVitesse;
	}
	
	public void setBoiteVitesse(String boiteVitesse) {
		this.boiteVitesse = boiteVitesse;
	}
	
	public int getNbkm() {
		return nbkm;
	}
	
	public void setNbkm(int nbkm) {
		this.nbkm = nbkm;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean isDispo() {
		return dispo;
	}
	
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	
	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}
	
	public Utilisateur getUtilisateur() {
		return user;
	}

	public void setUtilisateur(Utilisateur user) {
		this.user = user;
	}
	
	//Constructeur
	public Voiture() {}
	
	public Voiture(String couleur, String carburant, String boiteVitesse, int nbkm, int age, boolean dispo, Modele modele, Utilisateur user) 
	{
		super();
		this.couleur = couleur;
		this.carburant = carburant;
		this.boiteVitesse = boiteVitesse;
		this.nbkm = nbkm;
		this.age = age;
		this.dispo = dispo;
		this.modele = modele;
		this.user = user;
	}
	
	//Methode
	//Creer
	public boolean Creer() throws JsonParseException, JsonMappingException, IOException {
		Voiture_DAO voi = new Voiture_DAO();
		return voi.create(this);
	}
	
	//Delete
	public boolean Delete() throws JsonParseException, JsonMappingException, IOException {
		Voiture_DAO voi = new Voiture_DAO();
		return voi.delete(this);
	}
	
	//Update
	public boolean Update() throws JsonParseException, JsonMappingException, IOException {
		Voiture_DAO voi = new Voiture_DAO();
		return voi.update(this);
	}
		
	//Creation de la liste
	public static List<Voiture> List() throws JsonParseException, JsonMappingException, IOException
	{
		//Voiture_DAO voiDao = new Voiture_DAO();
		//return voiDao.list();
		
		List<Voiture> listvoiture = new ArrayList<Voiture>();
		Voiture v1 = new Voiture();
		v1.setId(1);
		v1.setAge(11);
		v1.setBoiteVitesse("auto");
		v1.setCarburant("essence");
		
		Voiture v2 = new Voiture();
		v2.setId(2);
		v2.setAge(22);
		v2.setBoiteVitesse("manuel");
		v2.setCarburant("diessel");
		
		listvoiture.add(v1);
		listvoiture.add(v2);
		
		return listvoiture;
	}
}