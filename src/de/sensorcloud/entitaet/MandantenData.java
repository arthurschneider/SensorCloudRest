package de.sensorcloud.entitaet;

public class MandantenData {

	private String ManID;
	private String ManBez;
	private String ManMitNutStaID;
	private NutzerStammdaten nutzerStammdaten;

	
	public String getManID() {
		return ManID;
	}

	public void setManID(String manID) {
		ManID = manID;
	}

	public String getManBez() {
		return ManBez;
	}

	public void setManBez(String manBez) {
		ManBez = manBez;
	}

	public String getManMitNutStaID() {
		return ManMitNutStaID;
	}

	public void setManMitNutStaID(String manMitNutStaID) {
		ManMitNutStaID = manMitNutStaID;
	}

	public NutzerStammdaten getNutzerStammdaten() {
		return nutzerStammdaten;
	}

	public void setNutzerStammdaten(NutzerStammdaten nutzerStammdaten) {
		this.nutzerStammdaten = nutzerStammdaten;
	}

}
