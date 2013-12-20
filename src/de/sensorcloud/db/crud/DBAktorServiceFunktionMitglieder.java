package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.sensorcloud.db.connection.Cassandra;

public class DBAktorServiceFunktionMitglieder {
	
public static final String TABNAME = "AktorServiceFunktionMitglieder";
    
    public static ArrayList<String> getFunktionIDListByAktSerID(String aktSerID){
        ArrayList<String> list = new ArrayList<String>();
        String CQL = "SELECT AktSerFunMitAktSerFunID FROM " +TABNAME + " WHERE AktSerFunMitAktSerID = '"+aktSerID+"'";
        
        try {
        
        	ResultSet RS = Cassandra.select(CQL);

        	while (RS.next()) {
	             list.add(RS.getString("AktSerFunMitAktSerFunID"));
        	}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
