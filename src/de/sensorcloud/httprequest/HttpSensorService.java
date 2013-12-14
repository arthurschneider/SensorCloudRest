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
import de.sensorcloud.db.crud.DBSensorService;
import de.sensorcloud.db.crud.DBSensorServiceFunktion;
import de.sensorcloud.db.crud.DBSensorServiceFunktionMitglied;
import de.sensorcloud.db.crud.DBSensorServiceMitglied;
import de.sensorcloud.entitaet.Feldgeraet;
import de.sensorcloud.entitaet.SensorService;
import de.sensorcloud.entitaet.SensorServiceMitFunktion;
import de.sensorcloud.helpertools.Helper;

@Path("/SensorService")
public class HttpSensorService {
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorServicesByNutStaID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<Feldgeraet> sensorList = new ArrayList<Feldgeraet>();
		ArrayList<SensorServiceMitFunktion> senServFunkSet = new ArrayList<SensorServiceMitFunktion>();
		
		sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
		
		for (Feldgeraet sensor : sensorList) {
			SensorServiceMitFunktion senServFunk = new SensorServiceMitFunktion();
			
			String senSerMitSenSerID = DBSensorServiceMitglied.getSenSerMitSenSerIDBySenSerMitSenID(sensor.getiD()); 
			SensorService senServ = DBSensorService.getSensorServiceBySenSerID(senSerMitSenSerID);

			String senSerFunMitSenSerFunID = DBSensorServiceFunktionMitglied.getSenSerFunMitSenSerFunIDBySenSerFunMitSenSerID(senServ.getSenSerID());
			senServFunk.setSenServFunkt(DBSensorServiceFunktion.getSenSerFunNamBySenSerFunID(senSerFunMitSenSerFunID));
			senServFunk.setSenServ(senServ);
			
			if (!Helper.checkObjectInList(senServFunk, senServFunkSet)) {
				senServFunkSet.add(senServFunk);
			}
		}
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(senServFunkSet);
        return jsonElement.toString();
	}

}
