package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.Sensor;

public class DBSensor {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static ArrayList<Sensor> getSensorByNutStaID(String nutStaID) throws SQLException {
		
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM Sensor WHERE SenNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	Sensor sensor = new Sensor();
	        	sensor.setSenID(RS.getString("KEY"));
	        	sensor.setSenNutStaID(nutStaID);
	        	sensor.setSenSenProID(RS.getString("SenSenProID"));
	        	sensor.setSenSenTypID(RS.getString("SenSenTypID"));
	        	sensor.setSenRauID(RS.getString("SenRauID"));
	        	sensor.setSenLocMasID(RS.getString("SenLocMasID"));
	        	sensor.setSenSouID(RS.getString("SenSouID"));
	        	sensor.setSenBez(RS.getString("SenBez"));
	        	sensor.setSenPos(RS.getString("SenPos"));
	        	sensor.setSenDatEin(RS.getString("SenDatEin"));
	        	sensorList.add(sensor);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return sensorList;
		
	} 
	
public static Sensor getSensorBySenID(String senID) throws SQLException {
		
		Sensor sensor = new Sensor();
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM Sensor WHERE SenID = '"+senID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	sensor.setSenID(senID);
	        	sensor.setSenNutStaID(RS.getString("SenNutStaID"));
	        	sensor.setSenSenProID(RS.getString("SenSenProID"));
	        	sensor.setSenSenTypID(RS.getString("SenSenTypID"));
	        	sensor.setSenRauID(RS.getString("SenRauID"));
	        	sensor.setSenLocMasID(RS.getString("SenLocMasID"));
	        	sensor.setSenSouID(RS.getString("SenSouID"));
	        	sensor.setSenBez(RS.getString("SenBez"));
	        	sensor.setSenPos(RS.getString("SenPos"));
	        	sensor.setSenDatEin(RS.getString("SenDatEin"));
	        
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return sensor;
		
	} 

}
