package musicbox;

import java.util.List;

import org.apache.commons.math3.util.Precision;

import musicbox.GetraenkException.ErrorCode;

public class Getraenkeautomat {
	private GetraenkeBox getraenkeBox;
	private Muenzbeutel muenzbeutel;

	public Getraenkeautomat(int maxArtGetranke, int maxGleicheGetraenke, List<Double> preise)
			throws GetraenkException {
		this.getraenkeBox = new GetraenkeBox(maxArtGetranke, maxGleicheGetraenke);
		this.muenzbeutel = new Muenzbeutel();
	}

	public void befuellen(List<Getraenk> getraenke, List<Muenze> muenzen) {
		this.getraenkeBox.befuellen(getraenke);
		this.muenzbeutel.befuellen(muenzen);
	}

	public void entleeren() {
		this.getraenkeBox.entleeren();
		this.muenzbeutel.entleeren();
	}

	public GetraenkUndWechselgeld kaufen(String auswahl, List<Muenze> muenzen)
			 {
		try {
			muenzbeutel.befuellen(muenzen);
			Double getraenkPreis = getraenkeBox.getPreis(auswahl);
			
			getraenkeBox.checkAuswahl(auswahl);
			
			List<Muenze> wechselgeld = getWechselgeld(muenzen, getraenkPreis);
			Getraenk getraenk = getraenkeBox.getGetraenk(auswahl);
			return new GetraenkUndWechselgeld(getraenk, wechselgeld);

		} catch (GetraenkException e) {
			System.out.println(e.getMessage());
			muenzbeutel.getWechselgeld(vonMuenzeZuGeld(muenzen));
			throw e;
		} 

	}

	private List<Muenze> getWechselgeld(List<Muenze> muenzen, Double getraenkPreis)
			 {
		Double eingegebenePreis = vonMuenzeZuGeld(muenzen);
		if (eingegebenePreis < getraenkPreis) {
			throw new GetraenkException(ErrorCode.NICHT_GENUG_GELD, "Nicht genug Geld");
		}
		return getMuenzbeutel().getWechselgeld(Precision.round(eingegebenePreis - getraenkPreis, 2));
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
