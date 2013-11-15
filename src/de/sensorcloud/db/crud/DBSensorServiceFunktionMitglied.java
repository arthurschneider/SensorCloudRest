package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorServiceFunktionMitglied {
	
	public static final String TABNAME = "SensorServiceFunktionMitglied";

	
	public static String getSenSerFunMitSenSerFunIDBySenSerFunMitSenSerID(String senSerID) {
		
		String CQL = "SELECT SenSerFunMitSenSerFunID FROM " +TABNAME + " WHERE SenSerFunMitSenSerID = '"+senSerID+"'";
		String senSerFunMitSenSerFunID = null;
		
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	senSerFunMitSenSerFunID =  RS.getString("SenSerFunMitSenSerFunID");
	        	
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return senSerFunMitSenSerFunID;
		
	}


}
