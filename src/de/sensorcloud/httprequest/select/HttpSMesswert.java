package de.sensorcloud.httprequest.select;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/messwert")
public class HttpSMesswert {
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getSensorByNutStaID(@PathParam(\"nutStaID\") String nutStaID)\n";
	}
	
	
	@GET
    @Path("/senID/{senID}/phyName/{phyName}/jahr/{jahr}/monat/{monat}/tag/{tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMesswerteBySenIDAndDay(@PathParam("senID") String senID,
    									  @PathParam("phyName") String phyName,
    									  @PathParam("jahr") String jahr,
    									  @PathParam("monat") String monat,
    									  @PathParam("tag") String tag) {
		
		return "";
		
	}

}
