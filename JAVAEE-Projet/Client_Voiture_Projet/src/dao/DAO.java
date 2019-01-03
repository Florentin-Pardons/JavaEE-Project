package dao;

import java.util.ArrayList;

import com.sun.jersey.api.client.WebResource;

public abstract class DAO<T> 
{
	protected WebResource webResource = null;
	
	public DAO(WebResource webResource) {
		this.webResource = webResource;
	}
	
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T select(int id);
	
	public abstract ArrayList<T> selectAll();
	
}
