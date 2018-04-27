package main.java;

import java.util.List;

import org.apache.commons.math3.util.Precision;

public class Getraenkeautomat {
	private GetraenkeBox getraenkeBox;
	private Muenzbeutel muenzbeutel;

	public Getraenkeautomat(int maxArtGetranke, int maxGleicheGetraenke, List<Double> preise)
			throws IncorrectProvidedParameterException {
		this.getraenkeBox = new GetraenkeBox(maxArtGetranke, maxGleicheGetraenke, preise);
		this.muenzbeutel = new Muenzbeutel();
	}

	public void befuellen(List<Getraenk> getraenke, List<Muenze> muenzen)
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException {
		this.getraenkeBox.befuellen(getraenke);
		this.muenzbeutel.befuellen(muenzen);
	}

	public void entleeren() {
		this.getraenkeBox.entleeren();
		this.muenzbeutel.entleeren();
	}

	public GetraenkUndWechselgeld kaufen(String auswahl, List<Muenze> muenzen)
			throws KeinWechselgeldException, NichtGefundenAuswahlException, NichtGenugGeldException {
		GetraenkUndWechselgeld getraenkUndWechselgeld = new GetraenkUndWechselgeld();
		try {
			this.getMuenzbeutel().befuellen(muenzen);
			Double getraenkPreis = this.getraenkeBox.getPreis(auswahl);
			this.getraenkeBox.checkGetraenk(auswahl);
			List<Muenze> wechselgeld = this.getWechselgeld(muenzen, getraenkPreis);
			Getraenk getraenk = this.getraenkeBox.getGetraenk(auswahl);
			getraenkUndWechselgeld.setGetraenk(getraenk);
			getraenkUndWechselgeld.setMuenzen(wechselgeld);
			return getraenkUndWechselgeld;

		} catch (NichtGefundenAuswahlException e) {
			System.out.println(e.getMessage());
			this.getMuenzbeutel().getWechselgeld(this.vonMuenzeZuGeld(muenzen));
			throw e;
		} catch (NichtGenugGeldException e) {
			System.out.println(e.getMessage());
			this.getMuenzbeutel().getWechselgeld(this.vonMuenzeZuGeld(muenzen));
			throw e;
		} 

	}

	private List<Muenze> getWechselgeld(List<Muenze> muenzen, Double getraenkPreis)
			throws NichtGenugGeldException, KeinWechselgeldException {
		Double eingegebenePreis = this.vonMuenzeZuGeld(muenzen);
		if (eingegebenePreis < getraenkPreis) {
			throw new NichtGenugGeldException("Nicht genug Geld");
		}
		return this.getMuenzbeutel().getWechselgeld(Precision.round(eingegebenePreis - getraenkPreis, 2));
	}

	private Double vonMuenzeZuGeld(List<Muenze> muenzen) {
		Double zurueck = new Double(0);
		for (Muenze muenze : muenzen) {
			zurueck += muenze.getWert();
		}
		return Precision.round(zurueck, 2);
	}

	public GetraenkeBox getGetraenkeBox() {
		return getraenkeBox;
	}

	public void setGetraenkeBox(GetraenkeBox getraenkeBox) {
		this.getraenkeBox = getraenkeBox;
	}

	public Muenzbeutel getMuenzbeutel() {
		return muenzbeutel;
	}

	public void setMuenzbeutel(Muenzbeutel muenzbeutel) {
		this.muenzbeutel = muenzbeutel;
	}

}
