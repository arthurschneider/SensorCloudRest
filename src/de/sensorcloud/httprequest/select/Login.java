package de.sensorcloud.httprequest.select;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.select.DBSNutzerEmail;
import de.sensorcloud.db.select.DBSNutzerSicherheit;


@Path("/login")
public class Login{
	
	    private final static String PASSWORT = "passwort";
	    private final static String EMAIL = "email";
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Enthaelt die Methode(n) :\n\n"
				+ "public String authetifizieren( MultivaluedMap<String, String> loginParams)\n";
	}
	
	@POST
	@Path("/authetifizieren")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String authetifizieren( MultivaluedMap<String, String> loginParams) {
		JsonElement jsonObj = null;
		ArrayList<String>  nutStaIDList;
		String loginErfolg = "fehler";
		
		String email = loginParams.getFirst(EMAIL);
	    String passwort = loginParams.getFirst(PASSWORT);
	    
	    try {
	    	
			nutStaIDList = DBSNutzerEmail.getNutEmaNutStaIDbyNutEmaBez(email);
			
			for (String nutStaID : nutStaIDList) {
				
				String nutsicPas = DBSNutzerSicherheit.getNutSicPasbyNutStaID(nutStaID);
				
				if (nutsicPas.equals(passwort)) {
					
					loginErfolg =  "erfolg";
					
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    Gson gson = new Gson();
	    jsonObj = gson.toJsonTree(loginErfolg);
        System.out.println("JSON STRING "+jsonObj);
	    return jsonObj.toString();
		
	}
	

}
