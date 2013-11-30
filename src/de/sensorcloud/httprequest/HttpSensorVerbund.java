package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBSensor;
import de.sensorcloud.db.crud.DBSensorVerbund;
import de.sensorcloud.db.crud.DBSensorVerbundMitglieder;
import de.sensorcloud.entitaet.Sensor;
import de.sensorcloud.entitaet.SensorList;
import de.sensorcloud.entitaet.SensorVerbund;
import de.sensorcloud.entitaet.SensorVerbundList;
import de.sensorcloud.entitaet.SensorVerbundMitSensor;
import de.sensorcloud.helpertools.Helper;

@Path("/SensorVerbund")
public class HttpSensorVerbund {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getSensorVerbundByNutStaID(@PathParam(\"nutStaID\") String nutStaID)\n"
				+ " public String getSensorBySensorVerbundID(@PathParam(\"verbundID\") String verbundID)\n";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorVerbundByNutStaID(@PathParam("nutStaID") String nutStaID) {
	
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		ArrayList<String> senVerbundMitgldrList = new ArrayList<String>();
		ArrayList<SensorVerbund> senVerbundList = new ArrayList<SensorVerbund>();
		
		sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
		
		for (Sensor sensor : sensorList) {
			
			senVerbundMitgldrList = DBSensorVerbundMitglieder.getSenVerMitSenVerIDBySenID(sensor.getSenID());
			
			for (String senVerMitSenVerID : senVerbundMitgldrList) {
				
				SensorVerbund senVerb = DBSensorVerbund.getSenVerbBezBySenVerMitSenVerID(senVerMitSenVerID);
				
				if (!Helper.checkObjectInList(senVerb, senVerbundList)) {
					senVerbundList.add(senVerb);
				}
			}
		}
		SensorVerbundList list = new SensorVerbundList();
		list.setSenVerbundList(senVerbundList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/SenVerID/{senVerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorBySenVerID(@PathParam("senVerID") String senVerID) {
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		ArrayList<String> senIDList = new ArrayList<String>();
	
		senIDList = DBSensorVerbundMitglieder.getSenIDBySenVerID(senVerID);
		
		for (String senID : senIDList) {
			sensorList.add(DBSensor.getSensorBySenID(senID));
		}
		SensorList list = new SensorList();
		list.setSensorList(sensorList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String createSensorVerbund(String data) {
		Gson gson = new Gson();
		SensorVerbundMitSensor sensorVerbundMitSensor = gson.fromJson(data, SensorVerbundMitSensor.class);
	
		String senVerID = DBSensorVerbund.createSensorVerbund(sensorVerbundMitSensor.getSensorVerbund());
		for (Sensor sensor: sensorVerbundMitSensor.getSensorList()) {
			DBSensorVerbundMitglieder.createSensorVerbundMitglieder(senVerID, sensor);
		}
		
		return senVerID;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addSensorToSensorVerbund(String data) {
		Gson gson = new Gson();
		SensorVerbundMitSensor sensorVerbundmitSensor = gson.fromJson(data, SensorVerbundMitSensor.class);
	
		for (Sensor sensor: sensorVerbundmitSensor.getSensorList()) {
			DBSensorVerbundMitglieder.createSensorVerbundMitglieder(sensorVerbundmitSensor.getSensorVerbund().getSenVerID(), sensor);
		}
		
		return "ausgefuehrt";
	}

}
