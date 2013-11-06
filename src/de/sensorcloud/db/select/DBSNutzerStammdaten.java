package de.sensorcloud.db.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.NutzerStammdaten;

public class DBSNutzerStammdaten {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static NutzerStammdaten getNutzerStammdatenByID(String tabelleName, String nutStaID) throws SQLException{
		NutzerStammdaten nutzerStammdaten = new NutzerStammdaten();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM '"+tabelleName+"' WHERE KEY = '"+nutStaID+"'";
	           
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

}
