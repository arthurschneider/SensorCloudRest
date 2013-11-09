package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorVerbund;

public class DBSensorVerbund {
	
	public static SensorVerbund getSenVerbBezBySenVerMitSenVerID(String senVerMitSenVerID) {
		
		SensorVerbund senVerb = new SensorVerbund();
		String CQL = "SELECT * FROM SensorVerbund WHERE SenVerID = '"+senVerMitSenVerID+"'";
		
		try { 
	      
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	senVerb.setSenVerBez(RS.getString("SenVerBez"));
	        	senVerb.setSenVerID(RS.getString("SenVerID"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return senVerb;
		
	}

}
