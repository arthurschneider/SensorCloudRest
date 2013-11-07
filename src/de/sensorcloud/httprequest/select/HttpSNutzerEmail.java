package de.sensorcloud.httprequest.select;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.DBNutzerEmail;
import de.sensorcloud.entitaet.NutzerEmail;

@Path("/nutEma")
public class HttpSNutzerEmail {
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getNutzerEmailByID( @PathParam(\"tabelleName\") String tabelleName, @PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/{tabelleName}/id/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerEmailByID( @PathParam("tabelleName") String tabelleName, @PathParam("nutStaID") String nutStaID) {
		ArrayList<NutzerEmail> nutzerEmail = new ArrayList<NutzerEmail>();
		JsonElement jsonElement = null;
		try {
			nutzerEmail = DBNutzerEmail.getNutzerEmailByNutStaID(tabelleName, nutStaID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
        //creates json from messwertListe object
		jsonElement = gson.toJsonTree(nutzerEmail);
        System.out.println("JSON STRING "+jsonElement);
        //create a new JSON object
        return jsonElement.toString();
	}

}
