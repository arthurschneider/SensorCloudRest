package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.NutzerStammdaten;

public class DBNutzerStammdaten {
	
	public static final String TABNAME = "NutzerStammdaten";

	public static NutzerStammdaten getNutzerStammdatenByNutStaID(String nutStaID) {
		
		NutzerStammdaten nutzerStammdaten = new NutzerStammdaten();
		String CQL = "SELECT NutStaID, NutStaAnr, NutStaAdrID, NutStaNam, NutStaFir, NutStaVor, NutStaDatEin FROM " + TABNAME + " WHERE KEY = '"+ nutStaID + "'";
		
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				nutzerStammdaten.setNutStaID(RS.getString("NutStaID"));
				nutzerStammdaten.setNutStaAnr(RS.getString("NutStaAnr"));
				nutzerStammdaten.setNutStaAdrID(RS.getString("NutStaAdrID"));
				nutzerStammdaten.setNutStaFir(RS.getString("NutStaFir"));
				nutzerStammdaten.setNutStaNam(RS.getString("NutStaNam"));
				nutzerStammdaten.setNutStaVor(RS.getString("NutStaVor"));
				nutzerStammdaten.setNutStaDatEin(RS.getString("NutStaDatEin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return nutzerStammdaten;
	}

	
	public static void updateNutzerStammdaten(NutzerStammdaten nutzerStammdaten){

		String CQL = "UPDATE " + TABNAME + " SET NutStaAnr = '"
				+ nutzerStammdaten.getNutStaAnr() + "', " + "NutStaAdrID = '"
				+ nutzerStammdaten.getNutStaAdrID() + "', NutStaFir = '"
				+ nutzerStammdaten.getNutStaFir() + "', " + "NutStaNam = '"
				+ nutzerStammdaten.getNutStaNam() + "', NutStaVor = '"
				+ nutzerStammdaten.getNutStaVor() + "', " + "NutStaDatEin = '"
				+ nutzerStammdaten.getNutStaDatEin() + "', NutStaID = '"
				+ nutzerStammdaten.getNutStaID() + "' WHERE KEY = "
				+ nutzerStammdaten.getNutStaID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
