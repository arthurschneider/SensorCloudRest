package de.sensorcloud.httprequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBSensorProdukt;
import de.sensorcloud.entitaet.SensorProduktSemantik;

@Path("/SensorProdukt")
public class HttpSensorProdukt {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "SensorProdukt Service laeuft";
	}
	
	@GET
    @Path("/SenProID/{senProID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorListByNutStaID(@PathParam("senProID") String senProID) {
		
		Gson gson = new Gson();
		SensorProduktSemantik sen = gson.fromJson(DBSensorProdukt.getSensorSemantikBySenProID(senProID), SensorProduktSemantik.class);
		
		
		JsonElement jsonElement = gson.toJsonTree(sen);
        return jsonElement.toString();
	}
}
