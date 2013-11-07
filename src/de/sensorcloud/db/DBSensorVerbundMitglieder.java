package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.Sensor;

public class DBSensorVerbundMitglieder {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static ArrayList<String> getSenVerMitSenVerIDBySenID(String senID) throws SQLException {
		
		ArrayList<String> senVerMitSenVerID = new ArrayList<String>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT SenVerMitSenVerID FROM SensorVerbundMitglieder WHERE SenVerMitSenID = '"+senID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	senVerMitSenVerID.add(RS.getString("SenVerMitSenVerID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return senVerMitSenVerID;
		
	}
	
	public static ArrayList<String> getSenIDBySenVerID(String senVerID) throws SQLException {
		
		ArrayList<String> senIDList = new ArrayList<String>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT SenVerMitSenID FROM SensorVerbundMitglieder WHERE SenVerMitSenVerID = '"+senVerID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	senIDList.add(RS.getString("SenVerMitSenID"));
	        	
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return senIDList;
		
	}

}
