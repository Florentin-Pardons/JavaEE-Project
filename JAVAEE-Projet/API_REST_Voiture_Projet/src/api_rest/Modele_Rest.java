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
import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;

@Path("modele")
public class Modele_Rest {	
	
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerModele(
    		@FormParam("nom") String nom, 
    		@FormParam("nbporte") int nbporte,
    		@FormParam("volumecoffre") int volumecoffre,
    		@FormParam("num_marque") int num_marque,
    		@FormParam("num_categorie") int num_categorie)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_MODELE.AJOUTER(?,?,?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setString(2, nom);
			stmt.setInt(3, nbporte);
			stmt.setInt(4, volumecoffre);
			stmt.setInt(5, num_marque);
			stmt.setInt(6, num_categorie);
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
    public Response deleteModele(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_MODELE.SUPPRIMER(?)}");
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
    public Response updateModele(
    		@FormParam("id") int id,
    		@FormParam("nom") String nom, 
    		@FormParam("nbporte") int nbporte,
    		@FormParam("volumecoffre") int volumecoffre,
    		@FormParam("num_marque") int num_marque,
    		@FormParam("num_categorie") int num_categorie)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_MODELE.MODIFIER(?,?,?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, nom);
			stmt.setInt(3, nbporte);
			stmt.setInt(4, volumecoffre);
			stmt.setInt(5, num_marque);
			stmt.setInt(6, num_categorie);
			
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
    public Response getModele(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Modele mod = new Modele();
		Marque mar = new Marque();
		Categorie cat = new Categorie();
		
		int id_mar = 0;
	    int id_cat = 0;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_MODELE.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
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
			
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(mod).build();
    }
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllModele()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		List<Modele> listModele = new ArrayList<Modele>();
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_MODELE.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Modele mod = new Modele();
				Marque mar = new Marque();
				Categorie cat = new Categorie();
				
				int id_mar = 0;
			    int id_cat = 0;
			    
			    mod.setId(res.getInt("NUM_MODELE_PK"));
				mod.setNom(res.getString("NOM"));
				mod.setNbPorte(res.getInt("NBPORTE"));
				mod.setVolumeCoffre(res.getInt("VOLUMECOFFRE"));
				id_mar = res.getInt("NUM_MARQUE_FK");	
				id_cat = res.getInt("NUM_CATEGORIE_FK");
				
				//init marque
				if(id_mar != 0)
				{
					CallableStatement stmt2 = connec.prepareCall("{ ? = call PAK_MARQUE.TROUVER(?)}");
					stmt2.registerOutParameter(1, OracleTypes.CURSOR);
					stmt2.setInt(2, id_mar);
					stmt2.execute();
					ResultSet res2 = (ResultSet)stmt2.getObject(1);
					
					if(res2.next()) //get by
					{
						mar.setId(res2.getInt("NUM_MARQUE_PK"));
						mar.setNom(res2.getString("NOM"));
						mar.setDateCrea(res2.getTimestamp("DATECREA"));
						mar.setPaysOrigine(res2.getString("PAYSORIGINE"));
					}
				}
				else
					return Response.status(Status.BAD_REQUEST).build();
				
				//init categorie
				if(id_cat != 0)
				{
					CallableStatement stmt3 = connec.prepareCall("{ ? = call PAK_CATEGORIE.TROUVER(?)}");
					stmt3.registerOutParameter(1, OracleTypes.CURSOR);
					stmt3.setInt(2, id_cat);
					stmt3.execute();
					ResultSet res3 = (ResultSet)stmt3.getObject(1);
					
					if(res3.next()) //get by
					{
						cat.setId(res3.getInt("NUM_CATEGORIE_PK"));
						cat.setNom(res3.getString("NOM"));
						cat.setDescription(res3.getString("DESCRIPTION"));
					}
				}
				else
					return Response.status(Status.BAD_REQUEST).build();
				
				mod.setMarque(mar);
				mod.setCategorie(cat);
				listModele.add(mod);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listModele).build();
	}
}