package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBAktor;
import de.sensorcloud.entitaet.Aktor;

@Path("/Aktor")
public class HttpAktor {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Aktor Service laeuft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAktorByNutStaID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();
		JsonElement jsonElement = null;
		aktorList = DBAktor.getAktorByNutStaID(nutStaID);
		Gson gson = new Gson();
        //creates json from messwertListe object
		jsonElement = gson.toJsonTree(aktorList);
        System.out.println("JSON STRING "+jsonElement);
        //create a new JSON object
        return jsonElement.toString();
	}

}
