package de.sensorcloud.helpertools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.entitaet.Event;
import de.sensorcloud.entitaet.SensorVerbund;

public class Helper {
	
	public final static long HOUR_IN_MICRO_SEC = 60*60*1000;

	public static boolean checkObjectInSet(SensorVerbund senVerb, HashSet<SensorVerbund> senVerbSet) {
		boolean result = false;

		if (!senVerbSet.isEmpty()) {
			for (SensorVerbund senVerbObj : senVerbSet) {
				if (senVerbObj.getSenVerID().equals(senVerb.getSenVerID())) {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean checkObjectInSet(AktorVerbund aktVerb, HashSet<AktorVerbund> aktVerbSet) {
		boolean result = false;

		if (!aktVerbSet.isEmpty()) {
			for (AktorVerbund senVerbObj : aktVerbSet) {
				if (senVerbObj.getAktVerID().equals(aktVerb.getAktVerID())) {
					result = true;
				}
			}
		}
		return result;
	}

	public static boolean checkObjectInSet(Event event, HashSet<Event> eventSet) {
		boolean result = false;

		if (!eventSet.isEmpty() && event != null) {
			for (Event eventObj : eventSet) {
				if (eventObj.getEveID().equals(event.getEveID())) {
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
