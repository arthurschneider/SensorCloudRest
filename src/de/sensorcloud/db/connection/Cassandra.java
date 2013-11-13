package de.sensorcloud.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cassandra {
	

	public static Connection connect() throws SQLException{
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
            
        	con = DriverManager.getConnection("insert here name");
            
        } catch (SQLException e){
            System.out.println("Fehler beim Verbindungsaufbau zur Datenbank!");
            System.out.println(e.getMessage());
        }
        return con;
    }  
	
	public static void update(String CQL) throws SQLException {
		Connection con = null;
		try {
			
			con = connect();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(CQL);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			con.close();
		}

	}
	
	
	public static ResultSet select(String CQL) throws SQLException {
		Connection con = null;
		ResultSet RS = null;
		try {

			con = connect();
			Statement Stmt = con.createStatement();
			RS = Stmt.executeQuery(CQL);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			con.close();
		}
		
		return RS;
	}
	
}
