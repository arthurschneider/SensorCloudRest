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

import de.sensorcloud.db.select.DBSNutzerTelefon;
import de.sensorcloud.entitaet.NutzerTelefon;

@Path("/nutTel")
public class HttpSNutzerTelefon {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getNutzerTelefonByID( @PathParam(\"tabelleName\") String tabelleName, @PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/{tabelleName}/id/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerTelefonByID(@PathParam("tabelleName") String tabelleName, @PathParam("nutStaID") String nutStaID) {
		ArrayList<NutzerTelefon> nutzerTelefon = new ArrayList<NutzerTelefon>();
		JsonElement jsonElement = null;
		try {
			nutzerTelefon = DBSNutzerTelefon.getNutzerTelefonByNutStaID(tabelleName, nutStaID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
        //creates json from messwertListe object
		jsonElement = gson.toJsonTree(nutzerTelefon);
        System.out.println("JSON STRING "+jsonElement);
        //create a new JSON object
        return jsonElement.toString();
	}

}
