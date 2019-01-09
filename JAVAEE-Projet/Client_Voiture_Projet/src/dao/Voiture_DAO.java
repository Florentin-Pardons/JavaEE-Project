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

import javabean.Voiture;

public class Voiture_DAO {
	
	//Creer
	public boolean create(Voiture voi) throws JsonParseException, JsonMappingException, IOException
	{		
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("num_modele", Integer.toString(voi.getModele().getId()));
		params.add("couleur", voi.getCouleur());
		params.add("carburant", voi.getCarburant());
		params.add("boitevitesse", voi.getBoiteVitesse());
		params.add("kilometrage", Integer.toString(voi.getNbkm()));
		params.add("age", Integer.toString(voi.getAge()));
		params.add("num_pers", Integer.toString(voi.getUtilisateur().getId()));
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("voiture")
				.type("application/x-www-form-urlencoded")
				.post(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Delete
	public boolean delete(Voiture voi) throws JsonParseException, JsonMappingException, IOException
	{		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("voiture")
				.queryParam("id", Integer.toString(voi.getId()))
				.delete(ClientResponse.class);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Update
	public boolean update(Voiture voi) throws JsonParseException, JsonMappingException, IOException
	{		
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("id", Integer.toString(voi.getId()));
		params.add("num_modele", Integer.toString(voi.getModele().getId()));
		params.add("couleur", voi.getCouleur());
		params.add("carburant", voi.getCarburant());
		params.add("boitevitesse", voi.getBoiteVitesse());
		params.add("kilometrage", Integer.toString(voi.getNbkm()));
		params.add("age", Integer.toString(voi.getAge()));
		params.add("num_pers", Integer.toString(voi.getUtilisateur().getId()));
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("voiture")
				.type("application/x-www-form-urlencoded")
				.put(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Trouver
	public Voiture getVoiture(int id) throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("voiture")
				.queryParam("id", Integer.toString(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		
		ObjectMapper mapper = new ObjectMapper();
		Voiture voi = mapper.readValue(jsonAnswer, Voiture.class);
		
		return voi;
	}
	
	//Tous
	public List<Voiture> list() throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("voiture/all")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		System.out.println(jsonAnswer); //
		
		ObjectMapper mapper = new ObjectMapper();
		List<Voiture> voi = mapper.readValue(jsonAnswer, new TypeReference<List<Voiture>>() {});
		
		return voi;
	}
}