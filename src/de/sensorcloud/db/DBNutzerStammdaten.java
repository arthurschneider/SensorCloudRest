package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.NutzerStammdaten;

public class DBNutzerStammdaten {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static NutzerStammdaten getNutzerStammdatenByID(String nutStaID) throws SQLException{
		NutzerStammdaten nutzerStammdaten = new NutzerStammdaten();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM NutzerStammdaten WHERE KEY = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
	        while (RS.next()) {
	        
	        	nutzerStammdaten.setNutStaID(RS.getString("KEY"));
	        	nutzerStammdaten.setNutStaAnr(RS.getString("NutStaAnr"));
	        	nutzerStammdaten.setNutStaAdrID(RS.getString("NutStaAdrID"));
	        	nutzerStammdaten.setNutStaFir(RS.getString("NutStaFir"));
	        	nutzerStammdaten.setNutStaNam(RS.getString("NutStaNam"));
	        	nutzerStammdaten.setNutStaVor(RS.getString("NutStaVor"));
	        	nutzerStammdaten.setNutStaDatEin(RS.getString("NutStaDatEin"));
	        }
	         
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally{
			con.close();
		}
		
		return nutzerStammdaten;
	}
	
	public static void updateNutzerStammdaten(NutzerStammdaten nutzerStammdaten) throws SQLException{
		
		try {
	      con = verb.connect();
	      Statement stmt = con.createStatement();
	          
           String CQL = "UPDATE NutzerStammdaten SET NutStaAnr = '"+nutzerStammdaten.getNutStaAnr()+"', "
           				+ "NutStaAdrID = '"+nutzerStammdaten.getNutStaAdrID()+"', NutStaFir = '"+nutzerStammdaten.getNutStaFir()+"', "
           				+ "NutStaNam = '"+nutzerStammdaten.getNutStaNam()+"', NutStaVor = '"+nutzerStammdaten.getNutStaVor()+"', "
           				+ "NutStaDatEin = '"+nutzerStammdaten.getNutStaDatEin()+"', NutStaID = '"+nutzerStammdaten.getNutStaID()+"' WHERE KEY = "+nutzerStammdaten.getNutStaID();
          stmt.executeUpdate(CQL);
	    } catch (SQLException ex) {
	      ex.printStackTrace();
	    }finally {
	    	con.close();
	    }
		 
	}

}
