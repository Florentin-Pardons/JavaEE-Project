package javabean;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.Modele_DAO;

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
	
	public Modele(int id, String nom, int nbPorte, int volumeCoffre, Marque marque, Categorie categorie)
	{
		this.id = id;
		this.nom = nom;
		this.nbPorte = nbPorte;
		this.volumeCoffre = volumeCoffre;
		this.marque = marque;
		this.categorie = categorie;
	}
	
	//Methode
	//Creer
	public boolean Creer() throws JsonParseException, JsonMappingException, IOException {
		Modele_DAO modDao = new Modele_DAO();
		return modDao.create(this);
	}
	
	//Delete
	public boolean Delete() throws JsonParseException, JsonMappingException, IOException {
		Modele_DAO modDao = new Modele_DAO();
		return modDao.delete(this);
	}
	
	//Update
	public boolean Update() throws JsonParseException, JsonMappingException, IOException {
		Modele_DAO modDao = new Modele_DAO();
		return modDao.update(this);
	}
	
	//Trouver
	public Modele Trouver(int id) throws JsonParseException, JsonMappingException, IOException
	{
		Modele_DAO marDao = new Modele_DAO();
		return marDao.getModele(id);
	}
		
	//Creation de la liste
	public static List<Modele> List() throws JsonParseException, JsonMappingException, IOException
	{
		Modele_DAO modDao = new Modele_DAO();
		return modDao.list();
	}
}
