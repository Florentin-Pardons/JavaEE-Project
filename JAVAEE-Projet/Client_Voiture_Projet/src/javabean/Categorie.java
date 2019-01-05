package javabean;

import java.io.IOException;
import java.util.ArrayList;
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
	
	public Categorie(int id, String nom, String description)
	{
		this.id = id;
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
		
	//Trouver
	public Categorie Trouver(int id) throws JsonParseException, JsonMappingException, IOException
	{
		Categorie_DAO catDao = new Categorie_DAO();
		return catDao.getCategorie(id);
	}
		
	//Creation de la liste
	public static List<Categorie> List() throws JsonParseException, JsonMappingException, IOException
	{
		Categorie_DAO catDao = new Categorie_DAO();
		return catDao.list();
		
		/*List<Categorie> cat = new ArrayList<Categorie>();
		Categorie s = new Categorie(1, "4x4", "blabla");
		Categorie ss = new Categorie(2, "suv", "yop");
		cat.add(s);
		cat.add(ss);*/
		
		//return cat;
	}	
}