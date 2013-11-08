package de.sensorcloud.helper;

import java.util.HashSet;

import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.entitaet.Event;
import de.sensorcloud.entitaet.SensorVerbund;

public class Helper {

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
}
