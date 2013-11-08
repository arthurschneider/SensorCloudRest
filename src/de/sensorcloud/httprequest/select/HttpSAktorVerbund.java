package de.sensorcloud.httprequest.select;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.DBAktor;
import de.sensorcloud.db.DBAktorVerbund;
import de.sensorcloud.db.DBAktorVerbundMitglieder;
import de.sensorcloud.entitaet.Aktor;
import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.helper.Helper;

@Path("/aktorVerb")
public class HttpSAktorVerbund {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ " public String getAktorVerbundByNutStaID(@PathParam(\"nutStaID\") String nutStaID)\n"
				+ " public String getAktorByAktorVerbundID(@PathParam(\"verbundID\") String verbundID)\n";
	}
	
	
	@GET
    @Path("/nutzerID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAktorVerbundByNutStaID(@PathParam("nutStaID") String nutStaID) {
	
		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();
		ArrayList<String> aktVerbundMitgldrList = new ArrayList<String>();
		HashSet<AktorVerbund> aktVerbundSet = new HashSet<AktorVerbund>();
		JsonElement jsonElement = null;
		
	
		aktorList = DBAktor.getAktorByNutStaID(nutStaID);
		
		for (Aktor aktor : aktorList) {
			
			aktVerbundMitgldrList = DBAktorVerbundMitglieder.getAktVerMitAktVerIDByAktID(aktor.getAktID());
			
			for (String aktVerMitAktVerID : aktVerbundMitgldrList) {
				
				AktorVerbund aktVerb = DBAktorVerbund.getAktVerbBezByAktVerMitAktVerID(aktVerMitAktVerID);
				
				if (!Helper.checkObjectInSet(aktVerb, aktVerbundSet)) {
					aktVerbundSet.add(aktVerb);
				}
			}
		}
			
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(aktVerbundSet);
        System.out.println("JSON STRING "+jsonElement);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/verbundID/{verbundID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAktorByAktorVerbundID(@PathParam("verbundID") String verbundID) {
		ArrayList<Aktor> aktorList = new ArrayList<Aktor>();
		ArrayList<String> aktIDList = new ArrayList<String>();
		JsonElement jsonElement = null;
	
		aktIDList = DBAktorVerbundMitglieder.getAktIDByAktVerID(verbundID);
		
		for (String aktID : aktIDList) {
			aktorList.add(DBAktor.getAktorByAktID(aktID));
		}
		
		Gson gson = new Gson();
		jsonElement = gson.toJsonTree(aktorList);
        System.out.println("JSON STRING "+jsonElement);
        return jsonElement.toString();
	
	}

}
