package api_rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnexion {

private static Connection instance = null;
	
	private OracleConnexion(){
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			String url = "jdbc:oracle:thin:@//char-oracle11.condorcet.be:1521/xe";
			//"jdbc:oracle:thin:@//char-oracle11.condorcet.be:1521/xe","florentin3","Florentin"
			instance = DriverManager.getConnection(url,"florentin3","Florentin");			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		if (instance == null) {
            System.out.println("La base de données est inaccessible");
        }
	}
	
	public static Connection getInstance() {
		if(instance == null){
			new OracleConnexion();
		}
		return instance;
	}
}
