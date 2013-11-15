package de.sensorcloud.httprequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBMessLinie;
import de.sensorcloud.db.crud.DBMesswert;
import de.sensorcloud.db.crud.DBSensor;
import de.sensorcloud.db.crud.DBSensorProdukt;
import de.sensorcloud.entitaet.DatasetMitSemantik;
import de.sensorcloud.entitaet.MesswertTime;
import de.sensorcloud.entitaet.ParVor;
import de.sensorcloud.entitaet.SensorProduktSemantik;
import de.sensorcloud.helpertools.Helper;

@Path("/Messwert")
public class HttpMesswert {
	

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
		
		Gson gson = new Gson();
		long mesLinTimBeg = 0;
		ArrayList<MesswertTime> messwertTimeList = new ArrayList<MesswertTime>();
		System.out.println(mesWerNam);
		try {
			
			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
			mesLinTimBeg = dfm.parse(mesWerTimYea+"-"+mesWerTimMon+"-"+MesWerTimDay).getTime();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (int hour = 0; hour < 24; hour++) {
			
			long mesLinTimEnd = Helper.getNextHour(mesLinTimBeg);
			String keysWithSemicolon = DBMessLinie.getMesLinMesWerIDsBySenIDAndMesLinTimBegAndMesLinTimEnd(senID, mesLinTimBeg, mesLinTimEnd);
			
			if (keysWithSemicolon != null) {
				messwertTimeList.addAll(DBMesswert.getMesswertByMesWerIDAndMesWerNam(keysWithSemicolon, mesWerNam)) ;
				
			}
			mesLinTimBeg = mesLinTimEnd;
		}	
		
		String senProID = DBSensor.getSenProIDBySenID(senID);
		String senProSem = DBSensorProdukt.getSensorSemantikBySenProID(senProID);
		
		
		SensorProduktSemantik sensorSemantik = gson.fromJson(senProSem, SensorProduktSemantik.class);
		DatasetMitSemantik datesetMitSemantik = new DatasetMitSemantik();
		
		for (ParVor parvor : sensorSemantik.getParvor()) {
			
			if (parvor.getPn().equals(mesWerNam)) {
				datesetMitSemantik.setMesswertTime(messwertTimeList);
				datesetMitSemantik.setParVor(parvor);
			}
			
		}
		
		JsonElement jsonElement = null;
		jsonElement = gson.toJsonTree(datesetMitSemantik);
        return jsonElement.toString();
		
	}

}
