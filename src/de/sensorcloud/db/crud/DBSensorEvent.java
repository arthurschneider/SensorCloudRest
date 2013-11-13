package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorEvent;
import de.sensorcloud.helper.Helper;

public class DBSensorEvent {
	
	public static String TABNAME = "SensorEvent";
	
	public static String getSenEveIDBySenEveQueID(String senID) {

		String senEveID = "";
		String CQL = "SELECT SenEveID FROM " + TABNAME + " WHERE SenEveQueID = '"+ senID + "'";
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
	
	
	public static String insertSensorEvent(String senEveID, SensorEvent sensorEvent) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "SenEveID = '" + senEveID + "', "
				+ "SenEveSenID = '" + sensorEvent.getSenEveSenID() + "', "
				+ "SenEvePhyNam = '" + sensorEvent.getSenEvePhyNam() + "', "
				+ "SenEveVop = '" + sensorEvent.getSenEveVop() + "', "
				+ "SenEveWer = '" + sensorEvent.getSenEveWer() + "' "
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
