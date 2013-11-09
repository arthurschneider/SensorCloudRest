package de.sensorcloud.httprequest.select;

import java.util.HashSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBEvent;
import de.sensorcloud.db.crud.DBEventMitglieder;
import de.sensorcloud.db.crud.DBSensor;
import de.sensorcloud.db.crud.DBSensorEvent;
import de.sensorcloud.entitaet.Event;
import de.sensorcloud.helper.Helper;

@Path("/Event")
public class HttpSEvent {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getSensorByNutStaID(@PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEventObjectListByNutStaID(@PathParam("nutStaID") String nutStaID) {
		
		HashSet<String> sensorIDList = new HashSet<String>();
		HashSet<Event> eventSet = new HashSet<Event>();
		Event event = new Event();
			
		sensorIDList = DBSensor.getSensorIDListByNutStaID(nutStaID);
		for (String senID : sensorIDList) {
			String senEveID = DBSensorEvent.getSenEveIDBySenEveQueID(senID);
			String eveID = DBEventMitglieder.getEveMitEveIDByEveMitSenEveID(senEveID);
			event = DBEvent.getEventObjByEventID(eveID);
			
			if (event != null && !Helper.checkObjectInSet(event, eventSet)) {
				eventSet.add(event);
			}
		}
			
		
		if (!eventSet.isEmpty()) {
			
			JsonElement jsonElement = null;
			Gson gson = new Gson();
			jsonElement = gson.toJsonTree(eventSet);
	      
			return jsonElement.toString();
			
		} else {
			
			return "leer";
			
		}

	}

}
