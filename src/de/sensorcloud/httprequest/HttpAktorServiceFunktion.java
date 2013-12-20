package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBAktorServiceFunktion;
import de.sensorcloud.db.crud.DBAktorServiceFunktionMitglieder;
import de.sensorcloud.entitaet.AktorServiceFunktion;
import de.sensorcloud.entitaet.AktorServiceFunktionList;

@Path("/AktorServiceFunktion")
public class HttpAktorServiceFunktion {
	
	 @GET
     @Produces(MediaType.TEXT_PLAIN)
     public String test(){
		 return "AktorServiceFunktion Service laeuft";
     }
	
	
	 @GET
	 @Path("/AktSerID/{aktSerID}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String getFunktionListByAktSerID(@PathParam("aktSerID") String aktSerID) {
         ArrayList<AktorServiceFunktion> funktionList = new ArrayList<AktorServiceFunktion>();
         ArrayList<String> funkIDList = new ArrayList<String>();
         JsonElement jsonElement = null;
         
         funkIDList = DBAktorServiceFunktionMitglieder.getFunktionIDListByAktSerID(aktSerID);
         
         for (String id : funkIDList) {
        	 AktorServiceFunktion funk = new AktorServiceFunktion();
        	 funk = DBAktorServiceFunktion.getFunktionByID(id);
        	 funktionList.add(funk);
		}
         AktorServiceFunktionList list = new AktorServiceFunktionList();
         list.setList(funktionList);
         Gson gson = new Gson();
         jsonElement = gson.toJsonTree(list);
	     return jsonElement.toString();
     }

}
