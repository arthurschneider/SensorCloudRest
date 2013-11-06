package de.sensorcloud.db.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.Aktor;

public class DBSAktor {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static ArrayList<Aktor> getAktorByNutStaID(String nutStaID) throws SQLException {
		
		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM Aktor WHERE AktNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        	
	        	Aktor aktor = new Aktor();
	        	aktor.setAktID(RS.getString("KEY"));
	        	aktor.setAktNutStaID(nutStaID);
	        	aktor.setAktAktProID(RS.getString("AktAktProID"));
	        	aktor.setAktAktTypID(RS.getString("AktAktTypID"));
	        	aktor.setAktRauID(RS.getString("AktRauID"));
	        	aktor.setAktLocMasID(RS.getString("AktLocMasID"));
	        	aktor.setAktSouID(RS.getString("AktSouID"));
	        	aktor.setAktBez(RS.getString("AktBez"));
	        	aktor.setAktPos(RS.getString("AktPos"));
	        	aktor.setAktDatEin(RS.getString("AktDatEin"));
	        	aktorList.add(aktor);
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}

		return aktorList;
		
	} 

}
