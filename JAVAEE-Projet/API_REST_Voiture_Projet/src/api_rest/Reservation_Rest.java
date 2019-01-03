package api_rest;
/*
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

import javabean.Reservation;
import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;
*/
//@Path("reservation")
public class Reservation_Rest {	
	/*
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerReservation(
    		@FormParam("dateres") String dateres, 
    		@FormParam("num_pers") String num_pers,
    		@FormParam("num_voiture") String num_voiture)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_RESERVATION.AJOUTER(?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setString(2, dateres);
			stmt.setString(3, num_pers);
			stmt.setString(4, num_voiture);
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
    public Response deleteReservation(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_RESERVATION.SUPPRIMER(?)}");
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
    public Response updateReservation(
    		@FormParam("id") int id, 
    		@FormParam("dateres") String dateres, 
    		@FormParam("num_pers") int num_pers,
    		@FormParam("num_voiture") int num_voiture)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_RESERVATION.MODIFIER(?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, dateres);
			stmt.setInt(3, num_pers);
			stmt.setInt(4, num_voiture);
			
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
    @Produces(MediaType.APPLICATION_XML)
    public Response getReservation(@QueryParam("id") int id)
	{
		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Reservation reserv = null;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_RESERVATION.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			if(res.next()) //get by
			{
				reserv = new Reservation();
				reserv.setId(res.getInt("NUM_RES_PK"));
				reserv.setDateRes(res.getTimestamp("DATERES"));	
				
				
				//reserv.setVoiture(res.getInt("NUM_VOITURE_FK"));
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(reserv).build();
    }
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReservation()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		List<Reservation> listReservation = new ArrayList<Reservation>();
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_RESERVATION.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Reservation reserv = new Reservation();
				
				reserv = new Reservation();
				reserv.setId(res.getInt("NUM_RES_PK"));
				reserv.setDateRes(res.getTimestamp("DATERES"));	
				
				
				//reserv.setVoiture(res.getInt("NUM_VOITURE_FK"));
				
				listReservation.add(reserv);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listReservation).build();
	}*/
}
