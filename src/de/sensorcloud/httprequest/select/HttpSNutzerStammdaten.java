package de.sensorcloud.httprequest.select;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.DBNutzerStammdaten;
import de.sensorcloud.entitaet.NutzerStammdaten;

@Path("/nutSta")
public class HttpSNutzerStammdaten {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ "public String authetifizieren( MultivaluedMap<String, String> loginParams)\n";
	}
	
	
	@GET
    @Path("nutzerID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerStammdatenByID(@PathParam("nutStaID") String nutStaID) {
		NutzerStammdaten nutzerStammdaten = new NutzerStammdaten();
		JsonElement jsonElement = null;
		try {
			nutzerStammdaten = DBNutzerStammdaten.getNutzerStammdatenByID(nutStaID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
        //creates json from messwertListe object
		jsonElement = gson.toJsonTree(nutzerStammdaten);
        System.out.println("JSON STRING "+jsonElement);
        //create a new JSON object
        return jsonElement.toString();
	}

}
