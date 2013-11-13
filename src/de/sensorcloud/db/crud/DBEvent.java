package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Event;
import de.sensorcloud.helper.Helper;

public class DBEvent {
	
	public static String TABNAME = "Event";

	public static Event getEventObjByEventID(String eveID) {

		Event event = new Event();
		
		String CQL = "SELECT * FROM " + TABNAME + " WHERE EveID = '"+ eveID + "'";

		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				event.setEveArt(RS.getString("EveArt"));
				event.setEveBez(RS.getString("EveBez"));
				event.setEveID(RS.getString("EveID"));
				event.setEveTimSta(RS.getString("EveTimSta"));
				event.setEveNac(RS.getString("Evenac"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return event;

	}
	
	
	public static String insertEvent(Event event) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "EveID = '" + uuID + "', "
				+ "EveArt = '" + event.getEveArt() + "', "
				+ "EveBez = '" + event.getEveBez() + "', "
				+ "EveNac = '" + event.getEveNac() + "', "
				+ "EveTimSta = '" + event.getEveTimSta() + "' "
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
