package de.sensorcloud.db.crud;

import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.EventBenachrichtigung;
import de.sensorcloud.helpertools.Helper;

public class DBEventBenachrichtigung {
	
	public static String TABNAME = "EventBenachrichtigung";
	
	public static String insertEventBenachrichtigung(String eveID , EventBenachrichtigung eventBen) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "EveBenID = '" + uuID + "', "
					+ "EveBenEveID = '" + eveID + "', "
					+ "EveBenWeg = '" + eventBen.getEveBenWeg() + "' "
					+ "WHERE KEY = " + uuID;
	
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uuID;
	}
	
	
	public static void updateEventBenachrichtigung(EventBenachrichtigung eventBen) {
		
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "EveBenID = '" + eventBen.getEveBenID() + "', "
					+ "EveBenEveID = '" + eventBen.getEveBenEveID() + "', "
					+ "EveBenWeg = '" + eventBen.getEveBenWeg() + "' "
					+ "WHERE KEY = " + eventBen.getEveBenID();
	
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
