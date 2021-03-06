package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerSicherheit;

public class DBNutzerSicherheit {
	
	public static final String TABNAME = "NutzerSicherheit";
	
	public static String getNutSicPasByNutStaID(String nutStaID){
	
		String nutSicPas = "";
		
		try {
		   
	        String CQL = "SELECT NutSicPas FROM " + TABNAME + " WHERE NutSicNutStaID = '"+nutStaID+"'";
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        
	        	nutSicPas = RS.getString("NutSicPas");
	        
	        }
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return nutSicPas;
	}
	
	
	
	
	public static String getNutSicPubKeyByNutStaID(String nutStaID){
		String nutSicPas = "";
		
		try {
		   
	        String CQL = "SELECT NutSicPubKey FROM " + TABNAME + " WHERE NutSicNutStaID = '"+nutStaID+"'";
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        	nutSicPas = RS.getString("NutSicPubKey");
	        }
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
		return nutSicPas;
	}
	
	
	public static ArrayList<NutzerSicherheit> getNutzerSicherheitByNutStaID(String nutStaID) {
		ArrayList<NutzerSicherheit> nutzerSicherheitList = new ArrayList<NutzerSicherheit>();      
        
		String CQL = "SELECT * FROM " + TABNAME + " WHERE NutSicNutStaID = '"+nutStaID+"'";
	   
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				NutzerSicherheit nutzerSicherheit = new NutzerSicherheit();
	        	nutzerSicherheit.setNutSicID(RS.getString("NutSicID"));
	        	nutzerSicherheit.setNutSicNutStaID(nutStaID);
	        	nutzerSicherheit.setNutSicPas(RS.getString("NutSicPas"));
	        	nutzerSicherheit.setNutSicPriKey(RS.getString("NutSicPriKey"));
	        	nutzerSicherheit.setNutSicPubKey(RS.getString("NutSicPubKey"));
	        	nutzerSicherheitList.add(nutzerSicherheit);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nutzerSicherheitList;
	}
	
	public static void updateNutzerSicherheit(NutzerSicherheit nutzerSicherheit) {
		String CQL = "UPDATE "+ TABNAME + " SET "
					+ "NutSicID = '" + nutzerSicherheit.getNutSicID() + "', "
					+ "NutSicNutStaID = '" + nutzerSicherheit.getNutSicNutStaID() + "', "
					+ "NutSicPas = '" + nutzerSicherheit.getNutSicPas() + "', "
					+ "NutSicPubKey = '" + nutzerSicherheit.getNutSicPubKey() + "', "
					+ "NutSicPriKey = '" + nutzerSicherheit.getNutSicPriKey() + "' "
					+ "WHERE KEY = " + nutzerSicherheit.getNutSicID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String insertNutzerSicherheit(NutzerSicherheit sicherheit) {
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "NutSicID = '" + sicherheit.getNutSicID() + "', "
					+ "NutSicNutStaID = '" + sicherheit.getNutSicNutStaID() + "', "
					+ "NutSicPas = '" + sicherheit.getNutSicPas() + "', "
					+ "NutSicPriKey = '" + sicherheit.getNutSicPriKey() + "', "
					+ "NutSicPubKey = '" + sicherheit.getNutSicPubKey() + "' "
					+ "WHERE KEY = " + sicherheit.getNutSicID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sicherheit.getNutSicID();
	}
	
	public static void deleteNutzerSicherheit(String nutSicID) {
		
		String CQL = "DELETE NutSicID, NutSicNutStaID, NutSicPas, NutSicPriKey, NutSicPubKey FROM " +TABNAME + "  WHERE KEY = '"+nutSicID+"'";
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
