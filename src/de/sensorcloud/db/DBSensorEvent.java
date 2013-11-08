package de.sensorcloud.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorEvent {
	
	public static String getSenEveIDBySenEveQueID(String senID) {

		String senEveID = "";
		String CQL = "SELECT SenEveID FROM SensorEvent WHERE SenEveQueID = '"+ senID + "'";
		ResultSet RS;
		try {
			
			RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				
				senEveID = RS.getString("SenEveID");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return senEveID;

	}

}
