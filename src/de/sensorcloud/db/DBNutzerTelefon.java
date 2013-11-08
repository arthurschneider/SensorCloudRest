package de.sensorcloud.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerTelefon;

public class DBNutzerTelefon {
	
	public static ArrayList<NutzerTelefon> getNutzerTelefonByNutStaID(String tabelleName, String nutStaID) {
		
		ArrayList<NutzerTelefon> nutzerTelefonList = new ArrayList<NutzerTelefon>();
		String CQL = "SELECT * FROM '"+tabelleName+"' WHERE NutTelNutStaID = '"+nutStaID+"'";
		
		try {
		   
	        
	           
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	NutzerTelefon nutzerTelefon = new NutzerTelefon();
	        	nutzerTelefon.setNutTelID(RS.getString("KEY"));
	        	nutzerTelefon.setNutTelNutStaID(nutStaID);
	        	nutzerTelefon.setNutTelNum(RS.getString("NutTelNum"));
	        	nutzerTelefon.setNutTelBez(RS.getString("NutTelBez"));
	        	nutzerTelefonList.add(nutzerTelefon);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return nutzerTelefonList;
	}
	
	public static void updateNutzerTelefon(NutzerTelefon nutzerTelefon) {

		String CQL = "UPDATE NutzerTelefon SET "
					+ "NutTelID = '" + nutzerTelefon.getNutTelID() + "', "
					+ "NutTelNutStaID = '" + nutzerTelefon.getNutTelNutStaID() + "', "
					+ "NutTelNum = '" + nutzerTelefon.getNutTelNum() + "', "
					+ "NutTelBez = '" + nutzerTelefon.getNutTelBez() + "' "
					+ "WHERE KEY = " + nutzerTelefon.getNutTelID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
