package javabean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public Voiture(String couleur, String carburant, String boiteVitesse, int nbkm, int age, boolean dispo, Modele modele) 
	{
		this.couleur = couleur;
		this.carburant = carburant;
		this.boiteVitesse = boiteVitesse;
		this.nbkm = nbkm;
		this.age = age;
		this.dispo = dispo;
		this.modele = modele;
	}
	
	public Voiture(int id, String couleur, String carburant, String boiteVitesse, int nbkm, int age, boolean dispo, Modele modele) 
	{
		this.id = id;
		this.couleur = couleur;
		this.carburant = carburant;
		this.boiteVitesse = boiteVitesse;
		this.nbkm = nbkm;
		this.age = age;
		this.dispo = dispo;
		this.modele = modele;
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
		
	//Trouver
	public Voiture Trouver(int id) throws JsonParseException, JsonMappingException, IOException
	{
		Voiture_DAO voiDao = new Voiture_DAO();
		return voiDao.getVoiture(id);
	}
		
	//Creation de la liste
	public static List<Voiture> List() throws JsonParseException, JsonMappingException, IOException
	{
		Voiture_DAO voiDao = new Voiture_DAO();
		
		//Remplir les elements manquants
		List<Voiture> listvoiture = voiDao.list();
		
		for(int i = 0; i < listvoiture.size(); i++)
		{
			Modele mod = new Modele();
			mod = mod.Trouver(listvoiture.get(i).getModele().getId());
			listvoiture.get(i).setModele(mod);
			
			Utilisateur user = new Utilisateur();
			user = user.Trouver(listvoiture.get(i).getUtilisateur().getId());
			listvoiture.get(i).setUtilisateur(user);			
		}
		
		return listvoiture;
	}
	
	//Creation de la liste
	public static List<Voiture> List(Utilisateur user) throws JsonParseException, JsonMappingException, IOException
	{
		Voiture_DAO voiDao = new Voiture_DAO();
		
		//Remplir les elements manquants (Modele)
		List<Voiture> listvoiture = voiDao.list();
		
		for(int i = 0; i < listvoiture.size(); i++)
		{
			Modele mod = new Modele();
			mod = mod.Trouver(listvoiture.get(i).getModele().getId());
			listvoiture.get(i).setModele(mod);			
		}
		
		//Trier la liste pour ne retourner que les éléments de l'utilisateur
		listvoiture = listvoiture.stream()
				.filter(v -> v.getUtilisateur().getId() == user.getId())
                .collect(Collectors.toList());
		
		return listvoiture;
	}
	
	//Recherche de voiture
	public static List<Voiture> Chercher(String couleur, String carburant, String boiteVitesse, int marque, int categorie) throws JsonParseException, JsonMappingException, IOException
	{
		List<Voiture> list = Voiture.List();
		List<Voiture> listreturn = new ArrayList<Voiture>();
		
		
		if(couleur != null && carburant != null && boiteVitesse != null && marque != 0 && categorie != 0)
		{
			listreturn = list.stream()
					.filter(v -> v.getCouleur().equals(couleur))
					.filter(v -> v.getCarburant().equals(carburant))
					.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
					.filter(v -> v.getModele().getMarque().getId() == marque)
					.filter(v -> v.getModele().getCategorie().getId() == categorie)
	                .collect(Collectors.toList());
		}
		else
		{
			if(couleur == null && carburant != null && boiteVitesse != null && marque != 0 && categorie != 0)
			{
				listreturn = list.stream()
						.filter(v -> v.getCarburant().equals(carburant))
						.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
						.filter(v -> v.getModele().getMarque().getId() == marque)
						.filter(v -> v.getModele().getCategorie().getId() == categorie)
		                .collect(Collectors.toList());
			}
			else
			{
				if(couleur != null && carburant == null && boiteVitesse != null && marque != 0 && categorie != 0)
				{
					listreturn = list.stream()
							.filter(v -> v.getCouleur().equals(couleur))
							.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
							.filter(v -> v.getModele().getMarque().getId() == marque)
							.filter(v -> v.getModele().getCategorie().getId() == categorie)
			                .collect(Collectors.toList());
				}
				else
				{
					if(couleur != null && carburant != null && boiteVitesse == null && marque != 0 && categorie != 0)
					{
						listreturn = list.stream()
								.filter(v -> v.getCouleur().equals(couleur))
								.filter(v -> v.getCarburant().equals(carburant))
								.filter(v -> v.getModele().getMarque().getId() == marque)
								.filter(v -> v.getModele().getCategorie().getId() == categorie)
				                .collect(Collectors.toList());
					}
					else
					{
						if(couleur != null && carburant != null && boiteVitesse != null && marque == 0 && categorie != 0)
						{
							listreturn = list.stream()
									.filter(v -> v.getCouleur().equals(couleur))
									.filter(v -> v.getCarburant().equals(carburant))
									.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
									.filter(v -> v.getModele().getCategorie().getId() == categorie)
					                .collect(Collectors.toList());
						}
						else
						{
							if(couleur != null && carburant != null && boiteVitesse != null && marque != 0 && categorie == 0)
							{
								listreturn = list.stream()
										.filter(v -> v.getCouleur().equals(couleur))
										.filter(v -> v.getCarburant().equals(carburant))
										.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
										.filter(v -> v.getModele().getMarque().getId() == marque)
						                .collect(Collectors.toList());
							}
							else
							{
								if(couleur == null && carburant == null && boiteVitesse != null && marque != 0 && categorie != 0)
								{
									listreturn = list.stream()
											.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
											.filter(v -> v.getModele().getMarque().getId() == marque)
											.filter(v -> v.getModele().getCategorie().getId() == categorie)
							                .collect(Collectors.toList());
								}
								else
								{
									if(couleur == null && carburant != null && boiteVitesse == null && marque != 0 && categorie != 0)
									{
										listreturn = list.stream()
												.filter(v -> v.getCarburant().equals(carburant))
												.filter(v -> v.getModele().getMarque().getId() == marque)
												.filter(v -> v.getModele().getCategorie().getId() == categorie)
								                .collect(Collectors.toList());
									}
									else
									{
										if(couleur == null && carburant != null && boiteVitesse != null && marque == 0 && categorie != 0)
										{
											listreturn = list.stream()
													.filter(v -> v.getCarburant().equals(carburant))
													.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
													.filter(v -> v.getModele().getCategorie().getId() == categorie)
									                .collect(Collectors.toList());
										}
										else
										{
											if(couleur == null && carburant != null && boiteVitesse != null && marque != 0 && categorie == 0)
											{
												listreturn = list.stream()
														.filter(v -> v.getCarburant().equals(carburant))
														.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
														.filter(v -> v.getModele().getMarque().getId() == marque)
										                .collect(Collectors.toList());
											}
											else
											{
												if(couleur != null && carburant == null && boiteVitesse == null && marque != 0 && categorie != 0)
												{
													listreturn = list.stream()
															.filter(v -> v.getCouleur().equals(couleur))
															.filter(v -> v.getModele().getMarque().getId() == marque)
															.filter(v -> v.getModele().getCategorie().getId() == categorie)
											                .collect(Collectors.toList());
												}
												else
												{
													if(couleur != null && carburant == null && boiteVitesse != null && marque == 0 && categorie != 0)
													{
														listreturn = list.stream()
																.filter(v -> v.getCouleur().equals(couleur))
																.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
																.filter(v -> v.getModele().getCategorie().getId() == categorie)
												                .collect(Collectors.toList());
													}
													else
													{
														if(couleur != null && carburant == null && boiteVitesse != null && marque != 0 && categorie == 0)
														{
															listreturn = list.stream()
																	.filter(v -> v.getCouleur().equals(couleur))
																	.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
																	.filter(v -> v.getModele().getMarque().getId() == marque)
													                .collect(Collectors.toList());
														}
														else
														{
															if(couleur != null && carburant != null && boiteVitesse == null && marque == 0 && categorie != 0)
															{
																listreturn = list.stream()
																		.filter(v -> v.getCouleur().equals(couleur))
																		.filter(v -> v.getCarburant().equals(carburant))
																		.filter(v -> v.getModele().getCategorie().getId() == categorie)
														                .collect(Collectors.toList());
															}
															else
															{
																if(couleur != null && carburant != null && boiteVitesse == null && marque != 0 && categorie == 0)
																{
																	listreturn = list.stream()
																			.filter(v -> v.getCouleur().equals(couleur))
																			.filter(v -> v.getCarburant().equals(carburant))
																			.filter(v -> v.getModele().getMarque().getId() == marque)
															                .collect(Collectors.toList());
																}
																else
																{
																	if(couleur != null && carburant != null && boiteVitesse != null && marque == 0 && categorie == 0)
																	{
																		listreturn = list.stream()
																				.filter(v -> v.getCouleur().equals(couleur))
																				.filter(v -> v.getCarburant().equals(carburant))
																				.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
																                .collect(Collectors.toList());
																	}
																	else
																	{

																		if(couleur == null && carburant == null && boiteVitesse == null && marque != 0 && categorie != 0)
																		{
																			listreturn = list.stream()
																					.filter(v -> v.getModele().getMarque().getId() == marque)
																					.filter(v -> v.getModele().getCategorie().getId() == categorie)
																	                .collect(Collectors.toList());
																		}
																		else
																		{

																			if(couleur != null && carburant == null && boiteVitesse == null && marque == 0 && categorie != 0)
																			{
																				listreturn = list.stream()
																						.filter(v -> v.getCouleur().equals(couleur))																						
																						.filter(v -> v.getModele().getCategorie().getId() == categorie)
																		                .collect(Collectors.toList());
																			}
																			else
																			{

																				if(couleur != null && carburant != null && boiteVitesse == null && marque == 0 && categorie == 0)
																				{
																					listreturn = list.stream()
																							.filter(v -> v.getCouleur().equals(couleur))
																							.filter(v -> v.getCarburant().equals(carburant))
																			                .collect(Collectors.toList());
																				}
																				else
																				{

																					if(couleur != null && carburant == null && boiteVitesse == null && marque == 0 && categorie == 0)
																					{
																						listreturn = list.stream()
																								.filter(v -> v.getCouleur().equals(couleur))
																								.filter(v -> v.getCarburant().equals(carburant))
																								.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
																								.filter(v -> v.getModele().getMarque().getId() == marque)
																								.filter(v -> v.getModele().getCategorie().getId() == categorie)
																				                .collect(Collectors.toList());
																					}
																					else
																					{

																						if(couleur == null && carburant != null && boiteVitesse == null && marque == 0 && categorie == 0)
																						{
																							listreturn = list.stream()
																									.filter(v -> v.getCarburant().equals(carburant))
																					                .collect(Collectors.toList());
																						}
																						else
																						{

																							if(couleur == null && carburant == null && boiteVitesse != null && marque == 0 && categorie == 0)
																							{
																								listreturn = list.stream()
																										.filter(v -> v.getBoiteVitesse().equals(boiteVitesse))
																						                .collect(Collectors.toList());
																							}
																							else
																							{

																								if(couleur == null && carburant == null && boiteVitesse == null && marque != 0 && categorie == 0)
																								{
																									listreturn = list.stream()
																											.filter(v -> v.getModele().getMarque().getId() == marque)
																							                .collect(Collectors.toList());
																								}
																								else
																								{

																									if(couleur == null && carburant == null && boiteVitesse == null && marque == 0 && categorie != 0)
																									{
																										listreturn = list.stream()
																												.filter(v -> v.getModele().getCategorie().getId() == categorie)
																								                .collect(Collectors.toList());
																									}
																									else
																									{
																										return null;
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return listreturn;
	}
}