package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.AktorServiceFunktion;

public class DBAktorServiceFunktion {
	
	public static final String TABNAME = "AktorServiceFunktion";
	
	public static AktorServiceFunktion getFunktionByID(String aktSerFunID) {
		
		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '" + aktSerFunID + "'";
		AktorServiceFunktion funktion = new AktorServiceFunktion();
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	funktion.setAktSerFunID(RS.getString("AktSerFunID"));
	        	funktion.setAktSerFunNam(RS.getString("AktSerFunNam"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return funktion;
	}

}
