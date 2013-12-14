package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Event;

public class DBEvent {
	
	public static String TABNAME = "Event";

	public static Event getEventObjByEventID(String eveID) {

		Event event = new Event();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '"+ eveID + "'";

		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				event.setEveArt(RS.getString("EveArt"));
				event.setEveBez(RS.getString("EveBez"));
				event.setEveID(RS.getString("EveID"));
				event.setEveTimSta(RS.getString("EveTimSta"));
				event.setEveNac(RS.getString("EveNac"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return event;
	}
	
	
//	public static String insertEvent(Event event) {
//		String uuID = Helper.generateUUID();
//		String CQL = "UPDATE " + TABNAME + " SET "
//				+ "EveID = '" + uuID + "', "
//				+ "EveArt = '" + event.getEveArt() + "', "
//				+ "EveBez = '" + event.getEveBez() + "', "
//				+ "EveNac = '" + event.getEveNac() + "', "
//				+ "EveTimSta = '" + event.getEveTimSta() + "' "
//				+ "WHERE KEY = " + uuID;
//	
//		try {
//			Cassandra.update(CQL);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return uuID;
//	}
	
	
	public static void updateEvent(Event event) {
		
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "EveID = '" + event.getEveID() + "', "
				+ "EveArt = '" + event.getEveArt() + "', "
				+ "EveBez = '" + event.getEveBez() + "', "
				+ "EveNac = '" + event.getEveNac() + "', "
				+ "EveTimSta = '" + event.getEveTimSta() + "' "
				+ "WHERE KEY = " + event.getEveID();
	
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
