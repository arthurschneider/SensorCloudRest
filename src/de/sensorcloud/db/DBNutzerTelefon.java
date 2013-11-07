package de.sensorcloud.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Verbindung;
import de.sensorcloud.entitaet.NutzerTelefon;

public class DBNutzerTelefon {
	
	static Verbindung verb = new Verbindung();
	static Connection con;
	
	public static ArrayList<NutzerTelefon> getNutzerTelefonByNutStaID(String tabelleName, String nutStaID) throws SQLException{
		ArrayList<NutzerTelefon> nutzerTelefonList = new ArrayList<NutzerTelefon>();
		
		try {
		      
			con = verb.connect();
	        Statement Stmt = con.createStatement();
	            
	        String CQL = "SELECT * FROM '"+tabelleName+"' WHERE NutTelNutStaID = '"+nutStaID+"'";
	           
	        ResultSet RS   = Stmt.executeQuery(CQL);
	       
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
		} finally{
			con.close();
		}

		return nutzerTelefonList;
	}

}
