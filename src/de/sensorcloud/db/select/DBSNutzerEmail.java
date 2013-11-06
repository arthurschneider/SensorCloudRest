package de.sensorcloud.db.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.NutzerEmail;

public class DBSNutzerEmail {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static ArrayList<String> getNutEmaNutStaIDbyNutEmaBez(String email) throws SQLException {
		ArrayList<String> emailList = new ArrayList<String>();
		 
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT NutEmaNutStaID FROM NutzerEmail WHERE NutEmaAdr = '"+email+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	emailList.add(RS.getString("NutEmaNutStaID"));
	        	
	        }
	                 
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}
		
		return emailList;
		
	}
	
	public static ArrayList<NutzerEmail> getNutzerEmailByNutStaID(String tabelleName, String nutStaID) throws SQLException{
		ArrayList<NutzerEmail> nutzerEmailList = new ArrayList<NutzerEmail>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM '"+tabelleName+"' WHERE NutEmaNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
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
		} finally{
			con.close();
		}

		return nutzerEmailList;
	}

}
