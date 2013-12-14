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
import de.sensorcloud.entitaet.Feldgeraet;
import de.sensorcloud.entitaet.FeldgeraetList;

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
		ArrayList<Feldgeraet> aktorList = new ArrayList<Feldgeraet>();
		FeldgeraetList list = new FeldgeraetList();
		aktorList = DBAktor.getAktorByNutStaID(nutStaID);
		Gson gson = new Gson();
		list.setList(aktorList);
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}

}
