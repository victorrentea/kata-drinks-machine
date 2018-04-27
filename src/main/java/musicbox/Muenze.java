package musicbox;

public enum Muenze {
	M200(2.0), M100(1.0), M50(0.50), M20(0.20), M10(0.10);

	private Double wert;

	private Muenze(Double wert) {
		this.wert = wert;
	}

	public Double getWert() {
		return wert;
	}

}
