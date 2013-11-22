package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.helpertools.Helper;

public class DBMandantenMitglieder {
	
	public static final String TABNAME = "MandantenMitglieder";

	public static String getManMitNutStaIDByManMitManID(String manID) {
		
		String manMitNutStaID = null;
		String CQL = "SELECT ManMitNutStaID FROM " + TABNAME + " WHERE ManMitManID = '"+manID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	manMitNutStaID = RS.getString("manMitNutStaID");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return manMitNutStaID;
		
	} 
	
	
	public static String insertMitglied(String manID, String manMitNutStaID) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "ManMitID = '" + uuID + "', "
					+ "ManMitManID = '" + manID + "', "
					+ "ManMitNutStaID = '" + manMitNutStaID + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uuID;
	}
}
