package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.ServiceLinien;

public class DBServiceLinien {
	
	public static final String TABNAME = "ServiceLinien";
	
	public static ArrayList<ServiceLinien> getServiceLinienListByNutStaID(String nutStaID){
        ArrayList<ServiceLinien> linienList = new ArrayList<ServiceLinien>();
        String CQL = "SELECT * FROM " +TABNAME + " WHERE SerLinNutStaID = '"+nutStaID+"'";
        
        try {
        	ResultSet RS = Cassandra.select(CQL);

        	while (RS.next()) {
	             ServiceLinien linie = new ServiceLinien();
	             linie.setSerLinID(RS.getString("SerLinID"));
	             linie.setSerLinBez(RS.getString("SerLinBez"));
	             linie.setSerLinNutStaID(RS.getString("SerLinNutStaID"));
	             linienList.add(linie);
        	}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return linienList;
    }
	
	public static String createServiceLinien(ServiceLinien linie) {
        String CQL =  "UPDATE " + TABNAME + " SET "
                    + "SerLinID = '" + linie.getSerLinID() + "', "
                    + "SerLinBez = '" + linie.getSerLinBez() + "', "
                    + "SerLinNutStaID = '" + linie.getSerLinNutStaID() + "' "
                    + "WHERE KEY = " + linie.getSerLinID();
        
        try {
                Cassandra.update(CQL);
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return linie.getSerLinID();
    }

}
