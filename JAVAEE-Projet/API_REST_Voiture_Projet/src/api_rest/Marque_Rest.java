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

import javabean.Marque;
import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;

@Path("marque")
public class Marque_Rest {	
	
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerMarque(
    		@FormParam("nom") String nom, 
    		@FormParam("datecrea") String datecrea,
    		@FormParam("paysorigine") String paysorigine)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_MARQUE.AJOUTER(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setString(2, nom);
			stmt.setString(3, datecrea);
			stmt.setString(4, paysorigine);
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
    public Response deleteMarque(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_MARQUE.SUPPRIMER(?)}");
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
    public Response updateMarque(
    		@FormParam("id") int id, 
    		@FormParam("nom") String nom, 
    		@FormParam("datecrea") String datecrea,
    		@FormParam("paysorigine") String paysorigine)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_MARQUE.MODIFIER(?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, nom);
			stmt.setString(3, datecrea);
			stmt.setString(4, paysorigine);
			
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
    public Response getMarque(@QueryParam("id") int id)
	{
		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Marque mar = null;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_MARQUE.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			if(res.next()) //get by
			{
				mar = new Marque();
				mar.setId(res.getInt("NUM_MARQUE_PK"));
				mar.setNom(res.getString("NOM"));
				mar.setDateCrea(res.getTimestamp("DATECREA"));	
				mar.setPaysOrigine(res.getString("PAYSORIGINE"));
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(mar).build();
    }
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllMarque()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		List<Marque> listMarque = new ArrayList<Marque>();
		/*Marque mar = new Marque();
		mar.setNom("test");
		listMarque.add(mar);
		return Response.status(Status.OK).entity(listMarque).build();*/
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_MARQUE.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Marque mar = new Marque();
				
				mar.setId(res.getInt("NUM_MARQUE_PK"));
				mar.setNom(res.getString("NOM"));
				mar.setDateCrea(res.getTimestamp("DATECREA"));	
				mar.setPaysOrigine(res.getString("PAYSORIGINE"));
				
				listMarque.add(mar);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listMarque).build();
	}
}