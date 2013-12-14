package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Feldgeraet;
import de.sensorcloud.helpertools.Helper;

public class DBSensorVerbundMitglieder {
	
	public static final String TABNAME = "SensorVerbundMitglieder";
	
	public static ArrayList<String> getSenVerMitSenVerIDBySenID(String senID) {
		
		ArrayList<String> senVerMitSenVerID = new ArrayList<String>();
		String CQL = "SELECT SenVerMitSenVerID FROM " + TABNAME + " WHERE SenVerMitSenID = '"+senID+"'";
		
		try {
		      
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	senVerMitSenVerID.add(RS.getString("SenVerMitSenVerID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return senVerMitSenVerID;
		
	}
	
	public static ArrayList<String> getSenIDBySenVerID(String senVerID) {
		
		ArrayList<String> senIDList = new ArrayList<String>();
		String CQL = "SELECT SenVerMitSenID FROM SensorVerbundMitglieder WHERE SenVerMitSenVerID = '"+senVerID+"'";
		
		try {
		      
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	senIDList.add(RS.getString("SenVerMitSenID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return senIDList;
		
	}
	
	
	public static String createSensorVerbundMitglieder(String senVerMitSenVerID, Feldgeraet sensor) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "SenVerMitID = '" + uuID + "', "
					+ "SenVerMitSenID = '" + sensor.getiD() + "', "
					+ "SenVerMitSenVerID = '" + senVerMitSenVerID + "' "
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
