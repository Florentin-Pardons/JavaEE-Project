package api_rest;

import java.sql.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javabean.Categorie;
import javabean.Marque;
import javabean.Modele;
import javabean.Utilisateur;
import javabean.Voiture;

import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;

@Path("voiture")
public class Voiture_Rest {	
	
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerVoiture(
    		@FormParam("num_modele") int num_modele, 
    		@FormParam("couleur") String couleur,
    		@FormParam("carburant") String carburant,
    		@FormParam("boitevitesse") String boitevitesse,
    		@FormParam("kilometrage") int kilometrage,
    		@FormParam("age") int age,
    		@FormParam("num_pers") int num_pers)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_VOITURE.AJOUTER(?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setInt(2, num_modele);
			stmt.setString(3, carburant);
			stmt.setString(4, boitevitesse);
			stmt.setString(5, boitevitesse);
			stmt.setInt(6, kilometrage);
			stmt.setInt(7, age);
			stmt.setInt(8, num_pers);
			stmt.execute();
			res = stmt.getInt(1);
			
			if(res != 0) //creer
				response = Response.status(Status.OK).entity(res).build();
			else
				response = Response.status(Status.BAD_REQUEST).build();
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return response;
    }
	
	//Delete
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
    public Response deleteVoiture(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_VOITURE.SUPPRIMER(?)}");
			stmt.setInt(1, id);
			stmt.execute();

			response = Response.status(Status.OK).build();
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		return response;
    }
	
	//Update
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
    public Response updateVoiture(
    		@FormParam("id") int id, 
    		@FormParam("num_modele") int num_modele, 
    		@FormParam("couleur") String couleur,
    		@FormParam("carburant") String carburant,
    		@FormParam("boitevitesse") String boitevitesse,
    		@FormParam("kilometrage") int kilometrage,
    		@FormParam("age") int age,
    		@FormParam("num_pers") int num_pers)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_VOITURE.MODIFIER(?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setInt(2, num_modele);
			stmt.setString(3, carburant);
			stmt.setString(4, boitevitesse);
			stmt.setString(5, boitevitesse);
			stmt.setInt(6, kilometrage);
			stmt.setInt(7, age);
			stmt.setInt(8, num_pers);
			
			stmt.execute();

			response = Response.status(Status.OK).build();
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		return response;
    }
	
	//Get
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVoiture(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Voiture voi = new Voiture();
		Modele mod = new Modele();
		Marque mar = new Marque();
		Categorie cat = new Categorie();
		Utilisateur user = new Utilisateur();
		
        int id_mod = 0;
        int id_mar = 0;
        int id_cat = 0;
        int id_user = 0;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_VOITURE.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			if(res.next()) //get by
			{
				voi.setId(res.getInt("NUM_VOITURE_PK"));
				voi.setCouleur(res.getString("COULEUR"));
				voi.setCarburant(res.getString("CARBURANT"));
				voi.setBoiteVitesse(res.getString("BOITEVITESSE"));
				voi.setNbkm(res.getInt("KILOMETRAGE"));
				voi.setAge(res.getInt("AGE"));
				id_mod = res.getInt("NUM_MODELE_FK");
				id_user = res.getInt("NUM_PERS_FK");
			}
			
			//init modele
			if(id_mod != 0)
			{
				stmt = connec.prepareCall("{ ? = call PAK_MODELE.TROUVER(?)}");
				stmt.registerOutParameter(1, OracleTypes.CURSOR);
				stmt.setInt(2, id_mod);
				stmt.execute();
				res = (ResultSet)stmt.getObject(1);
				
				if(res.next()) //get by
				{
					mod.setId(res.getInt("NUM_MODELE_PK"));
					mod.setNom(res.getString("NOM"));
					mod.setNbPorte(res.getInt("NBPORTE"));
					mod.setVolumeCoffre(res.getInt("VOLUMECOFFRE"));
					
					id_mar = res.getInt("NUM_MARQUE_FK");	
					id_cat = res.getInt("NUM_CATEGORIE_FK");
				}
			}
			else
				return Response.status(Status.BAD_REQUEST).build();
			
			//init marque
			if(id_mar != 0)
			{
				stmt = connec.prepareCall("{ ? = call PAK_MARQUE.TROUVER(?)}");
				stmt.registerOutParameter(1, OracleTypes.CURSOR);
				stmt.setInt(2, id_mar);
				stmt.execute();
				res = (ResultSet)stmt.getObject(1);
				
				if(res.next()) //get by
				{
					mar.setId(res.getInt("NUM_MARQUE_PK"));
					mar.setNom(res.getString("NOM"));
					mar.setDateCrea(res.getTimestamp("DATECREA"));
					mar.setPaysOrigine(res.getString("PAYSORIGINE"));
				}
			}
			else
				return Response.status(Status.BAD_REQUEST).build();
			
			//init categorie
			if(id_cat != 0)
			{
				stmt = connec.prepareCall("{ ? = call PAK_CATEGORIE.TROUVER(?)}");
				stmt.registerOutParameter(1, OracleTypes.CURSOR);
				stmt.setInt(2, id_cat);
				stmt.execute();
				res = (ResultSet)stmt.getObject(1);
				
				if(res.next()) //get by
				{
					cat.setId(res.getInt("NUM_CATEGORIE_PK"));
					cat.setNom(res.getString("NOM"));
					cat.setDescription(res.getString("DESCRIPTION"));
				}
			}
			else
				return Response.status(Status.BAD_REQUEST).build();
			
			//init personne
			if(id_user != 0)
			{
				stmt = connec.prepareCall("{ ? = call PAK_PERSONNE.TROUVER(?)}");
				stmt.registerOutParameter(1, OracleTypes.CURSOR);
				stmt.setInt(2, id_user);
				stmt.execute();
				res = (ResultSet)stmt.getObject(1);
				
				if(res.next()) //get by
				{
					user = new Utilisateur();
					user.setId(res.getInt("NUM_PERS_PK"));
					user.setMail(res.getString("MAIL"));
					user.setMp(res.getString("MOTDEPASSE"));
					user.setNom(res.getString("NOM"));
					user.setPrenom(res.getString("PRENOM"));
					user.setDateNaissance(res.getTimestamp("DATENAISSANCE"));
					user.setAdresse(res.getString("ADRESSE"));
					user.setRole(res.getInt("STATUT")!=0?true:false);
				}
			}
			else
				return Response.status(Status.BAD_REQUEST).build();
			
			mod.setMarque(mar);
			mod.setCategorie(cat);
			voi.setModele(mod);
			voi.setUtilisateur(user);
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(voi).build();
    }
	
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVoiture()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
        
		List<Voiture> listVoiture = new ArrayList<Voiture>();
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_VOITURE.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Voiture voi = new Voiture();
				Modele mod = new Modele();
				Utilisateur user = new Utilisateur();
				
				voi.setId(res.getInt("NUM_VOITURE_PK"));
				voi.setCouleur(res.getString("COULEUR"));
				voi.setCarburant(res.getString("CARBURANT"));
				voi.setBoiteVitesse(res.getString("BOITEVITESSE"));
				voi.setNbkm(res.getInt("KILOMETRAGE"));
				voi.setAge(res.getInt("AGE"));
				
				
				mod.setId(res.getInt("NUM_MODELE_FK"));
				voi.setModele(mod);
				
				user.setId(res.getInt("NUM_PERS_FK"));
				voi.setUtilisateur(user);
				
				listVoiture.add(voi);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listVoiture).build();
	}
}
