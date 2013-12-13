package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBRaum {
	
public static final String TABNAME = "Raum";
	
	public static String getRauBezByRauID(String rauID){
		String raumBez = null;
		String CQL = "SELECT RaumBez FROM " +TABNAME + " WHERE KEY = '"+rauID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	raumBez = RS.getString("RaumBez");
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return raumBez;
	} 

}
