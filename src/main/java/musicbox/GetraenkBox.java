package musicbox;

import java.util.ArrayList;
import java.util.List;

import musicbox.GetraenkException.ErrorCode;

public class GetraenkBox {
	private String code;
	private int maxGetraenke;
	private Double preis;
	private List<Getraenk> getraenke;

	public GetraenkBox(String code, int maxGetraenke, Double preis) {
		this.code = code;
		this.maxGetraenke = maxGetraenke;
		this.preis = preis;
		this.getraenke = new ArrayList<Getraenk>();
	}

	public void befuellen(List<Getraenk> getraenke) throws GetraenkException {
		if (this.checkFreiePlaetzeForGetraenke(getraenke)) {
			for (Getraenk getraenk : getraenke) {
				this.addGetraenk(getraenk);
			}
		} else {
			throw new GetraenkException(ErrorCode.KEIN_KAPAZITAET_MEHR,"Es gibt kein Platz für so viele Getränke");
		}
	}

	private void addGetraenk(Getraenk getraenk) {
		if (this.getraenke.isEmpty() || (!this.getraenke.isEmpty() && this.getraenke.get(0).equals(getraenk))) {
			this.getraenke.add(getraenk);
		} else {
			throw new GetraenkException(ErrorCode.NICHT_GUELTIG_GETRAENK, "Getränksart nicht Gültig");
		}
	}

	public boolean checkFreiePlaetzeForGetraenke(List<Getraenk> getraenke) {
		int freiPlaetze = this.maxGetraenke - this.getraenke.size();
		if (getraenke.size() > freiPlaetze) {
			return false;
		}
		return true;
	}

	public boolean gibtEsFreiePlaetze() {
		if (getraenke.size() >= this.maxGetraenke) {
			return false;
		}
		return true;
	}

	public boolean esIstGueltigGetraenkArt(Getraenk getraenk) {
		if (this.getraenke.isEmpty()) {
			return true;
		} else if (!this.getraenke.isEmpty() && this.getraenke.get(0).equals(getraenk)) {
			return true;
		}
		return false;
	}

	public void entleeren() {
		this.getraenke.clear();
	}

	public int getMaxGetraenke() {
		return maxGetraenke;
	}

	public void setMaxGetraenke(int maxGetraenke) {
		this.maxGetraenke = maxGetraenke;
	}

	public List<Getraenk> getGetraenke() {
		return getraenke;
	}

	public void setGetraenke(List<Getraenk> getraenke) {
		this.getraenke = getraenke;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPreis() {
		return preis;
	}

	public void setPreis(Double preis) {
		this.preis = preis;
	}
}
