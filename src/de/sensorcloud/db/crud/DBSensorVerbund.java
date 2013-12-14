package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.FeldgeraetVerbund;
import de.sensorcloud.helpertools.Helper;

public class DBSensorVerbund {
	public static final String TABNAME = "SensorVerbund";
	
	public static FeldgeraetVerbund getSenVerbBezBySenVerMitSenVerID(String senVerMitSenVerID) {
		FeldgeraetVerbund senVerb = new FeldgeraetVerbund();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '"+senVerMitSenVerID+"'";
		
		try { 
	      
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	System.out.println("VerbundId : "+RS.getString("SenVerID") );
	        	senVerb.setVerBez(RS.getString("SenVerBez"));
	        	senVerb.setVerID(RS.getString("SenVerID"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return senVerb;
		
	}
	
	
	public static String createSensorVerbund(FeldgeraetVerbund sensorVerbund) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "senVerID = '" + uuID + "', "
					+ "AktSenBez = '" + sensorVerbund.getVerBez() + "' "
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
