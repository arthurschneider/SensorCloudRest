package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Sensor;

public class DBSensor {
	
	public static final String TABNAME = "Sensor";
	
	public static ArrayList<Sensor> getSensorListByNutStaID(String nutStaID){
		
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		String CQL = "SELECT * FROM " +TABNAME + " WHERE SenNutStaID = '"+nutStaID+"'";
		
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
		String CQL = "SELECT * FROM " + TABNAME + " WHERE SenID = '"+senID+"'";
		
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
		String CQL = "SELECT SenSenProID FROM " + TABNAME + " WHERE SenID = '"+ senID + "'";
		ResultSet RS;
		
		try {
			
			RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				
				senSenProID = RS.getString("SenSenProID");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return senSenProID;

	}
	
	public static ArrayList<String> getSensorIDListByNutStaID(String senID) {
		ArrayList<String> senIDLIst = new ArrayList<String>();
		String CQL = "SELECT SenID FROM " + TABNAME + " WHERE SenNutStaID = '"+ senID + "'";
		
		try {
			ResultSet RS = Cassandra.select(CQL);
			while (RS.next()) {
				
				senIDLIst.add(RS.getString("SenID"));
				System.out.println("SenIDByNutStaID : "+RS.getString("SenID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return senIDLIst;

	}

}
