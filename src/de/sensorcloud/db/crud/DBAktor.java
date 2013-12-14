package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Feldgeraet;

public class DBAktor {
	
	public static final String TABNAME = "Aktor";

	public static ArrayList<Feldgeraet> getAktorByNutStaID(String nutStaID) {

		ArrayList<Feldgeraet> aktorList = new ArrayList<Feldgeraet>();

		String CQL = "SELECT * FROM "+ TABNAME +" WHERE AktNutStaID = '" + nutStaID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			while (RS.next()) {

				Feldgeraet aktor = new Feldgeraet();
				aktor.setiD(RS.getString("KEY"));
				aktor.setNutStaID(nutStaID);
				aktor.setProID(RS.getString("AktAktProID"));
				aktor.setTypID(RS.getString("AktAktTypID"));
				aktor.setRauID(DBRaum.getRauBezByRauID(RS.getString("AktRauID")));
				aktor.setLocMasID(RS.getString("AktLocMasID"));
				aktor.setSouID(RS.getString("AktSouID"));
				aktor.setBez(RS.getString("AktBez"));
				aktor.setPos(RS.getString("AktPos"));
				aktor.setDatEin(RS.getString("AktDatEin"));
				aktorList.add(aktor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aktorList;

	}

	public static Feldgeraet getAktorByAktID(String aktID) {

		Feldgeraet aktor = new Feldgeraet();

		String CQL = "SELECT * FROM " + TABNAME + " WHERE KEY = '" + aktID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);

			while (RS.next()) {

				aktor.setiD(aktID);
				aktor.setNutStaID(RS.getString("AktNutStaID"));
				aktor.setProID(RS.getString("AktAktProID"));
				aktor.setTypID(RS.getString("AktAktTypID"));
				aktor.setRauID(RS.getString("AktRauID"));
				aktor.setLocMasID(RS.getString("AktLocMasID"));
				aktor.setSouID(RS.getString("AktSouID"));
				aktor.setBez(RS.getString("AktBez"));
				aktor.setPos(RS.getString("AktPos"));
				aktor.setDatEin(RS.getString("AktDatEin"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aktor;

	}

	public static HashSet<String> getAktorSensorIDListByNutStaID(String aktID) {

		HashSet<String> aktIDLIst = new HashSet<String>();
		String CQL = "SELECT AktID FROM " + TABNAME + " WHERE AktNutStaID = '" + aktID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			while (RS.next()) {

				aktIDLIst.add(RS.getString("AktID"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aktIDLIst;

	}

}
