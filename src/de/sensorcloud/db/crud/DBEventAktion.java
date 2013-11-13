package de.sensorcloud.db.crud;

import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.EventAktion;
import de.sensorcloud.helper.Helper;

public class DBEventAktion {
	
	public static String TABNAME = "EventAktion";
	
	public static String insertEventAktion(String eveID, EventAktion eventAktion) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
				+ "EveAktiID = '" + uuID + "', "
				+ "EveAktiEveID = '" + eveID + "', "
				+ "EveAktiBez = '" + eventAktion.getEveAktiBez() + "', "
				+ "EveAktiZie = '" + eventAktion.getEveAktiZie() + "', "
				+ "EveAktiZieID = '" + eventAktion.getEveAktiZieID() + "', "
				+ "EveAktiZiePar = '" + eventAktion.getEveAktiZiePar() + "', "
				+ "EveAktiZieWer = '" + eventAktion.getEveAktiZieWer() + "', "
				+ "EveAktiZiePrio = '" + eventAktion.getEveAktiZiePrio() + "' "
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
