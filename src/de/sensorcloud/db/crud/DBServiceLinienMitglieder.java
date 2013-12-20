package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.ServiceIDMitTyp;
import de.sensorcloud.helpertools.Helper;

public class DBServiceLinienMitglieder {
	
	public static final String TABNAME = "ServiceLinienMitglieder";
	
	public static String createServiceLinienMitglieder(String serLinID, String typ, String serviceID) {
		String uuID = Helper.generateUUID();
        String CQL =  "UPDATE " + TABNAME + " SET "
                    + "SerLinMitID = '" + uuID + "', "
                    + "SerLinMitSerID = '" + serviceID + "', "
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

	public static ArrayList<ServiceIDMitTyp> getServiceIDTypBySerLinID(String serLinID) {
        ArrayList<ServiceIDMitTyp> servList = new ArrayList<ServiceIDMitTyp>();
        String CQL = "SELECT SerLinMitSerID, SerLinMitTyp FROM " + TABNAME + " WHERE SerLinMitSerLinID = '"+serLinID+"'";
        // AND SerLinMitTyp = '"+"SensorService"+"'
        try {
        
        	ResultSet RS = Cassandra.select(CQL);

        	while (RS.next()) {
        		ServiceIDMitTyp idTyp = new ServiceIDMitTyp();
        		idTyp.setServID(RS.getString("SerLinMitSerID"));
        		idTyp.setTyp(RS.getString("SerLinMitTyp"));
        		servList.add(idTyp);
        	}
            } catch (SQLException ex) {
                ex.printStackTrace();
        }
        return servList;
    }
}
