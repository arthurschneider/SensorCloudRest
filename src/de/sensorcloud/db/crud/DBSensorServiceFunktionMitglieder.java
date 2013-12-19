package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;

public class DBSensorServiceFunktionMitglieder {
	
public static final String TABNAME = "SensorServiceFunktionMitglieder";
    
    public static ArrayList<String> getFunktionIDListBySenSerID(String senSerID){
        ArrayList<String> list = new ArrayList<String>();
        String CQL = "SELECT SenSerFunMitSenSerFunID FROM " +TABNAME + " WHERE SenSerFunMitSenSerID = '"+senSerID+"'";
        
        try {
        
        	ResultSet RS = Cassandra.select(CQL);

        	while (RS.next()) {
	             list.add(RS.getString("SenSerFunMitSenSerFunID"));
        	}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
