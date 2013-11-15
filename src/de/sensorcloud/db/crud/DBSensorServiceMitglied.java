package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorServiceMitglied {
	
	public static final String TABNAME = "SensorServiceMitglied";
	
	public static String getSenSerMitSenSerIDBySenSerMitSenID(String senID) {
		
		String CQL = "SELECT SenSerMItSenSerID FROM " +TABNAME + " WHERE SenSerMitSenID = '"+senID+"'";
		String SenSerMitSenSerID = null;
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	SenSerMitSenSerID = RS.getString("AktVerMitAktVerID");
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return SenSerMitSenSerID;
		
	}
	

}
