package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorServiceFunktion;

public class DBSensorServiceFunktion {
	
public static final String TABNAME = "SensorServiceFunktion";

	
	public static String getSenSerFunNamBySenSerFunID(String senSerFunID) {
		
		String CQL = "SELECT SenSerFunNam FROM " +TABNAME + " WHERE KEY = '"+senSerFunID+"'";
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
	
	public static SensorServiceFunktion getFunktionByID(String senSerFunID) {
		
		String CQL = "SELECT * FROM " +TABNAME + " WHERE KEY = '"+senSerFunID+"'";
		SensorServiceFunktion funktion = new SensorServiceFunktion();
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	funktion.setSenSerFunID(RS.getString("SenSerFunID"));
	        	funktion.setSenSerFunNam(RS.getString("SenSerFunNam"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return funktion;
	}

}
