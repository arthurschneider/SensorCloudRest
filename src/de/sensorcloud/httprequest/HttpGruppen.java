package de.sensorcloud.httprequest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBGruppe;
import de.sensorcloud.db.crud.DBGruppenMitglieder;
import de.sensorcloud.db.crud.DBNutzerEmail;
import de.sensorcloud.db.crud.DBNutzerStammdaten;
import de.sensorcloud.entitaet.Gruppen;
import de.sensorcloud.entitaet.GruppenList;
import de.sensorcloud.entitaet.GruppenMitglied;
import de.sensorcloud.entitaet.Mitglied;
import de.sensorcloud.entitaet.MitgliederList;
import de.sensorcloud.entitaet.NutzerEmail;
import de.sensorcloud.helpertools.Helper;

@Path("/Gruppen")
public class HttpGruppen {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		
		return "Gruppen Service lauft";
	}
	
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGruppeByID(@PathParam("nutStaID") String nutStaID) {
		Gruppen grp = new Gruppen();
		GruppenList grpList = new GruppenList();
		ArrayList<String> idList = new ArrayList<String>();
		
		idList.addAll(DBGruppenMitglieder.getGruMitGruIDByAdrID(nutStaID));
		for (String id : idList) {
			grp = DBGruppe.getGruppeByGruMitGruID(id);
			if (!Helper.checkObjectInList(grp, grpList.getGrpList())) {
				grpList.getGrpList().add(grp);
			}
			
		}
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(grpList);
        return jsonElement.toString();
	}
	
	
	@GET
    @Path("/GruID/{gruID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMitgliederByGruID(@PathParam("gruID") String gruID) {
		MitgliederList mitList = new MitgliederList();
		ArrayList<String> nutzerList = new ArrayList<String>();
		
		nutzerList.addAll(DBGruppenMitglieder.getGruMitNutStaIDByGruID(gruID));
		for (String id : nutzerList) {
			Mitglied mitglied = new Mitglied();
			
			mitglied.setNutzer(DBNutzerStammdaten.getNutzerStammdatenByNutStaID(id));
			
			ArrayList<NutzerEmail>mailList = new ArrayList<NutzerEmail>();
			mailList.addAll(DBNutzerEmail.getNutzerEmailByNutStaID(id));
			mitglied.setMailList(mailList);
			
			mitList.getList().add(mitglied);		
		}
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(mitList);
        return jsonElement.toString();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertMitglied(String data) throws SQLException {
		Gson gson = new Gson();
		
		GruppenMitglied mitglied = gson.fromJson(data, GruppenMitglied.class);
	
		String nutStaID = DBNutzerEmail.getNutEmaNutStaIDbyNutEmaBez(mitglied.getEmail());
		String uuID = DBGruppenMitglieder.insertMitglied(nutStaID, mitglied.getId());
		return uuID;
	}

}
