package de.sensorcloud.httprequest.update;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.sensorcloud.db.DBNutzerStammdaten;
import de.sensorcloud.entitaet.NutzerStammdaten;

@Path("/nutSta")
public class HttpUNutzerStammdaten {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {

		return "Enthaelt die Methode(n) :\n\n"
				+ "public String authetifizieren( MultivaluedMap<String, String> loginParams)\n";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerStammdaten(String data) throws SQLException {
		Gson gson = new Gson();
		NutzerStammdaten nutzerStammdaten = gson.fromJson(data, NutzerStammdaten.class);
	
		DBNutzerStammdaten.updateNutzerStammdaten(nutzerStammdaten);

		return "ausgefuehrt";
	}

}
