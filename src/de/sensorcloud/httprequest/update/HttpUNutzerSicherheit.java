package de.sensorcloud.httprequest.update;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.sensorcloud.db.crud.DBNutzerSicherheit;
import de.sensorcloud.entitaet.NutzerSicherheit;

@Path("/NutzerSicherheit")
public class HttpUNutzerSicherheit {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {

		return "Enthaelt die Methode(n) :\n\n"
				+ "public String updateNutzerSicherheit(String data)\n";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerSicherheit(String data) {
		Gson gson = new Gson();
		NutzerSicherheit nutzerSicherheit = gson.fromJson(data, NutzerSicherheit.class);
	
		DBNutzerSicherheit.updateNutzerSicherheit(nutzerSicherheit);

		return "ausgefuehrt";
	}
}
