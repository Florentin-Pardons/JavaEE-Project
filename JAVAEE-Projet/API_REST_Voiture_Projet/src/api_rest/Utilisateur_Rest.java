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

import javabean.Utilisateur;
import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;

@Path("utilisateur")
public class Utilisateur_Rest {	
	
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerUtilisateur(
    		@FormParam("mail") String mail, 
    		@FormParam("motdepasse") String motdepasse,
    		@FormParam("nom") String nom,
    		@FormParam("prenom") String prenom,
    		@FormParam("datenaissance") String datenaissance,
    		@FormParam("adresse") String adresse,
    		@FormParam("statut") int statut)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_UTILISATEUR.AJOUTER(?,?,?,?,?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setString(2, mail);
			stmt.setString(3, motdepasse);
			stmt.setString(4, nom);
			stmt.setString(5, prenom);
			stmt.setString(6, datenaissance);
			stmt.setString(7, adresse);
			stmt.setInt(8, statut);
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
    public Response deleteUtilisateur(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_UTILISATEUR.SUPPRIMER(?)}");
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
    public Response updateUtilisateur(
    		@FormParam("id") int id,
    		@FormParam("mail") String mail, 
    		@FormParam("motdepasse") String motdepasse,
    		@FormParam("nom") String nom,
    		@FormParam("prenom") String prenom,
    		@FormParam("datenaissance") String datenaissance,
    		@FormParam("adresse") String adresse,
    		@FormParam("statut") int statut)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_UTILISATEUR.MODIFIER(?,?,?,?,?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, mail);
			stmt.setString(3, motdepasse);
			stmt.setString(4, nom);
			stmt.setString(5, prenom);
			stmt.setString(6, datenaissance);
			stmt.setString(7, adresse);
			stmt.setInt(8, statut);
			
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
    public Response getUtilisateur(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Utilisateur user = null;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_UTILISATEUR.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
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
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(user).build();
    }
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUtilisateur()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		List<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_UTILISATEUR.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Utilisateur user = new Utilisateur();
				
				user = new Utilisateur();
				user.setId(res.getInt("NUM_PERS_PK"));
				user.setMail(res.getString("MAIL"));
				user.setMp(res.getString("MOTDEPASSE"));
				user.setNom(res.getString("NOM"));
				user.setPrenom(res.getString("PRENOM"));
				user.setDateNaissance(res.getTimestamp("DATENAISSANCE"));
				user.setAdresse(res.getString("ADRESSE"));
				//user.setRole(res.getInt("STATUT")!=0?true:false);	
				
				listUtilisateur.add(user);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listUtilisateur).build();
	}
}