package musicbox;

import java.util.List;

import org.apache.commons.math3.util.Precision;

import musicbox.GetraenkException.ErrorCode;

public class Getraenkeautomat {
	private final GetraenkeBox getraenkeBox;
	private final Muenzbeutel muenzbeutel;

	public Getraenkeautomat(int maxArtGetranke, int maxGleicheGetraenke, List<Double> preise) {
		this.getraenkeBox = new GetraenkeBox(maxArtGetranke, maxGleicheGetraenke);
		this.muenzbeutel = new Muenzbeutel();
	}

	public void befuellen(List<Getraenk> getraenke, List<Muenze> muenzen) {
		getraenkeBox.befuellen(getraenke);
		muenzbeutel.befuellen(muenzen);
	}

	public void entleeren() {
		getraenkeBox.entleeren();
		muenzbeutel.entleeren();
	}
	
	// buyer first enters the coins in the machine
	// then selects the product he wants

	public GetraenkUndWechselgeld kaufen(String auswahl, List<Muenze> buyerMuenzen) {
		try {
			muenzbeutel.befuellen(buyerMuenzen);
			Double getraenkPreis = getraenkeBox.getPreisByAuswahl(auswahl);
			
			// compute the change
			List<Muenze> wechselgeld = muenzbeutel.getWechselgeld(buyerMuenzen, getraenkPreis);
			Getraenk getraenk = getraenkeBox.takeGetraenk(auswahl);
			return new GetraenkUndWechselgeld(getraenk, wechselgeld);
		} catch (GetraenkException e) {
			System.out.println(e.getMessage());
			List<Muenze> coins = muenzbeutel.getWechselgeld(buyerMuenzen);
			return new GetraenkUndWechselgeld(null, coins);
		} 
	}

	public GetraenkeBox getGetraenkeBox() {
		return getraenkeBox;
	}

	public Muenzbeutel getMuenzbeutel() {
		return muenzbeutel;
	}

}
