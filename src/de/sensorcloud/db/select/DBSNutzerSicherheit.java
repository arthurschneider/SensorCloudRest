package de.sensorcloud.db.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.NutzerSicherheit;

public class DBSNutzerSicherheit {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	
	public static String getNutSicPasByNutStaID(String nutStaID) throws SQLException {
	
		String nutSicPas = "";
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT NutSicPas FROM NutzerSicherheit WHERE NutSicNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        
	        	nutSicPas = RS.getString("NutSicPas");
	        
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return nutSicPas;
		
	}
	
	
	public static ArrayList<NutzerSicherheit> getNutzerSicherheitByNutStaID(String tabelleName, String nutStaID) throws SQLException {
		
		ArrayList<NutzerSicherheit> nutzerSicherheitList = new ArrayList<NutzerSicherheit>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM '"+tabelleName+"' WHERE NutSicNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	NutzerSicherheit nutzerSicherheit = new NutzerSicherheit();
	        	nutzerSicherheit.setNutSicID(RS.getString("KEY"));
	        	nutzerSicherheit.setNutSicNutStaID(nutStaID);
	        	nutzerSicherheit.setNutSicPas(RS.getString("NutSicPas"));
	        	nutzerSicherheit.setNutSicPriKey(RS.getString("NutSicPriKey"));
	        	nutzerSicherheit.setNutSicPubKey(RS.getString("NutSicPubKey"));
	        	nutzerSicherheitList.add(nutzerSicherheit);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return nutzerSicherheitList;
		
	}

}
