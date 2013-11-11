package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.MesswertTime;
import de.sensorcloud.helper.Helper;

public class DBMesswert {
	
	private final static String TABNAME =  "Messwert3";
	
	
	public static ArrayList<MesswertTime> getMesswertByMesWerIDAndMesWerNam(String keysWithSemicolon, String mesWerNam) {
		
		ArrayList<MesswertTime> wertTimeList = new ArrayList<MesswertTime>();
		String in = Helper.replaceSemikolon(keysWithSemicolon);
		
		//String CQL = "SELECT MesWerWer, MesWerTimSta FROM '"+TABNAME+"' WHERE MesWerNam = '"+mesWerNam+"' AND KEY IN ('"+in+"')";
		String CQL = "SELECT MesWerWer, MesWerTimSta FROM '"+TABNAME+"' WHERE KEY IN ('"+in+"')";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	MesswertTime wertTime = new MesswertTime();
	        	wertTime.setMesWerWer(RS.getString("MesWerWer"));
	        	wertTime.setMesWerTimSta(RS.getString("MesWerTimSta"));
	        	wertTimeList.add(wertTime);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		
		return wertTimeList;
		
	} 

}
