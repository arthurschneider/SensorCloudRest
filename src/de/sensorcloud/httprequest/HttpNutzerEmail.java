package de.sensorcloud.httprequest;

import java.sql.SQLException;

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

import de.sensorcloud.db.crud.DBNutzerEmail;
import de.sensorcloud.entitaet.NutzerEmail;
import de.sensorcloud.entitaet.NutzerEmailList;

@Path("/NutzerEmail")
public class HttpNutzerEmail {
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "NutzerEmail Serveice läuft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNutzerEmailByID(@PathParam("nutStaID") String nutStaID) {
		NutzerEmailList  list = new NutzerEmailList();
		JsonElement jsonElement = null;
		
		list.setList(DBNutzerEmail.getNutzerEmailByNutStaID(nutStaID));
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(list);
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
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String delAppNutzerEmail(String data) throws SQLException {
		Gson gson = new Gson();
		String nutEmaID = gson.fromJson(data, String.class);
	
		DBNutzerEmail.deleteNutzerEmail(nutEmaID);

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
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteNutzerEmail(String data) throws SQLException {
		Gson gson = new Gson();
		String nutEmaID = gson.fromJson(data, String.class);
	
		DBNutzerEmail.deleteNutzerEmail(nutEmaID);

		return "ausgefuehrt";
	}

}
