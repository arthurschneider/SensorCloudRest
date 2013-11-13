package de.sensorcloud.httprequest;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerStammdaten;
import de.sensorcloud.entitaet.NutzerStammdaten;

@Path("/NutzerStammdaten")
public class HttpNutzerStammdaten {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ "public String getNutzerStammdatenByID(@PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerStammdatenByNutStaID(@PathParam("nutStaID") String nutStaID) {
		NutzerStammdaten nutzerStammdaten = new NutzerStammdaten();
		JsonElement jsonElement = null;
		
		nutzerStammdaten = DBNutzerStammdaten.getNutzerStammdatenByNutStaID(nutStaID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(nutzerStammdaten);
        return jsonElement.toString();
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerStammdaten(String data) throws SQLException {
		Gson gson = new Gson();
		NutzerStammdaten nutzerStammdaten = gson.fromJson(data, NutzerStammdaten.class);
	
		DBNutzerStammdaten.updateNutzerStammdaten(nutzerStammdaten);

		return "ausgefuehrt";
	}

}
