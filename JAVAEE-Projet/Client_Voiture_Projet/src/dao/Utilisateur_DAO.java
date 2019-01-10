package dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javabean.Utilisateur;

public class Utilisateur_DAO {
	
	//Creer
	public boolean create(Utilisateur user) throws JsonParseException, JsonMappingException, IOException
	{		
		DateFormat dateform = new SimpleDateFormat("dd/MM/yyyy");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("mail", user.getMail());
		params.add("motdepasse", user.getMp());
		params.add("nom", user.getNom());
		params.add("prenom", user.getPrenom());
		params.add("datenaissance", dateform.format(user.getDateNaissance()));
		params.add("adresse", user.getAdresse());
		params.add("statut", user.isRole()!=false?"1":"0");
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("utilisateur")
				.type("application/x-www-form-urlencoded")
				.post(ClientResponse.class, params);
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Delete
	public boolean delete(Utilisateur user) throws JsonParseException, JsonMappingException, IOException
	{		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("utilisateur")
				.queryParam("id", Integer.toString(user.getId()))
				.delete(ClientResponse .class);
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Update
	public boolean update(Utilisateur user) throws JsonParseException, JsonMappingException, IOException
	{		
		DateFormat dateform = new SimpleDateFormat("MM/dd/yyyy");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("id", Integer.toString(user.getId()));
		params.add("mail", user.getMail());
		params.add("motdepasse", user.getMp());
		params.add("nom", user.getNom());
		params.add("prenom", user.getPrenom());
		params.add("datenaissance", dateform.format(user.getDateNaissance()));
		params.add("adresse", user.getAdresse());
		params.add("statut", user.isRole()!=false?"1":"0");
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("utilisateur")
				.type("application/x-www-form-urlencoded")
				.put(ClientResponse.class, params);
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Trouver
	public Utilisateur getUtilisateur(int id) throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("utilisateur")
				.queryParam("id", Integer.toString(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		
		ObjectMapper mapper = new ObjectMapper();
		Utilisateur user = mapper.readValue(jsonAnswer, Utilisateur.class);
		
		return user;
	}
	
	//Tous
	public List<Utilisateur> list() throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("utilisateur/all")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		
		ObjectMapper mapper = new ObjectMapper();
		List<Utilisateur> user = mapper.readValue(jsonAnswer, new TypeReference<List<Utilisateur>>() {});
		
		return user;
	}
}