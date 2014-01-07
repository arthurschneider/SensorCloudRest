package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBEvent;
import de.sensorcloud.db.crud.DBEventAktion;
import de.sensorcloud.db.crud.DBEventBenachrichtigung;
import de.sensorcloud.db.crud.DBEventMitglieder;
import de.sensorcloud.db.crud.DBSensor;
import de.sensorcloud.db.crud.DBSensorEvent;
import de.sensorcloud.entitaet.Event;
import de.sensorcloud.entitaet.EventAktion;
import de.sensorcloud.entitaet.EventList;
import de.sensorcloud.entitaet.EventRegel;
import de.sensorcloud.entitaet.SensorEvent;
import de.sensorcloud.helpertools.Helper;

@Path("/Event")
public class HttpEvent {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "Event Servie laeuft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEventObjectListByNutStaID(@PathParam("nutStaID") String nutStaID) {
		
		ArrayList<String> sensorIDList = new ArrayList<String>();
		ArrayList<Event> list = new ArrayList<Event>();
		String eveID = null;
		Event event = new Event();
		
			
		sensorIDList = DBSensor.getSensorIDListByNutStaID(nutStaID);
		
		for (String senID : sensorIDList) {
			String senEveID = null;
			senEveID = DBSensorEvent.getSenEveIDBySenEveQueID(senID);
			
			if (senEveID != null) {
				eveID = DBEventMitglieder.getEveMitEveIDByEveMitSenEveID(senEveID);
				
				if (eveID != null) {
					event = DBEvent.getEventObjByEventID(eveID);
					if (event != null && !Helper.checkObjectInList(event, list)) {
						list.add(event);
					}
				}
			}	
			
		}	
		
		if (!list.isEmpty()) {
			EventList eventList = new EventList();
			eventList.setList(list);
			JsonElement jsonElement = null;
			Gson gson = new Gson();
			jsonElement = gson.toJsonTree(eventList);
	      
			return jsonElement.toString();
			
		} else {
			return "leer";
		}
	}
	
	
	@GET
    @Path("/EveID/{eveID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEventRegelByEveID(@PathParam("eveID") String eveID) {
		EventRegel eventRegel = new EventRegel();
		ArrayList<String> eveMitgldrList = new  ArrayList<String>();
		ArrayList<SensorEvent> senEveList = new ArrayList<SensorEvent>();
		ArrayList<EventAktion> eveAktionList = new ArrayList<EventAktion>();
		
		eventRegel.setEvent(DBEvent.getEventObjByEventID(eveID));
		eventRegel.setEventBen(DBEventBenachrichtigung.getEventBenachrichtigungByEveID(eveID));
		eveMitgldrList  = DBEventMitglieder.getSensorEventListByEveID(eveID);
		for (String senEveID : eveMitgldrList) {
			senEveList.add(DBSensorEvent.getSensorEventBySenEveID(senEveID));
		}
		
		eveAktionList = DBEventAktion.getEventAktionByEveID(eveID);
		
		eventRegel.setSensorEvent(senEveList);
		eventRegel.setEventAktion(eveAktionList);
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(eventRegel);
      
		return jsonElement.toString();
	}
	
//	@PUT		//Insert
//	@Consumes(MediaType.APPLICATION_JSON)
//	public String insertEventRegel(String data) {
//		
//		Gson gson = new Gson();
//		EventRegel eventRegel = gson.fromJson(data, EventRegel.class);
//		int reihenFolge = 0;
//		String eveID = DBEvent.insertEvent(eventRegel.getEvent());
//		
//		for (SensorEvent sensorEvent: eventRegel.getSensorEvent()) {
//			reihenFolge ++;
//			System.out.println("Reihenfolge : " + reihenFolge);
//			String eveMitSenEveID = DBEventMitglieder.insertEventMitglieder(eveID, sensorEvent.getSenEveID(), reihenFolge);
//			DBSensorEvent.insertSensorEvent(eveMitSenEveID, sensorEvent);
//			
//		}
//		for (EventAktion eventAktion : eventRegel.getEventAktion()) {
//			DBEventAktion.insertEventAktion(eveID, eventAktion);
//		}
//		DBEventBenachrichtigung.insertEventBenachrichtigung(eveID, eventRegel.getEventBen());
//		
//		return "ausgefuehrt";
//	}
	
	@POST		//Update
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateEventRegel(String data) {
		
		Gson gson = new Gson();
		EventRegel eventRegel = gson.fromJson(data, EventRegel.class);
		DBEvent.updateEvent(eventRegel.getEvent());
		
		for (SensorEvent sensorEvent: eventRegel.getSensorEvent()) {
			
			DBSensorEvent.updateSensorEvent(sensorEvent);
			
		}
		for (EventAktion eventAktion : eventRegel.getEventAktion()) {
			DBEventAktion.updateEventAktion(eventAktion);
		}
		DBEventBenachrichtigung.updateEventBenachrichtigung(eventRegel.getEventBen());
		
		return "ausgefuehrt";
	}
}
