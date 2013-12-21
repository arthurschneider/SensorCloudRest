package de.sensorcloud.httprequest;

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

import de.sensorcloud.db.crud.DBNutzerSicherheit;
import de.sensorcloud.entitaet.NutzerSicherheit;
import de.sensorcloud.entitaet.NutzerSicherheitList;

@Path("/NutzerSicherheit")
public class HttpNutzerSicherheit {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "NutzerSicherheit Service laeuft";
	}
	
	
	@GET
    @Path("NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerSicherheitByID(@PathParam("nutStaID") String nutStaID) {
		NutzerSicherheitList sicherheitList = new NutzerSicherheitList();
		
		sicherheitList.setList(DBNutzerSicherheit.getNutzerSicherheitByNutStaID(nutStaID));
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(sicherheitList);
        return jsonElement.toString();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateNutzerSicherheit(String data) {
		Gson gson = new Gson();
		NutzerSicherheit nutzerSicherheit = gson.fromJson(data, NutzerSicherheit.class);
	
		DBNutzerSicherheit.updateNutzerSicherheit(nutzerSicherheit);

		return "ausgefuehrt";
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteNutzerSicherheit(String data) {
		Gson gson = new Gson();
		String nutSicID = gson.fromJson(data, String.class);
	
		DBNutzerSicherheit.deleteNutzerSicherheit(nutSicID);

		return "ausgefuehrt";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertNutzerSicherheit(String data) {
		Gson gson = new Gson();
		NutzerSicherheit nutzerSicherheit = gson.fromJson(data, NutzerSicherheit.class);
	
		String uuID = DBNutzerSicherheit.insertNutzerSicherheit(nutzerSicherheit);

		return uuID;
	}

}
