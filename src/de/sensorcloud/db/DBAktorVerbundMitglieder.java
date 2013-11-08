package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;

public class DBAktorVerbundMitglieder {
	
	public static ArrayList<String> getAktVerMitAktVerIDByAktID(String aktID) {
		
		ArrayList<String> aktVerMitAktVerID = new ArrayList<String>();
		String CQL = "SELECT AktVerMitAktVerID FROM AktorVerbundMitglieder WHERE AktVerMitAktID = '"+aktID+"'";
	
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktVerMitAktVerID.add(RS.getString("AktVerMitAktVerID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return aktVerMitAktVerID;
		
	}
	
	public static ArrayList<String> getAktIDByAktVerID(String aktVerID){
		
		ArrayList<String> aktIDList = new ArrayList<String>();
		String CQL = "SELECT AktVerMitAktID FROM AktorVerbundMitglieder WHERE AktVerMitAktVerID = '"+aktVerID+"'";
        
		try {
	           
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktIDList.add(RS.getString("AktVerMitAktID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return aktIDList;
		
	}

}
