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
		
        int id_mod = 0;
        int id_mar = 0;
        int id_cat = 0;        
        
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
			
			mod.setMarque(mar);
			mod.setCategorie(cat);
			voi.setModele(mod);
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
				Marque mar = new Marque();
				Categorie cat = new Categorie();
				
		        int id_mod = 0;
		        int id_mar = 0;
		        int id_cat = 0;
		        
				voi.setId(res.getInt("NUM_VOITURE_PK"));
				voi.setCouleur(res.getString("COULEUR"));
				voi.setCarburant(res.getString("CARBURANT"));
				voi.setBoiteVitesse(res.getString("BOITEVITESSE"));
				voi.setNbkm(res.getInt("KILOMETRAGE"));
				voi.setAge(res.getInt("AGE"));
				id_mod = res.getInt("NUM_MODELE_FK");
				
				//init modele
				if(id_mod != 0)
				{
					CallableStatement stmt2 = connec.prepareCall("{ ? = call PAK_MODELE.TROUVER(?)}");
					stmt2.registerOutParameter(1, OracleTypes.CURSOR);
					stmt2.setInt(2, id_mod);
					stmt2.execute();
					ResultSet res2 = (ResultSet)stmt2.getObject(1);
					
					if(res2.next()) //get by
					{
						mod.setId(res2.getInt("NUM_MODELE_PK"));
						mod.setNom(res2.getString("NOM"));
						mod.setNbPorte(res2.getInt("NBPORTE"));
						mod.setVolumeCoffre(res2.getInt("VOLUMECOFFRE"));
						id_mar = res2.getInt("NUM_MARQUE_FK");	
						id_cat = res2.getInt("NUM_CATEGORIE_FK");					
					}
				}
				else
					return Response.status(Status.BAD_REQUEST).build();
				
				//init marque
				if(id_mar != 0)
				{
					CallableStatement stmt3 = connec.prepareCall("{ ? = call PAK_MARQUE.TROUVER(?)}");
					stmt3.registerOutParameter(1, OracleTypes.CURSOR);
					stmt3.setInt(2, id_mar);
					stmt3.execute();
					ResultSet res3 = (ResultSet)stmt3.getObject(1);
					
					if(res3.next()) //get by
					{
						mar.setId(res3.getInt("NUM_MARQUE_PK"));
						mar.setNom(res3.getString("NOM"));
						mar.setDateCrea(res3.getTimestamp("DATECREA"));
						mar.setPaysOrigine(res3.getString("PAYSORIGINE"));
					}
				}
				else
					return Response.status(Status.BAD_REQUEST).build();
				
				//init categorie
				if(id_cat != 0)
				{
					CallableStatement stmt4 = connec.prepareCall("{ ? = call PAK_CATEGORIE.TROUVER(?)}");
					stmt4.registerOutParameter(1, OracleTypes.CURSOR);
					stmt4.setInt(2, id_cat);
					stmt4.execute();
					ResultSet res4 = (ResultSet)stmt4.getObject(1);
					
					if(res4.next()) //get by
					{
						cat.setId(res4.getInt("NUM_CATEGORIE_PK"));
						cat.setNom(res4.getString("NOM"));
						cat.setDescription(res4.getString("DESCRIPTION"));
					}
				}
				else
					return Response.status(Status.BAD_REQUEST).build();
				
				mod.setMarque(mar);
				mod.setCategorie(cat);
				voi.setModele(mod);
				
				listVoiture.add(voi);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listVoiture).build();
	}
}
