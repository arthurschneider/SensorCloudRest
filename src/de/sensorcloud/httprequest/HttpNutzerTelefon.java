package de.sensorcloud.httprequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerTelefon;
import de.sensorcloud.entitaet.NutzerTelefon;
import de.sensorcloud.entitaet.NutzerTelefonList;

@Path("/NutzerTelefon")
public class HttpNutzerTelefon {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "NutzerTelefon lauft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerTelefonByNutStaID(@PathParam("nutStaID") String nutStaID) {
		NutzerTelefonList list = new NutzerTelefonList();
		JsonElement jsonElement = null;
		
		list.setList(DBNutzerTelefon.getNutzerTelefonByNutStaID(nutStaID));
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerTelefon(String data) {
		Gson gson = new Gson();
		NutzerTelefon nutzerTelefon = gson.fromJson(data, NutzerTelefon.class);
	
		DBNutzerTelefon.updateNutzerTelefon(nutzerTelefon);

		return "ausgefuehrt";
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteNutzerTelefonPOST(String data) {
		Gson gson = new Gson();
		String nutTelID = gson.fromJson(data, String.class);
	
		DBNutzerTelefon.deleteNutzerTelefon(nutTelID);

		return "ausgefuehrt";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertNutzerTelefon(String data) {
		Gson gson = new Gson();
		NutzerTelefon nutzerTelefon = gson.fromJson(data, NutzerTelefon.class);
	
		String uuID = DBNutzerTelefon.insertNutzerTelefon(nutzerTelefon);

		return uuID;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteNutzerTelefon(String data) {
		Gson gson = new Gson();
		String nutTelID = gson.fromJson(data, String.class);
	
		DBNutzerTelefon.deleteNutzerTelefon(nutTelID);

		return "ausgefuehrt";
	}

}
