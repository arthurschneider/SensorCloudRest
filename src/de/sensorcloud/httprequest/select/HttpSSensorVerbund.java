package de.sensorcloud.httprequest.select;

import java.util.ArrayList;
import java.util.HashSet;

import javax.ws.rs.GET;
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
import de.sensorcloud.entitaet.SensorVerbund;
import de.sensorcloud.helper.Helper;

@Path("/SensorVerbund")
public class HttpSSensorVerbund {
	
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
		HashSet<SensorVerbund> senVerbundSet = new HashSet<SensorVerbund>();
		JsonElement jsonElement = null;
		
		sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
		
		for (Sensor sensor : sensorList) {
			
			senVerbundMitgldrList = DBSensorVerbundMitglieder.getSenVerMitSenVerIDBySenID(sensor.getSenID());
			
			for (String senVerMitSenVerID : senVerbundMitgldrList) {
				
				SensorVerbund senVerb = DBSensorVerbund.getSenVerbBezBySenVerMitSenVerID(senVerMitSenVerID);
				
				if (!Helper.checkObjectInSet(senVerb, senVerbundSet)) {
					senVerbundSet.add(senVerb);
				}
			}
		}
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(senVerbundSet);
        System.out.println("JSON STRING "+jsonElement);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/SenVerID/{senVerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorBySenVerID(@PathParam("senVerID") String senVerID) {
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		ArrayList<String> senIDList = new ArrayList<String>();
		JsonElement jsonElement = null;
	
		senIDList = DBSensorVerbundMitglieder.getSenIDBySenVerID(senVerID);
		
		for (String senID : senIDList) {
			sensorList.add(DBSensor.getSensorBySenID(senID));
		}
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(sensorList);
        System.out.println("JSON STRING "+jsonElement);
        return jsonElement.toString();
	
	}

}
