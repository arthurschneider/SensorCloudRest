package de.sensorcloud.httprequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBAdresse;
import de.sensorcloud.entitaet.Adresse;

@Path("/Adresse")
public class HttpAdresse {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Adresse laueft";
	}
	
	
	@GET
    @Path("/AdrID/{adrID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAdresseByAdrID(@PathParam("adrID") String adrID) {
		Adresse adresse = new Adresse();
		JsonElement jsonElement = null;
		
		adresse = DBAdresse.getAdresseByAdrID(adrID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(adresse);
        return jsonElement.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateAdresse(String data) {
		Gson gson = new Gson();
		Adresse adresse = gson.fromJson(data, Adresse.class);
	
		DBAdresse.updateAdresse(adresse);

		return "ausgefuehrt";
	}
}
