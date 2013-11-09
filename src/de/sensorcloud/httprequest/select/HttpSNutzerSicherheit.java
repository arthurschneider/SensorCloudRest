package de.sensorcloud.httprequest.select;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerSicherheit;
import de.sensorcloud.entitaet.NutzerSicherheit;

@Path("/NutzerSicherheit")
public class HttpSNutzerSicherheit {
	
	
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

}
