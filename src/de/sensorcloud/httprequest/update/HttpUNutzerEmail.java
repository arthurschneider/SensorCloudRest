package de.sensorcloud.httprequest.update;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.sensorcloud.db.crud.DBNutzerEmail;
import de.sensorcloud.entitaet.NutzerEmail;

@Path("/NutzerEmail")
public class HttpUNutzerEmail {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {

		return "Enthaelt die Methode(n) :\n\n"
				+ "public String updateNutzerEmail(String data)\n";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerEmail(String data) throws SQLException {
		Gson gson = new Gson();
		NutzerEmail nutzerEmail = gson.fromJson(data, NutzerEmail.class);
	
		DBNutzerEmail.updateNutzerEmail(nutzerEmail);

		return "ausgefuehrt";
	}

}
