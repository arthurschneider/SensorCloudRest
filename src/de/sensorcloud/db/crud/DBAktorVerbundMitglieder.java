package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Feldgeraet;
import de.sensorcloud.helpertools.Helper;

public class DBAktorVerbundMitglieder {
	
	public static final String TABNAME = "AktorVerbundMitglieder";
	
	public static ArrayList<String> getAktVerMitAktVerIDByAktID(String aktID) {
		
		ArrayList<String> aktVerMitAktVerID = new ArrayList<String>();
		String CQL = "SELECT AktVerMitAktVerID FROM " +TABNAME + " WHERE AktVerMitAktID = '"+aktID+"'";
	
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktVerMitAktVerID.add(RS.getString("AktVerMitAktVerID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return aktVerMitAktVerID;
		
	}
	
	public static ArrayList<String> getAktIDByAktVerID(String aktVerID){
		
		ArrayList<String> aktIDList = new ArrayList<String>();
		String CQL = "SELECT AktVerMitAktID FROM " + TABNAME + " WHERE AktVerMitAktVerID = '"+aktVerID+"'";
        
		try {
	           
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktIDList.add(RS.getString("AktVerMitAktID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return aktIDList;
		
	}
	
	public static String createAktorVerbundMitglieder(String aktVerMitAktVerID, Feldgeraet aktor) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "AktVerMitID = '" + uuID + "', "
					+ "AktVerMitAktID = '" + aktor.getiD() + "', "
					+ "AktVerMitAktVerID = '" + aktVerMitAktVerID + "' "
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
