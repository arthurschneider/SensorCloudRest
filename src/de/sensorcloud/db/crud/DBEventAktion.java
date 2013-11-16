package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.EventAktion;
import de.sensorcloud.helpertools.Helper;

public class DBEventAktion {
	
	public static String TABNAME = "EventAktion";
	
	public static ArrayList<EventAktion> getEventAktionByEveID(String eveID){
		
		ArrayList<EventAktion> eveAktionList = new ArrayList<EventAktion>();
		String CQL = "SELECT EveAktiID, EveAktiBez, EveAktiEveID, EveAktiZie, EveAktiZieID, EveAktiZiePar, EveAktiZieWer, EveAktiZiePri  FROM " + TABNAME + " WHERE EveAktiEveID = '"+eveID+"'";
		
		try {
		 
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	EventAktion eveAkt = new EventAktion();
	        	eveAkt.setEveAktiID(RS.getString("EveAktiID"));
	        	eveAkt.setEveAktiBez(RS.getString("EveAktiBez"));
	        	eveAkt.setEveAktiEveID(RS.getString("EveAktiEveID"));
	        	eveAkt.setEveAktiZie(RS.getString("EveAktiZie"));
	        	eveAkt.setEveAktiZieID(RS.getString("EveAktiZieID"));
	        	eveAkt.setEveAktiZiePar(RS.getString("EveAktiZiePar"));
	        	eveAkt.setEveAktiZieWer(RS.getString("EveAktiZieWer"));
	        	eveAkt.setEveAktiZiePrio(RS.getString("EveAktiZiePri"));
	        	eveAktionList.add(eveAkt);
	        }           
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return eveAktionList;
	}
	
	
	
	public static String insertEventAktion(String eveID, EventAktion eventAktion) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE '" + TABNAME + "' SET "
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
			e.printStackTrace();
		}
		
		return uuID;
	}
	
	
	public static void updateEventAktion(EventAktion eventAktion) {
		
		String CQL = "UPDATE '" + TABNAME + "' SET "
				+ "EveAktiID = '" + eventAktion.getEveAktiID() + "', "
				+ "EveAktiEveID = '" + eventAktion.getEveAktiEveID() + "', "
				+ "EveAktiBez = '" + eventAktion.getEveAktiBez() + "', "
				+ "EveAktiZie = '" + eventAktion.getEveAktiZie() + "', "
				+ "EveAktiZieID = '" + eventAktion.getEveAktiZieID() + "', "
				+ "EveAktiZiePar = '" + eventAktion.getEveAktiZiePar() + "', "
				+ "EveAktiZieWer = '" + eventAktion.getEveAktiZieWer() + "', "
				+ "EveAktiZiePrio = '" + eventAktion.getEveAktiZiePrio() + "' "
				+ "WHERE KEY = " + eventAktion.getEveAktiID();
	
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

