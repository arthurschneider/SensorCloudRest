package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBSensorService;
import de.sensorcloud.db.crud.DBSensorServiceMitglieder;
import de.sensorcloud.db.crud.DBServiceLinienMitglieder;
import de.sensorcloud.entitaet.SensorService;
import de.sensorcloud.entitaet.SensorServiceList;

@Path("/SensorService")
public class HttpSensorService {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "SensorService Service laeuft";
	}
	
//	@GET
//    @Path("/NutStaID/{nutStaID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getSensorServicesByNutStaID(@PathParam("nutStaID") String nutStaID) {
//		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
//		ArrayList<SensorServiceMitFunktion> senServFunkSet = new ArrayList<SensorServiceMitFunktion>();
//		
//		sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
//		
//		for (Sensor sensor : sensorList) {
//			SensorServiceMitFunktion senServFunk = new SensorServiceMitFunktion();
//			
//			String senSerMitSenSerID = DBSensorServiceMitglieder.getSenSerMitSenSerIDBySenSerMitSenID(sensor.getSenID()); 
//			SensorService senServ = DBSensorService.getSensorServiceBySenSerID(senSerMitSenSerID);
//
//			String senSerFunMitSenSerFunID = DBSensorServiceFunktionMitglied.getSenSerFunMitSenSerFunIDBySenSerFunMitSenSerID(senServ.getSenSerID());
//			senServFunk.setSenServFunkt(DBSensorServiceFunktion.getSenSerFunNamBySenSerFunID(senSerFunMitSenSerFunID));
//			senServFunk.setSenServ(senServ);
//			
//			if (!Helper.checkObjectInList(senServFunk, senServFunkSet)) {
//				senServFunkSet.add(senServFunk);
//			}
//		}
//		Gson gson = new Gson();
//		JsonElement jsonElement = gson.toJsonTree(senServFunkSet);
//        return jsonElement.toString();
//	}
	
	
	@GET
    @Path("/SenID/{senID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorServicesBySenID(@PathParam("senID") String senID) {
		ArrayList<String> servIDList = new ArrayList<String>();
		ArrayList<SensorService> senServList = new ArrayList<SensorService>();
		
		servIDList = DBSensorServiceMitglieder.getServiceIDBySenID(senID);
		
		for (String id : servIDList) {
			SensorService serv = new SensorService();
			serv = DBSensorService.getSensorServiceBySenSerID(id);
			senServList.add(serv);
		}
		
		SensorServiceList list = new SensorServiceList();
		list.setList(senServList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	@GET
    @Path("/SerLinID/{serLinID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorServicesBySerLinID(@PathParam("serLinID") String serLinID) {
		ArrayList<String> servIDList = new ArrayList<String>();
		ArrayList<SensorService> senServList = new ArrayList<SensorService>();
		
		servIDList = DBServiceLinienMitglieder.getServiceIDBySerLinID(serLinID);
		
		for (String id : servIDList) {
			SensorService serv = new SensorService();
			serv = DBSensorService.getSensorServiceByID(id);
			senServList.add(serv);
		}
		
		SensorServiceList list = new SensorServiceList();
		list.setList(senServList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}


}
