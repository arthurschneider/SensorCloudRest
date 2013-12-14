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

import de.sensorcloud.db.crud.DBRaum;
import de.sensorcloud.db.crud.DBSensor;
import de.sensorcloud.db.crud.DBSensorVerbund;
import de.sensorcloud.db.crud.DBSensorVerbundMitglieder;
import de.sensorcloud.entitaet.Feldgeraet;
import de.sensorcloud.entitaet.FeldgeraetList;
import de.sensorcloud.entitaet.FeldgeraetVerbund;
import de.sensorcloud.entitaet.FeldgeraetVerbundList;
import de.sensorcloud.entitaet.FeldgeraetVerbundMitFeldgeraet;
import de.sensorcloud.helpertools.Helper;

@Path("/SensorVerbund")
public class HttpSensorVerbund {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "SensorVerbund Service laeuft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorVerbundByNutStaID(@PathParam("nutStaID") String nutStaID) {
	
		ArrayList<Feldgeraet> sensorList = new ArrayList<Feldgeraet>();
		ArrayList<String> senVerbundMitgldrList = new ArrayList<String>();
		ArrayList<FeldgeraetVerbund> senVerbundList = new ArrayList<FeldgeraetVerbund>();
		
		sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
		
		for (Feldgeraet sensor : sensorList) {
			
			senVerbundMitgldrList = DBSensorVerbundMitglieder.getSenVerMitSenVerIDBySenID(sensor.getiD());
			
			for (String senVerMitSenVerID : senVerbundMitgldrList) {
				System.out.println("httpSenverb senVerMitSenVerID : " +senVerMitSenVerID);
				FeldgeraetVerbund senVerb = DBSensorVerbund.getSenVerbBezBySenVerMitSenVerID(senVerMitSenVerID);
				
				if (senVerb != null && !Helper.checkObjectInList(senVerb, senVerbundList)) {
					senVerbundList.add(senVerb);
				}
			}
		}
		FeldgeraetVerbundList list = new FeldgeraetVerbundList();
		list.setList(senVerbundList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/SenVerID/{senVerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorBySenVerID(@PathParam("senVerID") String senVerID) {
		ArrayList<Feldgeraet> sensorList = new ArrayList<Feldgeraet>();
		ArrayList<String> senIDList = new ArrayList<String>();
	
		senIDList = DBSensorVerbundMitglieder.getSenIDBySenVerID(senVerID);
		
		for (String senID : senIDList) {
			Feldgeraet sensor = DBSensor.getSensorBySenID(senID);
			sensor.setRauID(DBRaum.getRauBezByRauID(sensor.getRauID()));
			sensorList.add(sensor);
		}
		FeldgeraetList list = new FeldgeraetList();
		list.setList(sensorList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String createSensorVerbund(String data) {
		Gson gson = new Gson();
		FeldgeraetVerbundMitFeldgeraet sensorVerbundMitSensor = gson.fromJson(data, FeldgeraetVerbundMitFeldgeraet.class);
	
		String senVerID = DBSensorVerbund.createSensorVerbund(sensorVerbundMitSensor.getFeldgeraetVerbund());
		for (Feldgeraet sensor: sensorVerbundMitSensor.getFeldgeraetList()) {
			DBSensorVerbundMitglieder.createSensorVerbundMitglieder(senVerID, sensor);
		}
		
		return senVerID;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addSensorToSensorVerbund(String data) {
		Gson gson = new Gson();
		FeldgeraetVerbundMitFeldgeraet sensorVerbundmitSensor = gson.fromJson(data, FeldgeraetVerbundMitFeldgeraet.class);
	
		for (Feldgeraet sensor: sensorVerbundmitSensor.getFeldgeraetList()) {
			DBSensorVerbundMitglieder.createSensorVerbundMitglieder(sensorVerbundmitSensor.getFeldgeraetVerbund().getVerID(), sensor);
		}
		
		return "ausgefuehrt";
	}

}
