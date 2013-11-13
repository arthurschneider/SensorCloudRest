package de.sensorcloud.httprequest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerEmail;
import de.sensorcloud.entitaet.NutzerEmail;

@Path("/NutzerEmail")
public class HttpNutzerEmail {
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getNutzerEmailByID( @PathParam(\"tabelleName\") String tabelleName, @PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerEmailByID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<NutzerEmail> nutzerEmail = new ArrayList<NutzerEmail>();
		JsonElement jsonElement = null;
		
		nutzerEmail = DBNutzerEmail.getNutzerEmailByNutStaID(nutStaID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(nutzerEmail);
        return jsonElement.toString();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerEmail(String data) throws SQLException {
		Gson gson = new Gson();
		NutzerEmail nutzerEmail = gson.fromJson(data, NutzerEmail.class);
	
		DBNutzerEmail.updateNutzerEmail(nutzerEmail);

		return "ausgefuehrt";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertNutzerEmail(String data) throws SQLException {
		Gson gson = new Gson();
		NutzerEmail nutzerEmail = gson.fromJson(data, NutzerEmail.class);
	
		String uuID = DBNutzerEmail.insertNutzerEmail(nutzerEmail);

		return uuID;
	}

}
