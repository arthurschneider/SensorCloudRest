package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.AktorVerbund;

public class DBAktorVerbund {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static AktorVerbund getAktVerbBezByAktVerMitAktVerID(String aktVerMitAktVerID) throws SQLException {
		
		AktorVerbund aktVerb = new AktorVerbund();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM AktorVerbund WHERE AktVerID = '"+aktVerMitAktVerID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktVerb.setAktVerBez(RS.getString("AktVerBez"));
	        	aktVerb.setAktVerID(RS.getString("AktVerID"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return aktVerb;
		
	}

}
