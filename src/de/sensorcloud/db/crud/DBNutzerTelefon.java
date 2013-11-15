package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerTelefon;
import de.sensorcloud.helpertools.Helper;

public class DBNutzerTelefon {
	
	public static final String TABNAME = "NutzerTelefon";
	
	public static ArrayList<NutzerTelefon> getNutzerTelefonByNutStaID(String nutStaID) {
		
		ArrayList<NutzerTelefon> nutzerTelefonList = new ArrayList<NutzerTelefon>();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE NutTelNutStaID = '"+nutStaID+"'";
		
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

		String CQL = "UPDATE " + TABNAME + " SET "
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
	
	public static String insertNutzerTelefon(NutzerTelefon nutzerTelefon) {

		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "NutTelID = '" + uuID + "', "
					+ "NutTelNutStaID = '" + nutzerTelefon.getNutTelNutStaID() + "', "
					+ "NutTelNum = '" + nutzerTelefon.getNutTelNum() + "', "
					+ "NutTelBez = '" + nutzerTelefon.getNutTelBez() + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uuID;
	}
	
	public static void deleteNutzerTelefon(String nutTelID) {
		
		String CQL = "DELETE NutTelNutStaID, NutTelNum, NutTelBez, NutTelID FROM " +TABNAME + "  WHERE KEY = '"+nutTelID+"'";
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
