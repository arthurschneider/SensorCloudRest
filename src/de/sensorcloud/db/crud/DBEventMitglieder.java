package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			e.printStackTrace();
		}
		
		return eveMitEveID;

	}
	
	
	
	public static ArrayList<String> getSensorEventListByEveID(String eveID){

		ArrayList<String> senEveList = new ArrayList<String>();
		String CQL = "SELECT EveMitSenEveID FROM " + TABNAME + " WHERE EveMitEveID = '"+ eveID + "'";
		
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				senEveList.add(RS.getString("EveMitSenEveID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return senEveList;

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
