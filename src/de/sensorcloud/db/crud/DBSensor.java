package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Feldgeraet;

public class DBSensor {
	
	public static final String TABNAME = "Sensor";
	
	public static ArrayList<Feldgeraet> getSensorListByNutStaID(String nutStaID){
		
		ArrayList<Feldgeraet> sensorList = new ArrayList<Feldgeraet>();
		String CQL = "SELECT * FROM " +TABNAME + " WHERE SenNutStaID = '"+nutStaID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	Feldgeraet sensor = new Feldgeraet();
	        	sensor.setiD(RS.getString("KEY"));
	        	sensor.setNutStaID(nutStaID);
	        	sensor.setProID(RS.getString("SenSenProID"));
	        	sensor.setTypID(RS.getString("SenSenTypID"));
	        	sensor.setRauID(DBRaum.getRauBezByRauID(RS.getString("SenRauID")));
	        	sensor.setLocMasID(RS.getString("SenLocMasID"));
	        	sensor.setSouID(RS.getString("SenSouID"));
	        	sensor.setBez(RS.getString("SenBez"));
	        	sensor.setPos(RS.getString("SenPos"));
	        	sensor.setDatEin(RS.getString("SenDatEin"));
	        	sensorList.add(sensor);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return sensorList;
		
	} 
	
	public static Feldgeraet getSensorBySenID(String senID) {
		
		Feldgeraet sensor = new Feldgeraet();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE SenID = '"+senID+"'";
		
		try {
			
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	
	        	sensor.setiD(senID);
	        	sensor.setNutStaID(RS.getString("SenNutStaID"));
	        	sensor.setProID(RS.getString("SenSenProID"));
	        	sensor.setTypID(RS.getString("SenSenTypID"));
	        	sensor.setRauID(RS.getString("SenRauID"));
	        	sensor.setLocMasID(RS.getString("SenLocMasID"));
	        	sensor.setSouID(RS.getString("SenSouID"));
	        	sensor.setBez(RS.getString("SenBez"));
	        	sensor.setPos(RS.getString("SenPos"));
	        	sensor.setDatEin(RS.getString("SenDatEin"));
	        
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
