package de.sensorcloud.httprequest.select;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerStammdaten;
import de.sensorcloud.entitaet.NutzerStammdaten;

@Path("/NutzerStammdaten")
public class HttpSNutzerStammdaten {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ "public String getNutzerStammdatenByID(@PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerStammdatenByID(@PathParam("nutStaID") String nutStaID) {
		NutzerStammdaten nutzerStammdaten = new NutzerStammdaten();
		JsonElement jsonElement = null;
		
		nutzerStammdaten = DBNutzerStammdaten.getNutzerStammdatenByID(nutStaID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(nutzerStammdaten);
        return jsonElement.toString();
	}

}
