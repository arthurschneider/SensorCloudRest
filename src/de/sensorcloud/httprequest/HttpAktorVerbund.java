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
import de.sensorcloud.entitaet.Feldgeraet;
import de.sensorcloud.entitaet.FeldgeraetList;
import de.sensorcloud.entitaet.FeldgeraetVerbund;
import de.sensorcloud.entitaet.FeldgeraetVerbundList;
import de.sensorcloud.entitaet.FeldgeraetVerbundMitFeldgeraet;
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
	
		ArrayList<Feldgeraet> aktorList = new ArrayList<Feldgeraet>();
		ArrayList<String> aktVerbundMitgldrList = new ArrayList<String>();
		ArrayList<FeldgeraetVerbund> aktVerbundList = new ArrayList<FeldgeraetVerbund>();
	
		aktorList = DBAktor.getAktorByNutStaID(nutStaID);
		
		for (Feldgeraet aktor : aktorList) {
			
			aktVerbundMitgldrList = DBAktorVerbundMitglieder.getAktVerMitAktVerIDByAktID(aktor.getiD());
			
			for (String aktVerMitAktVerID : aktVerbundMitgldrList) {
				
				FeldgeraetVerbund aktVerb = DBAktorVerbund.getAktVerbBezByAktVerMitAktVerID(aktVerMitAktVerID);
				
				if (!Helper.checkObjectInList(aktVerb, aktVerbundList)) {
					aktVerbundList.add(aktVerb);
				}
			}
		}
			
		FeldgeraetVerbundList list = new FeldgeraetVerbundList();
		list.setList(aktVerbundList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/AktVerID/{aktVerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAktorByAktVerID(@PathParam("aktVerID") String aktVerID) {
		ArrayList<Feldgeraet> aktorList = new ArrayList<Feldgeraet>();
		ArrayList<String> aktIDList = new ArrayList<String>();
	
		aktIDList = DBAktorVerbundMitglieder.getAktIDByAktVerID(aktVerID);
		
		for (String aktID : aktIDList) {
			Feldgeraet aktor = DBAktor.getAktorByAktID(aktID);
			aktor.setRauID(DBRaum.getRauBezByRauID(aktor.getRauID()));
			aktorList.add(aktor);
		}
		FeldgeraetList list = new FeldgeraetList();
		list.setList(aktorList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(list);
        return jsonElement.toString();
	
	}
	
	@PUT //Insert
	@Consumes(MediaType.APPLICATION_JSON)
	public String createAktorVerbund(String data) {
		Gson gson = new Gson();
		FeldgeraetVerbundMitFeldgeraet aktorVerbundmitAktor = gson.fromJson(data, FeldgeraetVerbundMitFeldgeraet.class);
	
		String aktVerID = DBAktorVerbund.createAktorVerbund(aktorVerbundmitAktor.getFeldgeraetVerbund());
		for (Feldgeraet aktor: aktorVerbundmitAktor.getFeldgeraetList()) {
			DBAktorVerbundMitglieder.createAktorVerbundMitglieder(aktVerID, aktor);
		}
		
		return aktVerID;
	}
	
	@POST	//Update
	@Consumes(MediaType.APPLICATION_JSON)
	public String addAktorToAktorVerbund(String data) {
		Gson gson = new Gson();
		FeldgeraetVerbundMitFeldgeraet aktorVerbundmitAktor = gson.fromJson(data, FeldgeraetVerbundMitFeldgeraet.class);
	
		for (Feldgeraet aktor: aktorVerbundmitAktor.getFeldgeraetList()) {
			DBAktorVerbundMitglieder.createAktorVerbundMitglieder(aktorVerbundmitAktor.getFeldgeraetVerbund().getVerID(), aktor);
		}
		
		return "ausgefuehrt";
	}

}
