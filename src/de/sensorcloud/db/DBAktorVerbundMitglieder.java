package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;

public class DBAktorVerbundMitglieder {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static ArrayList<String> getAktVerMitAktVerIDByAktID(String aktID) throws SQLException {
		
		ArrayList<String> aktVerMitAktVerID = new ArrayList<String>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT AktVerMitAktVerID FROM AktorVerbundMitglieder WHERE AktVerMitAktID = '"+aktID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktVerMitAktVerID.add(RS.getString("AktVerMitAktVerID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return aktVerMitAktVerID;
		
	}
	
	public static ArrayList<String> getAktIDByAktVerID(String aktVerID) throws SQLException {
		
		ArrayList<String> aktIDList = new ArrayList<String>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT AktVerMitAktID FROM AktorVerbundMitglieder WHERE AktVerMitAktVerID = '"+aktVerID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	aktIDList.add(RS.getString("AktVerMitAktID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return aktIDList;
		
	}

}
