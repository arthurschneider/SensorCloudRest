package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBSensorServiceFunktion;
import de.sensorcloud.db.crud.DBSensorServiceFunktionMitglieder;
import de.sensorcloud.entitaet.SensorServiceFunktion;
import de.sensorcloud.entitaet.SensorServiceFunktionList;

@Path("/SensorServiceFunktion")
public class HttpSensorServiceFunktion {
	
	 @GET
     @Produces(MediaType.TEXT_PLAIN)
     public String test(){
		 return "SensorServiceFunktion Service laeuft";
     }

	 
	 @GET
	 @Path("/SenSerID/{senSerID}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String getFunktionListBySenSerID(@PathParam("senSerID") String senSerID) {
         ArrayList<SensorServiceFunktion> funktionList = new ArrayList<SensorServiceFunktion>();
         ArrayList<String> funkIDList = new ArrayList<String>();
         JsonElement jsonElement = null;
         
         funkIDList = DBSensorServiceFunktionMitglieder.getFunktionIDListBySenSerID(senSerID);
         
         for (String id : funkIDList) {
        	 SensorServiceFunktion funk = new SensorServiceFunktion();
        	 funk = DBSensorServiceFunktion.getFunktionByID(id);
        	 funktionList.add(funk);
		}
         
         SensorServiceFunktionList list = new SensorServiceFunktionList();
         list.setList(funktionList);
         Gson gson = new Gson();
         jsonElement = gson.toJsonTree(list);
	     return jsonElement.toString();
     }
	 
	 
	
}
