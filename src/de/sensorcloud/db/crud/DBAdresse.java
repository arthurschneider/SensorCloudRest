package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Adresse;

public class DBAdresse {

	public static final String TABNAME = "Adresse";
	
	public static Adresse getAdresseByAdrID(String adrID) {
		Adresse adresse = new Adresse();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '"+ adrID + "'";
		
		try {
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {

				adresse.setAdrID(RS.getString("KEY"));
				adresse.setAdrBez(RS.getString("AdrBez"));
				adresse.setAdrStr(RS.getString("AdrStr"));
				adresse.setAdrPlz(RS.getString("AdrPlz"));
				adresse.setAdrOrt(RS.getString("AdrOrt"));
				adresse.setAdrLan(RS.getString("AdrLan"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}
	
	
	public static void updateAdresse(Adresse adresse) {
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "AdrBez = '" + adresse.getAdrBez() + "', "
					+ "AdrStr = '" + adresse.getAdrStr() + "', "
					+ "AdrPlz = '" + adresse.getAdrPlz() + "', "
					+ "AdrOrt = '" + adresse.getAdrOrt() + "', "
					+ "AdrLan = '" + adresse.getAdrLan() + "' "
					+ "WHERE KEY = " + adresse.getAdrID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String insertAdresse(Adresse adresse) {
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "AdrID = '" + adresse.getAdrID() + "', "
					+ "AdrBez = '" + adresse.getAdrBez() + "', "
					+ "AdrStr = '" + adresse.getAdrStr() + "', "
					+ "AdrPlz = '" + adresse.getAdrPlz() + "', "
					+ "AdrOrt = '" + adresse.getAdrOrt() + "', "
					+ "AdrLan = '" + adresse.getAdrLan() + "' "
					+ "WHERE KEY = " + adresse.getAdrID();
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse.getAdrID();
	}

}
