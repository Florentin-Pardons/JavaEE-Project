package javabean;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.Categorie_DAO;

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
	
	public Categorie(String nom, String description)
	{
		this.nom = nom;
		this.description = description;
	}
	
	//Methode
	//Creer
	public boolean Creer() throws JsonParseException, JsonMappingException, IOException {
		Categorie_DAO cat = new Categorie_DAO();
		return cat.create(this);
	}
	
	//Delete
	public boolean Delete() throws JsonParseException, JsonMappingException, IOException {
		Categorie_DAO cat = new Categorie_DAO();
		return cat.delete(this);
	}
	
	//Update
	public boolean Update() throws JsonParseException, JsonMappingException, IOException {
		Categorie_DAO cat = new Categorie_DAO();
		return cat.update(this);
	}
		
	//Creation de la liste
	public static List<Categorie> List() throws JsonParseException, JsonMappingException, IOException
	{
		Categorie_DAO catDao = new Categorie_DAO();
		return catDao.list();
	}	
}