package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Gruppen;
import de.sensorcloud.helpertools.Helper;

public class DBGruppen {
	public static String TABNAME = "Gruppen";

	public static Gruppen getGruppeByGruMitGruID(String gruID) {

		Gruppen gruppe = new Gruppen();
		String CQL = "SELECT GruID, GruBez FROM " + TABNAME + " WHERE KEY = '"+ gruID + "'";
		try {
			
			ResultSet RS = Cassandra.select(CQL);
			while (RS.next()) {
				
				gruppe.setGruID(RS.getString("GruID"));
				gruppe.setGruBez(RS.getString("GruBez"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return gruppe;
	}
	
	public static String createGruppe(String gruBez) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " +TABNAME + " SET "
					+ "GruID = '" + uuID + "', "
					+ "GruBez = '" + gruBez + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uuID;
	}

}
