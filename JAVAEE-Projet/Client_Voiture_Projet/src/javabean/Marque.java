package javabean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dao.Marque_DAO;

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
	
	public Marque(int id, String nom, Date dateCrea, String paysOrigine)
	{
		this.id = id;
		this.nom = nom;
		this.dateCrea = dateCrea;
		this.paysOrigine = paysOrigine;
	}
	
	//Methode
	//Creer
	public boolean Creer() throws JsonParseException, JsonMappingException, IOException {
		Marque_DAO mar = new Marque_DAO();
		return mar.create(this);
	}
	
	//Delete
	public boolean Delete() throws JsonParseException, JsonMappingException, IOException {
		Marque_DAO mar = new Marque_DAO();
		return mar.delete(this);
	}
	
	//Update
	public boolean Update() throws JsonParseException, JsonMappingException, IOException {
		Marque_DAO mar = new Marque_DAO();
		return mar.update(this);
	}
		
	//Creation de la liste
	public static List<Marque> List() throws JsonParseException, JsonMappingException, IOException
	{
		//Marque_DAO marDao = new Marque_DAO();
		//return marDao.list();
		
		List<Marque> listmarque = new ArrayList<Marque>();
		Marque m1 = new Marque(1,"test", new Date(01/01/1990),"sdsf");
		Marque m2 = new Marque(2,"hhhh", new Date(01/01/1990),"sdsf");
		
		listmarque.add(m1);
		listmarque.add(m2);
		
		return listmarque;
		
	}
}