package de.sensorcloud.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerSicherheit;

public class DBNutzerSicherheit {
	
	public static String getNutSicPasByNutStaID(String nutStaID){
	
		String nutSicPas = "";
		
		try {
		   
	        String CQL = "SELECT NutSicPas FROM NutzerSicherheit WHERE NutSicNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Cassandra.select(CQL);
	       
	        while (RS.next()) {
	        
	        	nutSicPas = RS.getString("NutSicPas");
	        
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 

		return nutSicPas;
		
	}
	
	
	public static ArrayList<NutzerSicherheit> getNutzerSicherheitByNutStaID(String tabelleName, String nutStaID) {
		
		ArrayList<NutzerSicherheit> nutzerSicherheitList = new ArrayList<NutzerSicherheit>();      
        
		String CQL = "SELECT * FROM NutzerSicherheit WHERE NutSicNutStaID = '"+nutStaID+"'";
	   
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
	        	NutzerSicherheit nutzerSicherheit = new NutzerSicherheit();
	        	nutzerSicherheit.setNutSicID(RS.getString("KEY"));
	        	nutzerSicherheit.setNutSicNutStaID(nutStaID);
	        	nutzerSicherheit.setNutSicPas(RS.getString("NutSicPas"));
	        	nutzerSicherheit.setNutSicPriKey(RS.getString("NutSicPriKey"));
	        	nutzerSicherheit.setNutSicPubKey(RS.getString("NutSicPubKey"));
	        	nutzerSicherheitList.add(nutzerSicherheit);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
        

		return nutzerSicherheitList;
		
	}
	
	public static void updateNutzerSicherheit(NutzerSicherheit nutzerSicherheit) {

		String CQL = "UPDATE NutzerSicherheit SET "
					+ "NutSicID = '" + nutzerSicherheit.getNutSicID() + "', "
					+ "NutSicNutStaID = '" + nutzerSicherheit.getNutSicNutStaID() + "', "
					+ "NutSicPas = '" + nutzerSicherheit.getNutSicPas() + "', "
					+ "NutSicPubKey = '" + nutzerSicherheit.getNutSicPubKey() + "', "
					+ "NutSicPriKey = '" + nutzerSicherheit.getNutSicPriKey() + "' "
					+ "WHERE KEY = " + nutzerSicherheit.getNutSicID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
