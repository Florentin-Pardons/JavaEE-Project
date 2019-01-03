package dao;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class VTConnection 
{
	public VTConnection() {}
	
	public static WebResource accessToAPI() 
	{
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(getBaseURI());
		return webResource;
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9090/API_REST_Voiture_Projet/rest").build();
	}
}