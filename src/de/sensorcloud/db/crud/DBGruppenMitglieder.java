package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.helpertools.Helper;

public class DBGruppenMitglieder {
	
public static final String TABNAME = "Gruppen";
	
	public static ArrayList<String> getGruMitGruIDByAdrID(String nutStaID) {
		
		ArrayList<String> id = new ArrayList<String>();
		String CQL = "SELECT GruMitGruID FROM " + TABNAME + " WHERE GruMitNutStaID = '"+ nutStaID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				id.add(RS.getString("GruMitGruID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	public static ArrayList<String> getGruMitNutStaIDByGruID(String gruID) {
		
		ArrayList<String> id = new ArrayList<String>();
		String CQL = "SELECT GruMitNutStaID FROM " + TABNAME + " WHERE GruMitGruID = '"+ gruID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				id.add(RS.getString("GruMitNutStaID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static String insertMitglied(String nutStaID, String gruID){
			
			String uuID = Helper.generateUUID();
			String CQL = "UPDATE " +TABNAME + " SET "
						+ "GruMitID = '" + uuID + "', "
						+ "GruMitGruID = '" + gruID + "', "
						+ "GruMitNutStaID = '" + nutStaID + "' "
						+ "WHERE KEY = " + uuID;

			try {
				Cassandra.update(CQL);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return uuID;
	}
}
