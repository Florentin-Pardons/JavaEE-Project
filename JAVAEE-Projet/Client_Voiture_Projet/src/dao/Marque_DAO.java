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

import javabean.Marque;

public class Marque_DAO {
	
	//Creer
	public boolean create(Marque mar) throws JsonParseException, JsonMappingException, IOException
	{		
		DateFormat dateform = new SimpleDateFormat("dd/MM/yyyy");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		//params.add("id", Integer.toString(mar.getId()));
		params.add("nom", mar.getNom());
		params.add("datecrea", dateform.format(mar.getDateCrea()));
		params.add("paysorigine", mar.getPaysOrigine());
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("marque")
				.type("applimarion/x-www-form-urlencoded")
				.post(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Delete
	public boolean delete(Marque mar) throws JsonParseException, JsonMappingException, IOException
	{		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("marque/"+mar.getId())
				.delete(ClientResponse .class);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Update
	public boolean update(Marque mar) throws JsonParseException, JsonMappingException, IOException
	{		
		DateFormat dateform = new SimpleDateFormat("MM/dd/yyyy");
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("id", Integer.toString(mar.getId()));
		params.add("nom", mar.getNom());
		params.add("datecrea", dateform.format(mar.getDateCrea()));
		params.add("paysorigine", mar.getPaysOrigine());
		
		ClientResponse jsonAnswer = VTConnection.accessToAPI()
				.path("marque")
				.type("applimarion/x-www-form-urlencoded")
				.put(ClientResponse.class, params);
		System.out.println(jsonAnswer); //
		
		if(jsonAnswer.getStatus() == 200) //update ok
			return true;
		
		return false;
	}
	
	//Trouver
	public Marque getMarque(int id) throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("marque/"+id)
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		
		ObjectMapper mapper = new ObjectMapper();
		Marque mar = mapper.readValue(jsonAnswer, Marque.class);
		
		return mar;
	}
	
	//Tous
	public List<Marque> list() throws JsonParseException, JsonMappingException, IOException
	{		
		String jsonAnswer = VTConnection.accessToAPI()
				.path("marque/all")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		System.out.println(jsonAnswer); //
		
		jsonAnswer = jsonAnswer.replaceAll("\\[UTC]","");
		System.out.println(jsonAnswer); //
		
		ObjectMapper mapper = new ObjectMapper();
		List<Marque> mar = mapper.readValue(jsonAnswer, new TypeReference<List<Marque>>() {});
		
		return mar;
	}
}