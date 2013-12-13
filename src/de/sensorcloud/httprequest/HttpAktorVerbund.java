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

import de.sensorcloud.db.crud.DBAktor;
import de.sensorcloud.db.crud.DBAktorVerbund;
import de.sensorcloud.db.crud.DBAktorVerbundMitglieder;
import de.sensorcloud.db.crud.DBRaum;
import de.sensorcloud.entitaet.Aktor;
import de.sensorcloud.entitaet.AktorList;
import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.entitaet.AktorVerbundList;
import de.sensorcloud.entitaet.AktorVerbundMitAktor;
import de.sensorcloud.helpertools.Helper;

@Path("/AktorVerbund")
public class HttpAktorVerbund {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Aktorverbund service laeuft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAktorVerbundByNutStaID(@PathParam("nutStaID") String nutStaID) {
	
		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();
		ArrayList<String> aktVerbundMitgldrList = new ArrayList<String>();
		ArrayList<AktorVerbund> aktVerbundList = new ArrayList<AktorVerbund>();
	
		aktorList = DBAktor.getAktorByNutStaID(nutStaID);
		
		for (Aktor aktor : aktorList) {
			
			aktVerbundMitgldrList = DBAktorVerbundMitglieder.getAktVerMitAktVerIDByAktID(aktor.getAktID());
			
			for (String aktVerMitAktVerID : aktVerbundMitgldrList) {
				
				AktorVerbund aktVerb = DBAktorVerbund.getAktVerbBezByAktVerMitAktVerID(aktVerMitAktVerID);
				
				if (!Helper.checkObjectInList(aktVerb, aktVerbundList)) {
					aktVerbundList.add(aktVerb);
				}
			}
		}
			
		AktorVerbundList list = new AktorVerbundList();
		list.setAktVerbundList(aktVerbundList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/AktVerID/{aktVerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAktorByAktVerID(@PathParam("aktVerID") String aktVerID) {
		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();
		ArrayList<String> aktIDList = new ArrayList<String>();
	
		aktIDList = DBAktorVerbundMitglieder.getAktIDByAktVerID(aktVerID);
		
		for (String aktID : aktIDList) {
			Aktor aktor = DBAktor.getAktorByAktID(aktID);
			aktor.setAktRauID(DBRaum.getRauBezByRauID(aktor.getAktRauID()));
			aktorList.add(aktor);
		}
		AktorList list = new AktorList();
		list.setList(aktorList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	
	}
	
	@PUT //Insert
	@Consumes(MediaType.APPLICATION_JSON)
	public String createAktorVerbund(String data) {
		Gson gson = new Gson();
		AktorVerbundMitAktor aktorVerbundmitAktor = gson.fromJson(data, AktorVerbundMitAktor.class);
	
		String aktVerID = DBAktorVerbund.createAktorVerbund(aktorVerbundmitAktor.getAktorVerbund());
		for (Aktor aktor: aktorVerbundmitAktor.getAktorList()) {
			DBAktorVerbundMitglieder.createAktorVerbundMitglieder(aktVerID, aktor);
		}
		
		return aktVerID;
	}
	
	@POST	//Update
	@Consumes(MediaType.APPLICATION_JSON)
	public String addAktorToAktorVerbund(String data) {
		Gson gson = new Gson();
		AktorVerbundMitAktor aktorVerbundmitAktor = gson.fromJson(data, AktorVerbundMitAktor.class);
	
		for (Aktor aktor: aktorVerbundmitAktor.getAktorList()) {
			DBAktorVerbundMitglieder.createAktorVerbundMitglieder(aktorVerbundmitAktor.getAktorVerbund().getAktVerID(), aktor);
		}
		
		return "ausgefuehrt";
	}

}
