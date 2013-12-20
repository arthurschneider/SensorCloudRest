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

import de.sensorcloud.db.crud.DBServiceLinien;
import de.sensorcloud.db.crud.DBServiceLinienMitglieder;
import de.sensorcloud.entitaet.AktorServiceMitServiceLinie;
import de.sensorcloud.entitaet.SensorServiceMitServiceLinie;
import de.sensorcloud.entitaet.ServiceLinien;
import de.sensorcloud.entitaet.ServiceLinienList;

@Path("/ServiceLinien")
public class HttpServiceLinien {
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test(){
        return "ServiceLinien Service laeuft";
    }
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getServiceLinienByNutStaID(@PathParam("nutStaID") String nutStaID) {
		ArrayList<ServiceLinien> servLinList = new ArrayList<ServiceLinien>();
	
		servLinList = DBServiceLinien.getServiceLinienListByNutStaID(nutStaID);
		
		ServiceLinienList list = new ServiceLinienList();
		list.setList(servLinList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	 @PUT
     @Consumes(MediaType.APPLICATION_JSON)
     public String createServiceLinien(String data) {
         Gson gson = new Gson();
         SensorServiceMitServiceLinie servMitServLin = gson.fromJson(data, SensorServiceMitServiceLinie.class);
 
         String serLinID = DBServiceLinien.createServiceLinien(servMitServLin.getServiceLinie());
         DBServiceLinienMitglieder.createServiceLinienMitglieder(serLinID, "SensorService", servMitServLin.getService().getSenSerID());
        
         return serLinID;
     }
	 
	 @PUT
	 @Path("/Aktor")
     @Consumes(MediaType.APPLICATION_JSON)
     public String createServiceLinienAktor(String data) {
         Gson gson = new Gson();
         AktorServiceMitServiceLinie servMitServLin = gson.fromJson(data, AktorServiceMitServiceLinie.class);
 
         String serLinID = DBServiceLinien.createServiceLinien(servMitServLin.getServiceLinie());
         DBServiceLinienMitglieder.createServiceLinienMitglieder(serLinID, "AktorService", servMitServLin.getService().getAktSerID());
        
         return serLinID;
     }
	 
	 @POST
     @Consumes(MediaType.APPLICATION_JSON)
     public String addServiceToServiceLinien(String data) {
         Gson gson = new Gson();
         SensorServiceMitServiceLinie servMitServLin = gson.fromJson(data, SensorServiceMitServiceLinie.class);

         DBServiceLinienMitglieder.createServiceLinienMitglieder(servMitServLin.getServiceLinie().getSerLinID(), "SensorService", servMitServLin.getService().getSenSerID());
         
         return "ausgefuehrt";
     }
	 
	 @POST
	 @Path("/Aktor")
     @Consumes(MediaType.APPLICATION_JSON)
     public String addServiceToServiceLinienAktor(String data) {
         Gson gson = new Gson();
         AktorServiceMitServiceLinie servMitServLin = gson.fromJson(data, AktorServiceMitServiceLinie.class);

         DBServiceLinienMitglieder.createServiceLinienMitglieder(servMitServLin.getServiceLinie().getSerLinID(), "AktorService", servMitServLin.getService().getAktSerID());
         
         return "ausgefuehrt";
     }

}
