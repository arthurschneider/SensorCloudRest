package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerTelefon;
import de.sensorcloud.entitaet.NutzerTelefon;

@Path("/NutzerTelefon")
public class HttpNutzerTelefon {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getNutzerTelefonByID( @PathParam(\"tabelleName\") String tabelleName, @PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerTelefonByNutStaID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<NutzerTelefon> nutzerTelefon = new ArrayList<NutzerTelefon>();
		JsonElement jsonElement = null;
		
		nutzerTelefon = DBNutzerTelefon.getNutzerTelefonByNutStaID(nutStaID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(nutzerTelefon);
        return jsonElement.toString();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerTelefon(String data) {
		Gson gson = new Gson();
		NutzerTelefon nutzerTelefon = gson.fromJson(data, NutzerTelefon.class);
	
		DBNutzerTelefon.updateNutzerTelefon(nutzerTelefon);

		return "ausgefuehrt";
	}

}
