package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.AktorService;

public class DBAktorService {
	
public static final String TABNAME = "AktorService";

	
	public static AktorService getAktorServiceByAktSerID(String aktSerID) {
		
		String CQL = "SELECT * FROM " +TABNAME + " WHERE KEY = '"+aktSerID+"'";
		AktorService serv = new AktorService();
		try {
			
			ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	serv.setAktSerID(RS.getString("AktSerID"));
	        	serv.setAktSerBez(RS.getString("AktSerBez"));
	        }
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return serv;
	}

}
