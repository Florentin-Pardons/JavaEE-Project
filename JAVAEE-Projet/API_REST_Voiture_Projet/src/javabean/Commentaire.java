package javabean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="commentaire")
public class Commentaire {

	//Variable
	private int id;
	private String texte;
	private Date dateCrea;
	private Voiture voiture;
	
	//Getter et Setteur
	@XmlElement(name="id_commentaire")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="text_commentaire")
	public String getTexte() {
		return texte;
	}
	
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	@XmlElement(name="datecrea_commentaire")
	public Date getDateCrea() {
		return dateCrea;
	}
	
	public void setDateCrea(Date dateCrea) {
		this.dateCrea = dateCrea;
	}
	
	@XmlElement(name="voiture_commentaire")
	public Voiture getVoiture() {
		return voiture;
	}
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	//Constructeur
	public Commentaire() {}
	
	public Commentaire(String texte, Date dateCrea, Voiture voiture) 
	{
		this.texte = texte;
		this.dateCrea = dateCrea;
		this.voiture = voiture;
	}	
}
