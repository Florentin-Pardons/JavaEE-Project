package javabean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="reservation")
public class Reservation {

	//Variable
	private int id;
	private Date dateRes;
	private Voiture voiture;
	
	//Getter et Setteur
	@XmlElement(name="id_reservation")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="dateres_reservation")
	public Date getDateRes() {
		return dateRes;
	}
	
	public void setDateRes(Date dateRes) {
		this.dateRes = dateRes;
	}
	
	@XmlElement(name="voiture_reservation")
	public Voiture getVoiture() {
		return voiture;
	}
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	//Constructeur
	public Reservation() {}
	
	public Reservation(Date dateRes, Voiture voiture) {
		this.dateRes = dateRes;
		this.voiture = voiture;
	}
}
