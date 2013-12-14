package de.sensorcloud.helpertools;

import java.util.ArrayList;
import java.util.UUID;

import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.entitaet.Event;
import de.sensorcloud.entitaet.Gruppen;
import de.sensorcloud.entitaet.SensorServiceMitFunktion;
import de.sensorcloud.entitaet.SensorVerbund;

public class Helper {
	
	public final static long HOUR_IN_MICRO_SEC = 60*60*1000;




	public static boolean checkObjectInList(AktorVerbund aktVerb, ArrayList<AktorVerbund> aktVerbList) {
		boolean result = false;

		if (!aktVerbList.isEmpty()) {
			for (AktorVerbund senVerbObj : aktVerbList) {
				if (senVerbObj.getAktVerID().equals(aktVerb.getAktVerID())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public static boolean checkObjectInList(SensorVerbund senVerb, ArrayList<SensorVerbund> senVerbList) {
		boolean result = false;

		if (!senVerbList.isEmpty()) {
			for (SensorVerbund senVerbObj : senVerbList) {
				if (senVerbObj.getSenVerID().equals(senVerb.getSenVerID())) {
					result = true;
				}
			}
		}
		return result;
	}
	

	public static boolean checkObjectInList(Gruppen grp, ArrayList<Gruppen> list) {
		boolean result = false;

		if (!list.isEmpty()) {
			for (Gruppen obj : list) {
				if (grp.getGruID().equals(obj.getGruID())) {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean checkObjectInList(Event event, ArrayList<Event> eventList) {
		boolean result = false;

		if (!eventList.isEmpty() && event != null) {
			for (Event eventObj : eventList) {
				if (eventObj.getEveID().equals(event.getEveID())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public static boolean checkObjectInList(SensorServiceMitFunktion senserv, ArrayList<SensorServiceMitFunktion> senServFunkList) {
		boolean result = false;

		if (!senServFunkList.isEmpty() && senserv != null) {
			for (SensorServiceMitFunktion Obj : senServFunkList) {
				if (Obj.getSenServ().getSenSerID().equals(senserv.getSenServ().getSenSerID())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public static ArrayList<String> splitSemikolon(String textMitSemikolon){
		ArrayList<String> result = new ArrayList<String>();
		
		String[] werteEinzeln = textMitSemikolon.split(";");
		
		for (int i = 0; i < werteEinzeln.length; i++) {
			result.add(werteEinzeln[i]);
		}
		
		return result;
	}
	
	public static long getNextHour(long anfang){
		return anfang + HOUR_IN_MICRO_SEC;
	}
	
	public static String generateUUID(){
		return UUID.randomUUID().toString();
	}
	
	public static String replaceSemikolon(String keysWithSemikolon){
	
		return keysWithSemikolon.replace(";", "', '");
	}
	
}
