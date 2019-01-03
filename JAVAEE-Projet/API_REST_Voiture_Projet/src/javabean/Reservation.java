package javabean;

import java.util.Date;

public class Reservation {

	//Variable
	private int id;
	private Date dateRes;
	private Voiture voiture;
	
	//Getter et Setteur
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDateRes() {
		return dateRes;
	}
	
	public void setDateRes(Date dateRes) {
		this.dateRes = dateRes;
	}
	
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
