package de.sensorcloud.db.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.sensorcloud.db.connection.Verbindung;

public class DBSNutzerSicherheit {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	
	public static String getNutSicPasbyNutStaID(String nutStaID) throws SQLException {
	
		String nutSicPas = "";
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT NutSicPas FROM NutzerSicherheit WHERE NutSicNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        
	        	nutSicPas = RS.getString("NutSicPas");
	        
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return nutSicPas;
		
	}

}
