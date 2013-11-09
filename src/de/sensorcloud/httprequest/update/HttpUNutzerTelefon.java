package de.sensorcloud.httprequest.update;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.sensorcloud.db.crud.DBNutzerTelefon;
import de.sensorcloud.entitaet.NutzerTelefon;

@Path("/NutzerTelefon")
public class HttpUNutzerTelefon {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {

		return "Enthaelt die Methode(n) :\n\n"
				+ "public String updateNutzerTelefon(String data)\n";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerTelefon(String data) {
		Gson gson = new Gson();
		NutzerTelefon nutzerTelefon = gson.fromJson(data, NutzerTelefon.class);
	
		DBNutzerTelefon.updateNutzerTelefon(nutzerTelefon);

		return "ausgefuehrt";
	}

}
