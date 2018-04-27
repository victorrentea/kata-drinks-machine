package main.java;

public enum Muenze {
	M20(2.0), M10(1.0), M05(0.50), M02(0.20), M01(0.10);

	private Double wert;

	Muenze(Double wert) {
		this.wert = wert;
	}

	public Double getWert() {
		return wert;
	}

}
