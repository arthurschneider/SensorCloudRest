package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Sensor;

public class DBSensor {
	
	static Cassandra verb = new Cassandra();
	static Connection con;
	
	public static ArrayList<Sensor> getSensorListByNutStaID(String nutStaID){
		
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		String CQL = "SELECT * FROM Sensor WHERE SenNutStaID = '"+nutStaID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
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
		}

		return sensorList;
		
	} 
	
	public static Sensor getSensorBySenID(String senID) {
		
		Sensor sensor = new Sensor();
		String CQL = "SELECT * FROM Sensor WHERE SenID = '"+senID+"'";
		
		try {
		      
			
	        
	           
	        ResultSet RS   = Cassandra.select(CQL);
	       
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
		} 

		return sensor;
		
	} 


	public static String getSenProIDBySenID(String senID) {

		String senSenProID = "";
		String CQL = "SELECT SenSenProID FROM Sensor WHERE SenID = '"+ senID + "'";
		ResultSet RS;
		
		try {
			
			RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				
				senSenProID = RS.getString("SenSenProID");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return senSenProID;

	}
	
	public static HashSet<String> getSensorIDListByNutStaID(String senID) {
		HashSet<String> senIDLIst = new HashSet<String>();
		String CQL = "SELECT SenID FROM Sensor WHERE SenNutStaID = '"+ senID + "'";
		
		try {
			ResultSet RS = Cassandra.select(CQL);
			while (RS.next()) {
				
				senIDLIst.add(RS.getString("SenID"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return senIDLIst;

	}

}
