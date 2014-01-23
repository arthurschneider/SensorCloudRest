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
import de.sensorcloud.db.crud.DBAktorServiceMitglieder;
import de.sensorcloud.entitaet.Aktor;
import de.sensorcloud.entitaet.AktorList;

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
        AktorList list = new AktorList();
        aktorList = DBAktor.getAktorByNutStaID(nutStaID);
        Gson gson = new Gson();
        list.setList(aktorList);
        JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
    }
    
  
    @GET
    @Path("/AktSerID/{aktSerID}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String getAktorListByAktSerID(@PathParam("aktSerID") String aktSerID) {
	    ArrayList<Aktor> aktsorList = new ArrayList<Aktor>();
	    ArrayList<String> senIDList = new ArrayList<String>();
	    JsonElement jsonElement = null;
	    
	    senIDList = DBAktorServiceMitglieder.getServiceIDByAktSerID(aktSerID);
	    
	    for (String id : senIDList) {
	   	 	Aktor akt = new Aktor();
	   	 	akt = DBAktor.getAktorByAktID(id);
	   	 	aktsorList.add(akt);
		}
	    
	    AktorList list = new AktorList();
	    list.setList(aktsorList);
	    Gson gson = new Gson();
	    jsonElement = gson.toJsonTree(list);
	    return jsonElement.toString();
	}
}
