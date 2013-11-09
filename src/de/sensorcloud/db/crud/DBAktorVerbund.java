package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.AktorVerbund;

public class DBAktorVerbund {

	public static AktorVerbund getAktVerbBezByAktVerMitAktVerID(String aktVerMitAktVerID) {

		AktorVerbund aktVerb = new AktorVerbund();

		String CQL = "SELECT * FROM AktorVerbund WHERE AktVerID = '" + aktVerMitAktVerID + "'";

		try {

			ResultSet RS = Cassandra.select(CQL);

			while (RS.next()) {

				aktVerb.setAktVerBez(RS.getString("AktVerBez"));
				aktVerb.setAktVerID(RS.getString("AktVerID"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return aktVerb;

	}

}
