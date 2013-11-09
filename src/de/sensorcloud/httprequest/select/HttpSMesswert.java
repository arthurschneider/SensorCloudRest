package de.sensorcloud.httprequest.select;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Messwert")
public class HttpSMesswert {
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Messwerte können hier abgefragt werden !";
	}
	
	
	@GET
    @Path("/SenID/{senID}/MesWerNam/{mesWerNam}/MesWerTimYea/{mesWerTimYea}/MesWerTimMon/{mesWerTimMon}/MesWerTimDay/{MesWerTimDay}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMesswerteBySenIDForDay(@PathParam("senID") String senID,
    									  @PathParam("mesWerNam") String mesWerNam,
    									  @PathParam("mesWerTimYea") String mesWerTimYea,
    									  @PathParam("mesWerTimMon") String mesWerTimMon,
    									  @PathParam("MesWerTimDay") String MesWerTimDay) {
		
		return "";
		
	}

}
