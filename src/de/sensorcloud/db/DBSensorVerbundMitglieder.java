package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Sensor;

public class DBSensorVerbundMitglieder {
	
	
	public static ArrayList<String> getSenVerMitSenVerIDBySenID(String senID) {
		
		ArrayList<String> senVerMitSenVerID = new ArrayList<String>();
		String CQL = "SELECT SenVerMitSenVerID FROM SensorVerbundMitglieder WHERE SenVerMitSenID = '"+senID+"'";
		
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

}
