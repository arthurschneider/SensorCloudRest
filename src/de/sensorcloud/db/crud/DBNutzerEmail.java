package de.sensorcloud.db.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerEmail;

public class DBNutzerEmail {
	
	static Connection con;
	
	public static ArrayList<String> getNutEmaNutStaIDbyNutEmaBez(String email) {
		
		ArrayList<String> emailList = new ArrayList<String>();
		String CQL = "SELECT NutEmaNutStaID FROM NutzerEmail WHERE NutEmaAdr = '"+email+"'";
		
		try {
		 
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	emailList.add(RS.getString("NutEmaNutStaID"));
	        }
	                 
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		return emailList;
		
	}
	
	
	public static ArrayList<NutzerEmail> getNutzerEmailByNutStaID(String nutStaID) {
		
		ArrayList<NutzerEmail> nutzerEmailList = new ArrayList<NutzerEmail>();
		String CQL = "SELECT * FROM NutzerEmail WHERE NutEmaNutStaID = '"+nutStaID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	NutzerEmail nutzerEmail = new NutzerEmail();
	        	nutzerEmail.setNutEmaID(RS.getString("KEY"));
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

		String CQL = "UPDATE NutzerEmail SET "
					+ "NutEmaID = '" + nutzerEmail.getNutEmaID() + "', "
					+ "NutEmaNutStaID = '" + nutzerEmail.getNutEmaNutStaID() + "', "
					+ "NutEmaAdr = '" + nutzerEmail.getNutEmaAdr() + "', "
					+ "NutEmaBez = '" + nutzerEmail.getNutEmaBez() + "' "
					+ "WHERE KEY = " + nutzerEmail.getNutEmaID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
