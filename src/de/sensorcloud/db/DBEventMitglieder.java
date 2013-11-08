package de.sensorcloud.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBEventMitglieder {
	
	public static String getEveMitEveIDByEveMitSenEveID(String senID){

		String eveMitEveID = "";
		String CQL = "SELECT EveMitEveID FROM EventMitglieder WHERE EveMitSenEveID = '"+ senID + "'";
		
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

}
