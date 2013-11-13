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

import de.sensorcloud.db.crud.DBNutzerSicherheit;
import de.sensorcloud.entitaet.NutzerSicherheit;

@Path("/NutzerSicherheit")
public class HttpNutzerSicherheit {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ "public String getNutzerSicherheitByID( @PathParam(\"tabelleName\") String tabelleName, @PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerSicherheitByID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<NutzerSicherheit> nutzerSicherheit = new ArrayList<NutzerSicherheit>();
		JsonElement jsonElement = null;
		
		nutzerSicherheit = DBNutzerSicherheit.getNutzerSicherheitByNutStaID(nutStaID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(nutzerSicherheit);
        return jsonElement.toString();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerSicherheit(String data) {
		Gson gson = new Gson();
		NutzerSicherheit nutzerSicherheit = gson.fromJson(data, NutzerSicherheit.class);
	
		DBNutzerSicherheit.updateNutzerSicherheit(nutzerSicherheit);

		return "ausgefuehrt";
	}

}
