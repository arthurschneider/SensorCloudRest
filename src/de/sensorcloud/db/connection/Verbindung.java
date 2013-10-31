package de.sensorcloud.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Verbindung {
	
	public Connection connect() throws SQLException{
        String treiber;

        treiber = "org.apache.cassandra.cql.jdbc.CassandraDriver";
        Connection con = null;
        
        // Treiber laden
        try{
            Class.forName(treiber).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.out.println("Fehler beim laden des Treibers: "+ e.getMessage());
        }
         
        // Erstellung Datenbank-Verbindungsinstanz
        try {
            
            con = DriverManager.getConnection("jdbc:cassandra://139.6.17.20:9160/SensorCloud");
            
        } catch (SQLException e){
            System.out.println("Fehler beim Verbindungsaufbau zur Datenbank!");
            System.out.println(e.getMessage());
        }
        return con;
    }
}
