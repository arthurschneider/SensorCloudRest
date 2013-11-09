package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Event;

public class DBEvent {

	public static Event getEventObjByEventID(String eveID) {

		Event event = new Event();
		
		String CQL = "SELECT * FROM Event WHERE EveID = '"+ eveID + "'";

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
}
