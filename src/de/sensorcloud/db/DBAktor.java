package de.sensorcloud.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.Aktor;

public class DBAktor {

	public static ArrayList<Aktor> getAktorByNutStaID(String nutStaID) {

		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();

		String CQL = "SELECT * FROM Aktor WHERE AktNutStaID = '" + nutStaID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			while (RS.next()) {

				Aktor aktor = new Aktor();
				aktor.setAktID(RS.getString("KEY"));
				aktor.setAktNutStaID(nutStaID);
				aktor.setAktAktProID(RS.getString("AktAktProID"));
				aktor.setAktAktTypID(RS.getString("AktAktTypID"));
				aktor.setAktRauID(RS.getString("AktRauID"));
				aktor.setAktLocMasID(RS.getString("AktLocMasID"));
				aktor.setAktSouID(RS.getString("AktSouID"));
				aktor.setAktBez(RS.getString("AktBez"));
				aktor.setAktPos(RS.getString("AktPos"));
				aktor.setAktDatEin(RS.getString("AktDatEin"));
				aktorList.add(aktor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aktorList;

	}

	public static Aktor getAktorByAktID(String aktID) {

		Aktor aktor = new Aktor();

		String CQL = "SELECT * FROM Aktor WHERE AktID = '" + aktID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);

			while (RS.next()) {

				aktor.setAktID(aktID);
				aktor.setAktNutStaID(RS.getString("AktNutStaID"));
				aktor.setAktAktProID(RS.getString("AktAktProID"));
				aktor.setAktAktTypID(RS.getString("AktAktTypID"));
				aktor.setAktRauID(RS.getString("AktRauID"));
				aktor.setAktLocMasID(RS.getString("AktLocMasID"));
				aktor.setAktSouID(RS.getString("AktSouID"));
				aktor.setAktBez(RS.getString("AktBez"));
				aktor.setAktPos(RS.getString("AktPos"));
				aktor.setAktDatEin(RS.getString("AktDatEin"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aktor;

	}

	public static HashSet<String> getAktorIDListByNutStaID(String aktID) {

		HashSet<String> aktIDLIst = new HashSet<String>();
		String CQL = "SELECT AktID FROM Aktor WHERE NutStaID = '" + aktID + "'";

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
