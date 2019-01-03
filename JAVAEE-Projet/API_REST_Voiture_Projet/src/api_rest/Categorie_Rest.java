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

import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;

@Path("categorie")
public class Categorie_Rest {	
	
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerCategorie(
    		@FormParam("nom") String nom, 
    		@FormParam("description") String description)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_CATEGORIE.AJOUTER(?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setString(2, nom);
			stmt.setString(3, description);
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
    public Response deleteCategorie(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_CATEGORIE.SUPPRIMER(?)}");
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
    public Response updateCategorie(
    		@FormParam("id") int id, 
    		@FormParam("nom") String nom, 
    		@FormParam("description") String description)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_CATEGORIE.MODIFIER(?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, nom);
			stmt.setString(3, description);
			
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
    public Response getCategorie(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Categorie cat = null;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_CATEGORIE.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			if(res.next()) //get by
			{
				cat = new Categorie();
				cat.setId(res.getInt("NUM_CATEGORIE_PK"));
				cat.setNom(res.getString("NOM"));
				cat.setDescription(res.getString("DESCRIPTION"));			
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(cat).build();
    }
	
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategorie()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		List<Categorie> listCategorie = new ArrayList<Categorie>();
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_CATEGORIE.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Categorie cat = new Categorie();
				
				cat.setId(res.getInt("NUM_CATEGORIE_PK"));
				cat.setNom(res.getString("NOM"));
				cat.setDescription(res.getString("DESCRIPTION"));
				
				listCategorie.add(cat);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listCategorie).build();
	}
}
