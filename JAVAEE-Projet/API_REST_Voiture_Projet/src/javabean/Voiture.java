package javabean;

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
		
	//Getter et Setteur
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
		
	//Getter et Setteur
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
	
	public boolean getDispo() {
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
	
	//Constructeur
	public Voiture() {}
	
	public Voiture(int id, String couleur, String carburant, String boiteVitesse, int nbkm, int age, boolean dispo, Modele modele) 
	{
		this.id =id;
		this.couleur = couleur;
		this.carburant = carburant;
		this.boiteVitesse = boiteVitesse;
		this.nbkm = nbkm;
		this.age = age;
		this.dispo = dispo;
		this.modele = modele;
	}

}
