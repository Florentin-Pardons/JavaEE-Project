package javabean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="marque")
public class Marque {

	//Variable
	private int id;
	private String nom;
	private Date dateCrea;
	private String paysOrigine;
	
	//Getter et Setteur
	@XmlElement(name="id_marque")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="nom_marque")
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@XmlElement(name="datecrea_marque")
	public Date getDateCrea() {
		return dateCrea;
	}
	
	public void setDateCrea(Date dateCrea) {
		this.dateCrea = dateCrea;
	}
	
	@XmlElement(name="paysorigine_marque")
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
