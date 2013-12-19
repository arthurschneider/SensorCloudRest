package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerEmail;
import de.sensorcloud.helpertools.Helper;

public class DBNutzerEmail {
	

	public static final String TABNAME = "NutzerEmail";
	
	public static String getNutEmaNutStaIDbyNutEmaBez(String email) {
		String nutEmaNutStaID = null;
		String CQL = "SELECT NutEmaNutStaID FROM " + TABNAME + " WHERE NutEmaAdr = '"+email+"'";
		try {
		 
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	nutEmaNutStaID = RS.getString("NutEmaNutStaID");
	        }
	                 
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return nutEmaNutStaID;
		
	}
	
	
	public static ArrayList<NutzerEmail> getNutzerEmailByNutStaID(String nutStaID) {
		
		ArrayList<NutzerEmail> nutzerEmailList = new ArrayList<NutzerEmail>();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE NutEmaNutStaID = '"+nutStaID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	NutzerEmail nutzerEmail = new NutzerEmail();
	        	nutzerEmail.setNutEmaID(RS.getString("NutEmaID"));
	        	nutzerEmail.setNutEmaNutStaID(nutStaID);
	        	nutzerEmail.setNutEmaAdr(RS.getString("NutEmaAdr"));
	        	nutzerEmail.setNutEmaBez(RS.getString("NutEmaBez"));
	        	nutzerEmailList.add(nutzerEmail);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return nutzerEmailList;
	}
	
	
	public static void updateNutzerEmail(NutzerEmail nutzerEmail) {

		String CQL = "UPDATE " +TABNAME + " SET "
					+ "NutEmaID = '" + nutzerEmail.getNutEmaID() + "', "
					+ "NutEmaNutStaID = '" + nutzerEmail.getNutEmaNutStaID() + "', "
					+ "NutEmaAdr = '" + nutzerEmail.getNutEmaAdr() + "', "
					+ "NutEmaBez = '" + nutzerEmail.getNutEmaBez() + "' "
					+ "WHERE KEY = " + nutzerEmail.getNutEmaID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static String insertNutzerEmail(NutzerEmail nutzerEmail) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "NutEmaID = '" + uuID + "', "
					+ "NutEmaNutStaID = '" + nutzerEmail.getNutEmaNutStaID() + "', "
					+ "NutEmaAdr = '" + nutzerEmail.getNutEmaAdr() + "', "
					+ "NutEmaBez = '" + nutzerEmail.getNutEmaBez() + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uuID;
	}
	
	public static void deleteNutzerEmail(String nutEmaID) {
		
		String CQL = "DELETE NutEmaNutStaID, NutEmaAdr, NutEmaBez, NutEmaID FROM " +TABNAME + "  WHERE KEY = '"+nutEmaID+"'";
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
