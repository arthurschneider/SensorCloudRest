package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.InsertMandant;
import de.sensorcloud.entitaet.Mandanten;
import de.sensorcloud.helpertools.Helper;

public class DBMandanten {
	
	public static final String TABNAME = "Mandanten";

	public static ArrayList<Mandanten> getMandantenByManNutStaID(String nutStaID) {
		
		ArrayList<Mandanten> mandantenList = new ArrayList<Mandanten>();
		
		String CQL = "SELECT * FROM " + TABNAME + " WHERE ManNutStaID = '"+nutStaID+"'";
		
		try {
		   
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	Mandanten mandanten = new Mandanten();
	        	mandanten.setManID(RS.getString("ManID"));
	        	mandanten.setManBez(RS.getString("ManBez"));
	        	mandanten.setManNutStaID(RS.getString("ManNutStaID"));
	        	mandantenList.add(mandanten);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return mandantenList;
		
	} 
	
	
	public static String insertMandanten(InsertMandant mandant) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "ManID = '" + uuID + "', "
					+ "ManNutStaID = '" + mandant.getManNutStaID() + "', "
					+ "ManBEz = '" + mandant.getManBez() + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uuID;
	}

}
