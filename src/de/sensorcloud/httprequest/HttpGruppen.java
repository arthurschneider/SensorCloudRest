package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBGruppen;
import de.sensorcloud.db.crud.DBGruppenMitglieder;
import de.sensorcloud.db.crud.DBNutzerEmail;
import de.sensorcloud.db.crud.DBNutzerStammdaten;
import de.sensorcloud.entitaet.Gruppen;
import de.sensorcloud.entitaet.GruppenList;
import de.sensorcloud.entitaet.GruppenMitglied;
import de.sensorcloud.entitaet.Mitglied;
import de.sensorcloud.entitaet.MitgliederList;
import de.sensorcloud.entitaet.NeueGruppeMitNutzer;
import de.sensorcloud.entitaet.NutStaIDAndGruID;
import de.sensorcloud.entitaet.NutzerEmail;
import de.sensorcloud.helpertools.Helper;

@Path("/Gruppen")
public class HttpGruppen {
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "Gruppen Service laeuft";
	}
	
	@GET
    @Path("/NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGruppeByID(@PathParam("nutStaID") String nutStaID) {
		Gruppen grp = new Gruppen();
		GruppenList grpList = new GruppenList();
		ArrayList<Gruppen> gruppen  = new ArrayList<Gruppen>();
		ArrayList<String> idList = new ArrayList<String>();
		
		idList.addAll(DBGruppenMitglieder.getGruMitGruIDByNutStaID(nutStaID));
		for (String id : idList) {
			grp = DBGruppen.getGruppeByGruMitGruID(id);
			if (grp != null && !Helper.checkObjectInList(grp, gruppen)) {
				gruppen.add(grp);
			}
			
		}
		grpList.setGrpList(gruppen);
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
		ArrayList<Mitglied> mitgliederList = new ArrayList<Mitglied>();
		nutzerList.addAll(DBGruppenMitglieder.getGruMitNutStaIDByGruID(gruID));
		for (String id : nutzerList) {
			Mitglied mitglied = new Mitglied();
			
			mitglied.setNutzer(DBNutzerStammdaten.getNutzerStammdatenByNutStaID(id));
			
			ArrayList<NutzerEmail>mailList = new ArrayList<NutzerEmail>();
			mailList.addAll(DBNutzerEmail.getNutzerEmailByNutStaID(id));
			mitglied.setMailList(mailList);
			mitgliederList.add(mitglied);
					
		}
		mitList.setList(mitgliederList);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(mitList);
        return jsonElement.toString();
	}
	
	@PUT
	@Path("/inviteMitglied")
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertMitglied(String data){
		Gson gson = new Gson();
		GruppenMitglied mitglied = gson.fromJson(data, GruppenMitglied.class);
	
		String nutStaID = DBNutzerEmail.getNutEmaNutStaIDbyNutEmaBez(mitglied.getEmail());
		if (nutStaID == null) {
			return "Es gibt keinen Nutzer mit dieser E-Mail";
		}
		String uuID = DBGruppenMitglieder.insertMitglied(nutStaID, mitglied.getId());
		return uuID;
	}
	
	@PUT
	@Path("/createGruppe")
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertGruppe(String data){
		Gson gson = new Gson();
		
		NeueGruppeMitNutzer gruMitNutzer = gson.fromJson(data, NeueGruppeMitNutzer.class);
	
		String gruID = DBGruppen.createGruppe(gruMitNutzer.getGruBez());
		String uuID = DBGruppenMitglieder.insertMitglied(gruMitNutzer.getNutStaID(), gruID);
		return uuID;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteGruppePOST(String data) {
		Gson gson = new Gson();
		NutStaIDAndGruID gruNut = gson.fromJson(data, NutStaIDAndGruID.class);
		String key = DBGruppenMitglieder.getKeyByGruIDAndGruNutStaID(gruNut.getNutStaID(), gruNut.getGruID());
		DBGruppenMitglieder.verlassenGruppe(key);
		return "Gruppe verlassen";
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteGruppe(String data) {
		Gson gson = new Gson();
		NutStaIDAndGruID gruNut = gson.fromJson(data, NutStaIDAndGruID.class);
		String key = DBGruppenMitglieder.getKeyByGruIDAndGruNutStaID(gruNut.getNutStaID(), gruNut.getGruID());
		DBGruppenMitglieder.verlassenGruppe(key);
		return "Gruppe verlassen";
	}

}
