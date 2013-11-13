package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.helpertools.Helper;

public class DBEventMitglieder {
	
	public static String TABNAME = "EventMitglieder";
	
	public static String getEveMitEveIDByEveMitSenEveID(String senID){

		String eveMitEveID = "";
		String CQL = "SELECT EveMitEveID FROM " + TABNAME + " WHERE EveMitSenEveID = '"+ senID + "'";
		
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				
				eveMitEveID = RS.getString("EveMitEveID");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return eveMitEveID;

	}
	
	public static String insertEventMitglieder(String eveMitEveID, String eveMitSenEveID, int eveMitRei) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "EveMitID = '" + uuID + "', "
				+ "EveMitEveID = '" + eveMitEveID + "', "
				+ "EveMitSenEveID = '" + eveMitSenEveID + "', "
				+ "EveMitRei = '" + eveMitRei + "' "
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
