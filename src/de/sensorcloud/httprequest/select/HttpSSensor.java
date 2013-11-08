package de.sensorcloud.httprequest.select;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.DBSensor;
import de.sensorcloud.entitaet.Sensor;

@Path("/sensor")
public class HttpSSensor {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getSensorByNutStaID(@PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/id/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSensorListByNutStaID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		JsonElement jsonElement = null;
		
		sensorList = DBSensor.getSensorListByNutStaID(nutStaID);
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(sensorList);
        return jsonElement.toString();
	}

}
