package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBSensor;
import de.sensorcloud.entitaet.Sensor;
import de.sensorcloud.entitaet.SensorList;

@Path("/Sensor")
public class HttpSensor {
	
	 @GET
     @Produces(MediaType.TEXT_PLAIN)
     public String test(){
		 return "Sensor Service laeuft";
     }
     
     
     @GET
	 @Path("/NutStaID/{nutStaID}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public String getSensorListByNutStaID(@PathParam("nutStaID") String nutStaID) {
         ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
         JsonElement jsonElement = null;
         
         sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
         SensorList list = new SensorList();
         list.setSensorList(sensorList);
         Gson gson = new Gson();
         jsonElement = gson.toJsonTree(list);
	     return jsonElement.toString();
     }
}
