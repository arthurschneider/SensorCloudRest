package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.FeldgeraetVerbund;
import de.sensorcloud.helpertools.Helper;

public class DBAktorVerbund {

	public static final String  TABNAME = "AktorVerbund";
	
	public static FeldgeraetVerbund getAktVerbBezByAktVerMitAktVerID(String aktVerMitAktVerID) {

		FeldgeraetVerbund aktVerb = new FeldgeraetVerbund();

		String CQL = "SELECT * FROM " +TABNAME + " WHERE KEY = '" + aktVerMitAktVerID + "'";

		try {

			ResultSet RS = Cassandra.select(CQL);

			while (RS.next()) {

				aktVerb.setVerBez(RS.getString("AktVerBez"));
				aktVerb.setVerID(RS.getString("AktVerID"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return aktVerb;

	}
	
	public static String createAktorVerbund(FeldgeraetVerbund aktorVerbund) {
		
		String uuID = Helper.generateUUID();
		String CQL = "UPDATE " + TABNAME + " SET "
					+ "AktVerID = '" + uuID + "', "
					+ "AktVerBez = '" + aktorVerbund.getVerBez() + "' "
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
