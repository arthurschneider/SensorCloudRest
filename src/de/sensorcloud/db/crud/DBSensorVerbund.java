package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorVerbund;
import de.sensorcloud.helpertools.Helper;

public class DBSensorVerbund {
	public static final String TABNAME = "SensorVerbund";
	
	public static SensorVerbund getSenVerbBezBySenVerMitSenVerID(String senVerMitSenVerID) {
		
		SensorVerbund senVerb = new SensorVerbund();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE SenVerID = '"+senVerMitSenVerID+"'";
		
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
	
	
	public static String createSensorVerbund(SensorVerbund sensorVerbund) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "senVerID = '" + uuID + "', "
					+ "AktSenBez = '" + sensorVerbund.getSenVerBez() + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uuID;
	}

}
