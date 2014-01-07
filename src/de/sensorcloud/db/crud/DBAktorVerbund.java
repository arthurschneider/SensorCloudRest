package de.sensorcloud.db.crud;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.entitaet.AktorVerbund;

public class DBAktorVerbund {

	public static final String TABNAME = "AktorVerbund";
    
    public static AktorVerbund getAktVerbBezByAktVerMitAktVerID(String aktVerMitAktVerID) {
        AktorVerbund aktVerb = new AktorVerbund();

        String CQL = "SELECT AktVerBez, AktVerID FROM " +TABNAME + " WHERE KEY = '" + aktVerMitAktVerID + "'";

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
            
            String CQL =  "UPDATE " + TABNAME + " SET "
                        + "AktVerID = '" + aktorVerbund.getAktVerID() + "', "
                        + "AktVerBez = '" + aktorVerbund.getAktVerBez() + "' "
                        + "WHERE KEY = " + aktorVerbund.getAktVerID();
            
            try {
            	Cassandra.update(CQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return aktorVerbund.getAktVerID();
    }

}
