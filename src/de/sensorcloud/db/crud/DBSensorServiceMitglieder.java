package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorServiceMitglieder {
	
	public static final String TABNAME = "SensorServiceMitglieder";
	
	public static ArrayList<String> getServiceIDBySenID(String senID) {
		
		ArrayList<String> id = new ArrayList<String>();
		String CQL = "SELECT SenSerMitSenSerID FROM " + TABNAME + " WHERE SenSerMitSenID = '"+ senID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				id.add(RS.getString("SenSerMitSenSerID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static ArrayList<String> getServiceIDBySenSerID(String senID) {
		
		ArrayList<String> id = new ArrayList<String>();
		String CQL = "SELECT SenSerMitSenID FROM " + TABNAME + " WHERE SenSerMitSenSerID = '"+ senID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				id.add(RS.getString("SenSerMitSenID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
