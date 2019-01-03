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

import javabean.Modele;

public class Modele_DAO {
	
	//Creer
	public boolean create(Modele mod) throws JsonParseException, JsonMappingException, IOException
	{		
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("nom", mod.getNom());
		params.add("nbporte", Integer.toString(mod.getNbPorte()));
		params.add("volumecoffre", Integer.toString(mod.getVolumeCoffre()));
		params.add("num_marque", Integer.toString(mod.getMarque().getId()));
		params.add("num_categorie", Integer.toString(mod.getCategorie().getId()));
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("modele")
				.type("applimodion/x-www-form-urlencoded")
				.post(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Delete
	public boolean delete(Modele mod) throws JsonParseException, JsonMappingException, IOException
	{		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("modele/"+mod.getId())
				.delete(ClientResponse .class);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Update
	public boolean update(Modele mod) throws JsonParseException, JsonMappingException, IOException
	{		
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("id", Integer.toString(mod.getId()));
		params.add("nom", mod.getNom());
		params.add("nbporte", Integer.toString(mod.getNbPorte()));
		params.add("volumecoffre", Integer.toString(mod.getVolumeCoffre()));
		params.add("num_marque", Integer.toString(mod.getMarque().getId()));
		params.add("num_categorie", Integer.toString(mod.getCategorie().getId()));
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("modele")
				.type("applimodion/x-www-form-urlencoded")
				.put(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Trouver
	public Modele getModele(int id) throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("modele/"+id)
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		
		ObjectMapper mapper = new ObjectMapper();
		Modele mod = mapper.readValue(jsonAnswer, Modele.class);
		
		return mod;
	}
	
	//Tous
	public List<Modele> list() throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("modele/all")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		System.out.println(jsonAnswer); //
		
		ObjectMapper mapper = new ObjectMapper();
		List<Modele> mod = mapper.readValue(jsonAnswer, new TypeReference<List<Modele>>() {});
		
		return mod;
	}
}
