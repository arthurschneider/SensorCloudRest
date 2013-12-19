package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.SensorService;
import de.sensorcloud.helpertools.Helper;

public class DBServiceLinienMitglieder {
	
	public static final String TABNAME = "ServiceLinienMitglieder";
	
	public static String createServiceLinienMitglieder(String serLinID, String typ, SensorService service) {
		String uuID = Helper.generateUUID();
        String CQL =  "UPDATE " + TABNAME + " SET "
                    + "SerLinMitID = '" + uuID + "', "
                    + "SerLinMitSenSerID = '" + service.getSenSerID() + "', "
                    + "SerLinMitSerLinID = '" + serLinID + "', "
                    + "SerLinMitTyp = '" + typ + "' "
                    + "WHERE KEY = " + uuID;
        
        try {
            Cassandra.update(CQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uuID;
    }

	public static ArrayList<String> getServiceIDBySerLinID(String serLinID) {
        ArrayList<String> servList = new ArrayList<String>();
        String CQL = "SELECT SerLinMitSenSerID FROM " + TABNAME + " WHERE SerLinMitSerLinID = '"+serLinID+"' AND SerLinMitTyp = '"+"SensorService"+"'";
        
        try {
        
        	ResultSet RS = Cassandra.select(CQL);

        	while (RS.next()) {
        		servList.add(RS.getString("SerLinMitSenSerID"));
        	}
            } catch (SQLException ex) {
                ex.printStackTrace();
        }
        return servList;
    }
}
