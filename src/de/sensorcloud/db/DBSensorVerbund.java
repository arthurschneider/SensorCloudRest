package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.SensorVerbund;

public class DBSensorVerbund {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static SensorVerbund getSenVerbBezBySenVerMitSenVerID(String senVerMitSenVerID) throws SQLException {
		
		SensorVerbund senVerb = new SensorVerbund();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM SensorVerbund WHERE SenVerID = '"+senVerMitSenVerID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	senVerb.setSenVerBez(RS.getString("SenVerBez"));
	        	senVerb.setSenVerID(RS.getString("SenVerID"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return senVerb;
		
	}

}
