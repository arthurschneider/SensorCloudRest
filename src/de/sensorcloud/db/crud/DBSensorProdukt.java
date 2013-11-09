package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorProdukt {
	
	public static String getSensorSemantikBySenProID(String senProID) {

		String senProSem = "";
		String CQL = "SELECT SenProSem FROM SensorProdukt WHERE SenProID = '"+ senProID + "'";

		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {

				senProSem = RS.getString("SenProSem");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return senProSem;

	}

}
