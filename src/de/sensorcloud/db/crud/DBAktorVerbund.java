package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.helpertools.Helper;

public class DBAktorVerbund {

	public static final String  TABNAME = "AktorVerbund";
	
	public static AktorVerbund getAktVerbBezByAktVerMitAktVerID(String aktVerMitAktVerID) {

		AktorVerbund aktVerb = new AktorVerbund();

		String CQL = "SELECT * FROM " +TABNAME + " WHERE AktVerID = '" + aktVerMitAktVerID + "'";

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
	
	public static String createAktorVerbund(AktorVerbund aktorVerbund) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "AktVerID = '" + uuID + "', "
					+ "AktVerBez = '" + aktorVerbund.getAktVerBez() + "' "
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
