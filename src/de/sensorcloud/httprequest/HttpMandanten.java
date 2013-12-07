package de.sensorcloud.httprequest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.sensorcloud.db.crud.DBMandanten;
import de.sensorcloud.db.crud.DBMandantenMitglieder;
import de.sensorcloud.db.crud.DBNutzerStammdaten;
import de.sensorcloud.entitaet.Mandanten;
import de.sensorcloud.entitaet.MandantenData;

@Path("/Mandanten")
public class HttpMandanten {
	
	@GET
    @Path("NutStaID/{nutStaID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMandantenFromUser(@PathParam("nutStaID") String nutStaID) {
		
		MandantenData manData = new MandantenData();
		ArrayList<MandantenData> manDataList = new ArrayList<MandantenData>();
		ArrayList<Mandanten> mandantenList = new ArrayList<Mandanten>();
		
		
		mandantenList = DBMandanten.getMandantenByManNutStaID(nutStaID);
		
		for (Mandanten mandanten : mandantenList) {
			String manMitNutStaID = DBMandantenMitglieder.getManMitNutStaIDByManMitManID(mandanten.getManID());
			manData.setNutzerStammdaten(DBNutzerStammdaten.getNutzerStammdatenByNutStaID(manMitNutStaID));
			manDataList.add(manData);
		}
		
		
		
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(manDataList);
        return jsonElement.toString();
	}
	

}
