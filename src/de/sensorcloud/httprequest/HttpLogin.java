package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBNutzerEmail;
import de.sensorcloud.db.crud.DBNutzerSicherheit;
import de.sensorcloud.db.crud.DBNutzerStammdaten;
import de.sensorcloud.entitaet.Login;
import de.sensorcloud.entitaet.NutzerStammdaten;


@Path("/Login")
public class HttpLogin{
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ "public String authetifizieren( MultivaluedMap<String, String> loginParams)\n";
	}
	
	@POST
	@Path("/authetifizieren")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String authetifizieren(String data) {
		JsonElement jsonObj = null;
		ArrayList<String>  nutStaIDList;
		String nutzerID = "";
		
		Gson gson = new Gson();
        Login login = gson.fromJson(data, Login.class);
	    
		nutStaIDList = DBNutzerEmail.getNutEmaNutStaIDbyNutEmaBez(login.getEmail());
		
		for (String nutStaID : nutStaIDList) {
			
			String nutsicPas = DBNutzerSicherheit.getNutSicPasByNutStaID(nutStaID);
			
			if (nutsicPas.equals(login.getPasswort())) {
				
				nutzerID =  nutStaID;
				
			}
		}
		
		NutzerStammdaten nutzer = DBNutzerStammdaten.getNutzerStammdatenByNutStaID(nutzerID);
		
	    jsonObj = gson.toJsonTree(nutzer);
	    return jsonObj.toString();
		
	}
	

}
