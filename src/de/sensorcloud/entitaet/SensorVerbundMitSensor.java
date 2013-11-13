package de.sensorcloud.entitaet;

import java.util.ArrayList;

public class SensorVerbundMitSensor {

	private ArrayList<Sensor> sensorList;
	private SensorVerbund sensorVerbund;

	
	public ArrayList<Sensor> getSensorList() {
		return sensorList;
	}

	public void setSensorList(ArrayList<Sensor> sensorList) {
		this.sensorList = sensorList;
	}

	public SensorVerbund getSensorVerbund() {
		return sensorVerbund;
	}

	public void setSensorVerbund(SensorVerbund sensorVerbund) {
		this.sensorVerbund = sensorVerbund;
	}

}
