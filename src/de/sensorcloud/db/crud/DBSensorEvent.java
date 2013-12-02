package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorEvent;
import de.sensorcloud.helpertools.Helper;

public class DBSensorEvent {
	
	public static String TABNAME = "SensorEvent";
	
	public static String getSenEveIDBySenEveQueID(String senID) {

		String senEveID = null;
		String CQL = "SELECT SenEveID FROM " + TABNAME + " WHERE SenEveQueID = '"+ senID + "'";
		
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				senEveID = RS.getString("SenEveID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return senEveID;
	}
	
	
	
	public static SensorEvent getSensorEventBySenEveID(String senEveID) {

		SensorEvent sensorEvent  = new SensorEvent();  
		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '"+ senEveID + "'";
		
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				sensorEvent.setSenEveID(RS.getString("SenEveID"));
				sensorEvent.setSenEveQueID(RS.getString("SenEveQueID"));
				sensorEvent.setSenEveQue(RS.getString("SenEveQue"));
				sensorEvent.setSenEvePhyNam(RS.getString("SenEvePhyNam"));
				sensorEvent.setSenEveVop(RS.getString("SenEveVop"));
				sensorEvent.setSenEveWer(RS.getString("SenEveWer"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sensorEvent;
	}
	
	
	public static String insertSensorEvent(String senEveID, SensorEvent sensorEvent) {

		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "SenEveID = '" + senEveID + "', "
				+ "SenEveQueID = '" + sensorEvent.getSenEveQueID() + "', "
				+ "SenEveQue = '" + sensorEvent.getSenEveQue() + "', "
				+ "SenEvePhyNam = '" + sensorEvent.getSenEvePhyNam() + "', "
				+ "SenEveVop = '" + sensorEvent.getSenEveVop() + "', "
				+ "SenEveWer = '" + sensorEvent.getSenEveWer() + "' "
				+ "WHERE KEY = " + uuID;
	
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uuID;
	}
	
	public static void updateSensorEvent(SensorEvent sensorEvent) {
	
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "SenEveID = '" + sensorEvent.getSenEveID() + "', "
				+ "SenEveQueID = '" + sensorEvent.getSenEveQueID() + "', "
				+ "SenEveQue = '" + sensorEvent.getSenEveQue() + "', "
				+ "SenEvePhyNam = '" + sensorEvent.getSenEvePhyNam() + "', "
				+ "SenEveVop = '" + sensorEvent.getSenEveVop() + "', "
				+ "SenEveWer = '" + sensorEvent.getSenEveWer() + "' "
				+ "WHERE KEY = " + sensorEvent.getSenEveID();
	
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
