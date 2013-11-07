package de.sensorcloud.helper;

import java.util.HashSet;

import de.sensorcloud.entitaet.AktorVerbund;
import de.sensorcloud.entitaet.SensorVerbund;

public class Helper {

	public static boolean checkObjectSensorInSet(SensorVerbund senVerb, HashSet<SensorVerbund> senVerbSet) {
		boolean result = false;
		
		if (!senVerbSet.isEmpty()) {
			for( SensorVerbund senVerbObj : senVerbSet){
				if (senVerbObj.getSenVerID().equals(senVerb.getSenVerID())) {
					result = true;
				}
			}
		}
		return result;	
	}
	
	public static boolean checkObjectAktorInSet(AktorVerbund aktVerb, HashSet<AktorVerbund> aktVerbSet) {
		boolean result = false;
		
		if (!aktVerbSet.isEmpty()) {
			for( AktorVerbund senVerbObj : aktVerbSet){
				if (senVerbObj.getAktVerID().equals(aktVerb.getAktVerID())) {
					result = true;
				}
			}
		}
		return result;	
	}
}
