package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;

public class DBAktorServiceMitglieder {
	
public static final String TABNAME = "AktorServiceMitglieder";
	
	public static ArrayList<String> getServiceIDByAktID(String aktID) {
		
		ArrayList<String> id = new ArrayList<String>();
		String CQL = "SELECT AktSerMitAktSerID FROM " + TABNAME + " WHERE AktnSerMitAktID = '"+ aktID + "'";

		try {
			ResultSet RS = Cassandra.select(CQL);
			
			while (RS.next()) {
				id.add(RS.getString("AktSerMitAktSerID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
