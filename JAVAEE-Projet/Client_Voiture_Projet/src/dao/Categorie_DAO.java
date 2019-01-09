package dao;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javabean.Categorie;

public class Categorie_DAO {
	
	//Creer
	public boolean create(Categorie cat) throws JsonParseException, JsonMappingException, IOException
	{		
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.add("id", Integer.toString(cat.getId()));
		params.add("nom", cat.getNom());
		params.add("description",cat.getDescription());
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("categorie")
				.type("application/x-www-form-urlencoded")
				.post(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Delete
	public boolean delete(Categorie cat) throws JsonParseException, JsonMappingException, IOException
	{		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("categorie")
				.queryParam("id", Integer.toString(cat.getId()))
				.delete(ClientResponse.class);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Update
	public boolean update(Categorie cat) throws JsonParseException, JsonMappingException, IOException
	{		
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("id", Integer.toString(cat.getId()));
		params.add("nom", cat.getNom());
		params.add("description",cat.getDescription());
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("categorie")
				.type("application/x-www-form-urlencoded")
				.put(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Trouver
	public Categorie getCategorie(int id) throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("categorie")
				.queryParam("id", Integer.toString(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		ObjectMapper mapper = new ObjectMapper();
		Categorie cat = mapper.readValue(jsonAnswer, Categorie.class);
		
		return cat;
	}
	
	//Tous
	public List<Categorie> list() throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("categorie/all")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		ObjectMapper mapper = new ObjectMapper();
		List<Categorie> cat = mapper.readValue(jsonAnswer, new TypeReference<List<Categorie>>() {});
		
		return cat;
	}
}
