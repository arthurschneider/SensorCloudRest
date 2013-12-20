package de.sensorcloud.httprequest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.sensorcloud.db.connection.Cassandra;
import de.sensorcloud.helpertools.Helper;

@Path("/Tabelle")
public class HttpTeballeAnlegen {

	
	@GET
	@Path("/create/ServiceLinienMitglieder")
	@Produces(MediaType.APPLICATION_JSON)
	public String createServiceLinienMitglieder() {
		String cql = "CREATE COLUMNFAMILY ServiceLinienMitglieder (KEY text PRIMARY KEY, SerLinMitID text, SerLinMitSerID text, SerLinMitSerLinID text, SerLinMitTyp text)"; 
		try {
			Cassandra.update(cql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ServiceLinienMitglieder ausgefuehrt";
	}
	
	@GET
	@Path("/create/SensorServiceFunktion")
	@Produces(MediaType.APPLICATION_JSON)
	public String createSensorServiceFunktion() {
		String cql = "CREATE COLUMNFAMILY SensorServiceFunktion (KEY text PRIMARY KEY, SenSerFunID text, SenSerFunNam text)"; 
		try {
			Cassandra.update(cql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "SensorServiceFunktion ausgefuehrt";
	}
	
	@GET
	@Path("/create/AktorServiceFunktion")
	@Produces(MediaType.APPLICATION_JSON)
	public String createAktorServiceFunktion() {
		String cql = "CREATE COLUMNFAMILY AktorServiceFunktion (KEY text PRIMARY KEY, AktSerFunID text, AktSerFunNam text)"; 
		try {
			Cassandra.update(cql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "AktorServiceFunktion ausgefuehrt";
	}
	
	@GET
	@Path("/create/SensorServiceFunktionMitglieder")
	@Produces(MediaType.APPLICATION_JSON)
	public String createSensorServiceFunktionMitglieder() {
		String cql = "CREATE COLUMNFAMILY SensorServiceFunktionMitglieder (KEY text PRIMARY KEY, SenSerFunMitID text, SenSerFunMitSenSerFunID text, SenSerFunMitSenSerID text)"; 
		try {
			Cassandra.update(cql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "SensorServiceFunktionMitglieder ausgefuehrt";
	}
	
	@GET
	@Path("/create/AktorServiceFunktionMitglieder")
	@Produces(MediaType.APPLICATION_JSON)
	public String createAktorServiceFunktionMitglieder() {
		String cql = "CREATE COLUMNFAMILY AktorServiceFunktionMitglieder (KEY text PRIMARY KEY, AktSerFunMitID text, AktSerFunMitAktSerFunID text, AktSerFunMitAktSerID text)"; 
		try {
			Cassandra.update(cql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "AktorServiceFunktionMitglieder ausgefuehrt";
	}
	
	@GET
	@Path("/create/Index")
	@Produces(MediaType.APPLICATION_JSON)
	public String createIndex() {
		String cql = "CREATE INDEX ON SensorServiceMitglieder (SenSerMitSenSerID)"; 
		try {
			Cassandra.update(cql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Index eingefuegt";
	}
	
	@GET
	@Path("/insert/SensorServiceFunktionMitglieder/SenSerID/{senSerID}/FunName/{funName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertNutzerTelefon(@PathParam("senSerID") String senSerID,
		  							  @PathParam("funName") String funName) {

		String uuID = Helper.generateUUID();
		String funuuID = Helper.generateUUID();
		String CQL = "UPDATE " +"SensorServiceFunktionMitglieder" + " SET "
					+ "SenSerFunMitID = '" + uuID + "', "
					+ "SenSerFunMitSenSerFunID = '" + funuuID + "', "
					+ "SenSerFunMitSenSerID = '" + senSerID + "' "
					+ "WHERE KEY = " + uuID;
		
		try {
			Cassandra.update(CQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String CQLB = "UPDATE " +"SensorServiceFunktion" + " SET "
					+ "SenSerFunID = '" + funuuID + "', "
					+ "SenSerFunNam = '" + funName + "' "
					+ "WHERE KEY = " + funuuID;
		
		try {
			Cassandra.update(CQLB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uuID;
	}
}
