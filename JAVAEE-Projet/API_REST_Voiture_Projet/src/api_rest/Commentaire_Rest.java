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

import javabean.Commentaire;
import javabean.Voiture;
import oracle.jdbc.internal.OracleTypes;

import java.util.ArrayList;
import java.util.List;

@Path("commentaire")
public class Commentaire_Rest {	
	/*
	//Creer
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response creerCommentaire(
    		@FormParam("texte") String texte, 
    		@FormParam("datecommentaire") String datecommentaire,
    		@FormParam("num_pers") String num_pers,
    		@FormParam("num_voiture") String num_voiture)
	{		
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		Integer res = null;
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_COMMENTAIRE.AJOUTER(?,?,?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.setString(2, texte);
			stmt.setString(3, datecommentaire);
			stmt.setString(4, num_pers);
			stmt.setString(5, num_voiture);
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
    public Response deleteCommentaire(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_COMMENTAIRE.SUPPRIMER(?)}");
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
    public Response updateCommentaire(
    		@FormParam("id") int id,
    		@FormParam("texte") String texte, 
    		@FormParam("datecommentaire") String datecommentaire,
    		@FormParam("num_pers") String num_pers,
    		@FormParam("num_voiture") String num_voiture)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		Response response = null;
		
		try
		{
			stmt = connec.prepareCall("{call PAK_COMMENTAIRE.MODIFIER(?,?,?,?,?)}");
			stmt.setInt(1, id);
			stmt.setString(2, texte);
			stmt.setString(3, datecommentaire);
			stmt.setString(4, num_pers);
			stmt.setString(5, num_voiture);
			
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
    public Response getCommentaire(@QueryParam("id") int id)
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		Commentaire com = new Commentaire();
		Voiture voi = new Voiture();
		
		int id_voi = 0;
        
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_COMMENTAIRE.TROUVER(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.setInt(2, id);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			if(res.next()) //get by
			{
				com.setId(res.getInt("NUM_COMM_PK"));
				com.setTexte(res.getString("TEXTE"));
				com.setDateCrea(res.getTimestamp("DATECOMMENTAIRE"));
				id_voi = res.getInt("NUM_VOITURE_FK");
			}
			
			//init modele
			if(id_voi != 0)
			{
				stmt = connec.prepareCall("{ ? = call PAK_MODELE.TROUVER(?)}");
				stmt.registerOutParameter(1, OracleTypes.CURSOR);
				stmt.setInt(2, id_voi);
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
				}
			}
			else
				return Response.status(Status.BAD_REQUEST).build();
			
			com.setVoiture(voi);
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(com).build();
    }
	
	//Get All
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCommentaire()
	{
		Connection connec = OracleConnexion.getInstance();
		CallableStatement stmt = null;
		ResultSet res = null;
		
		List<Commentaire> listCommentaire = new ArrayList<Commentaire>();
		
		try
		{
			stmt = connec.prepareCall("{ ? = call PAK_COMMENTAIRE.TOUS()}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			res = (ResultSet)stmt.getObject(1);
			
			while(res.next()) //get all
			{
				Commentaire com = new Commentaire();
				
				com = new Commentaire();
				com.setId(res.getInt("NUM_COMM_PK"));
				com.setTexte(res.getString("TEXTE"));
				com.setDateCrea(res.getTimestamp("DATECOMMENTAIRE"));	
				
				listCommentaire.add(com);
			}
		}
		catch(SQLException e){
			return Response.status(Status.BAD_REQUEST).build();
		}
		
        return Response.status(Status.OK).entity(listCommentaire).build();
	}*/
}
