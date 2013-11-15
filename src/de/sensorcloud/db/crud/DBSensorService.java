package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorService;

public class DBSensorService {
	
	public static final String TABNAME = "SensorService";

	
	public static SensorService getSensorServiceBySenSerID(String senSerID) {
		
		String CQL = "SELECT * FROM " +TABNAME + " WHERE SenSerID = '"+senSerID+"'";
		SensorService senServ = new SensorService();
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	senServ.setSenSerID(RS.getString("SenSerID"));
	        	senServ.setSenSerBez(RS.getString("SenSerBez"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return senServ;
		
	}
}
