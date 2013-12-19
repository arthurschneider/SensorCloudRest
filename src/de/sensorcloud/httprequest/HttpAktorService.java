package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBAktorService;
import de.sensorcloud.db.crud.DBAktorServiceMitglieder;
import de.sensorcloud.entitaet.AktorService;
import de.sensorcloud.entitaet.AktorServiceList;

@Path("/AktorService")
public class HttpAktorService {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "AktorService Service laeuft";
	}
	
	@GET
    @Path("/AktID/{aktID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorServicesBySenID(@PathParam("aktID") String aktID) {
		ArrayList<String> servIDList = new ArrayList<String>();
		ArrayList<AktorService> aktServList = new ArrayList<AktorService>();
		
		servIDList = DBAktorServiceMitglieder.getServiceIDByAktID(aktID);
		
		for (String id : servIDList) {
			AktorService serv = new AktorService();
			serv = DBAktorService.getAktorServiceByAktSerID(id);
			aktServList.add(serv);
		}
		
		AktorServiceList list = new AktorServiceList();
		list.setList(aktServList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}

}
