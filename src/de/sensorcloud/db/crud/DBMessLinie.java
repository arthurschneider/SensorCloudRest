package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;

public class DBMessLinie {

	public static final String TABNAME = "MessLinie";

	public static String getMesLinMesWerIDsBySenIDAndMesLinTimBegAndMesLinTimEnd(String senID, long mesLinTimBeg, long mesLinTimEnd) {
		
		String messwerteKeys = null;
		
			String CQL = "SELECT MesLinMesWerIDs FROM " + TABNAME + " WHERE MesLinQueID = '"+senID+"' AND MesLinTimBeg = '"+mesLinTimBeg+"' AND MesLinTimEnd = '"+mesLinTimEnd+"'";
			
			try {
			   
		        ResultSet RS   = Cassandra.select(CQL);
		       
		        while (RS.next()) {
		        		messwerteKeys = RS.getString("MesLinMesWerIDs");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			return messwerteKeys;
		
	} 

}
