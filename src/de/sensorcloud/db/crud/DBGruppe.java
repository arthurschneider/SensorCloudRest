package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Gruppen;

public class DBGruppe {
	public static String TABNAME = "Gruppen";

	public static Gruppen getGruppeByGruMitGruID(String gruID) {

		Gruppen gruppe = new Gruppen();
		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '"+ gruID + "'";
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

}
