package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javabean.Categorie;
import javabean.Marque;
import javabean.Utilisateur;
import javabean.Voiture;

public class test {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub

		//Utilisateur_DAO test = new Utilisateur_DAO();
		
		//System.out.println(test.create());

		/*
		try {
			System.out.println("test");
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Connection c = DriverManager.getConnection("jdbc:oracle:thin:@//char-oracle11.condorcet.be:1521/xe","florentin3","Florentin");
			Statement stmt = c.createStatement();
			
			ResultSet rset = stmt.executeQuery("select * from UTILISATEUR");

			System.out.println("test2");
			while(rset.next())
			{
				System.out.println("test");
				for(int i = 0; i<3; i++)
				{
					System.out.println("test");
					System.out.println(rset.getString(i+1)+"\t\t");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}*/
		
		
		/*
		Categorie_DAO testeur = new Categorie_DAO();
		
		List<Categorie> cat = testeur.list();
		System.out.println(cat.get(0).getNom());*/
		
		/*
		Marque_DAO tee = new Marque_DAO();
		List<Marque> maaa = tee.list();
		
		for (Marque ma : maaa) 
		{
			System.out.println(ma.getId() + " " + ma.getNom() + " " + ma.getDateCrea() + " " + ma.getPaysOrigine());
		}*/
		
		//String test = "[{dateCrea:2017-12-31T23:00:00Z[UTC],id:1,nom:mod,paysOrigine:mod}]";
		//String test = "[UTC]  sdffdsfdsdfsd";
		//test = test.replaceAll("\\b[UTC]\\b", "test");
		//System.out.println(test);
		
		/*String x = "[UTC] pickaxe [UTC]";
		test = test.replaceAll("\\[UTC]","");
		
		System.out.print(test);*/
		
		/*
		Utilisateur_DAO test = new Utilisateur_DAO();
		Utilisateur user = test.getUtilisateur(1);
		
		System.out.println(user.getMail());*/
		/*
		Utilisateur user = new Utilisateur();
		user.setRole(true);
		System.out.println(user.isRole()!=false?"1":"0");*/
		
		/*
		List<Voiture> v = Voiture.Chercher("jaune", null, null, 1, 1);
		
		for(Voiture v1 : v)
		{
			System.out.println(v1.getId());
		}*/
	}
}
