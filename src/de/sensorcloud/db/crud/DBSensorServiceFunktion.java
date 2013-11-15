package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorServiceFunktion {
	
public static final String TABNAME = "SensorServiceFunktion";

	
	public static String getSenSerFunNamBySenSerFunID(String senSerFunID) {
		
		String CQL = "SELECT SenSerFunNam FROM " +TABNAME + " WHERE SenSerFunID = '"+senSerFunID+"'";
		String senSerFunNam = null;
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	senSerFunNam = RS.getString("SenSerID");
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return senSerFunNam;
		
	}

}
