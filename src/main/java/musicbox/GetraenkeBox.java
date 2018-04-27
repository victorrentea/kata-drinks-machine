package musicbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import musicbox.GetraenkException.ErrorCode;

public class GetraenkeBox {
	private List<GetraenkBox> getraenkeBoxes;

	public GetraenkeBox(int maxArtGetranke, int maxGleicheGetraenke, List<Double> preise) throws GetraenkException {
		this.checkPreiseMenge(maxArtGetranke, preise);
		this.getraenkeBoxes = new ArrayList<GetraenkBox>();
		for (int i = 0; i < maxArtGetranke; i++) {
			GetraenkBox getraenkBox = new GetraenkBox(Integer.toString(i), maxGleicheGetraenke, preise.get(i));
			this.getraenkeBoxes.add(getraenkBox);
		}
	}

	private void checkPreiseMenge(int maxArtGetranke, List<Double> preise) throws GetraenkException {
		if(maxArtGetranke != preise.size()) {
			throw new GetraenkException(
					ErrorCode.INCORRECT_PROVIDED_PARAMETER, "Jedes Getränk solltet einen Preis haben");
		}
	}

	public void befuellen(List<Getraenk> getraenke) {
		for (Getraenk getraenk : getraenke) {
			boolean found = false;
			boolean foundType = false;
			for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
				if (getraenkBox.esIstGueltigGetraenkArt(getraenk)) {
					foundType = true;
					if (getraenkBox.gibtEsFreiePlaetze()) {
						List<Getraenk> glist = Arrays.asList(getraenk);
						getraenkBox.befuellen(glist);
						found = true;
						break;
					}
				}
			}
			if (!found) {
				if (foundType) {
					throw new GetraenkException(ErrorCode.KEIN_KAPAZITAET_MEHR,"Kein Platz Mehr");
				}
				throw new GetraenkException(ErrorCode.NICHT_GUELTIG_GETRAENK,"Getränkart nicht gültig");
			}
		}
	}

	public List<Getraenk> getGetraenke() {
		List<Getraenk> getraenke = new ArrayList<Getraenk>();
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			getraenke.addAll(getraenkBox.getGetraenke());
		}
		return getraenke;
	}

	public List<GetraenkBox> getGetraenkeBoxes() {
		return getraenkeBoxes;
	}

	public void setGetraenkeBoxes(List<GetraenkBox> getraenke) {
		this.getraenkeBoxes = getraenke;
	}

	public void entleeren() {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			getraenkBox.entleeren();
		}

	}

	public Double getPreis(String auswahl) {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				return getraenkBox.getPreis();
			}
		}
		throw new GetraenkException(ErrorCode.NICHT_GEFUNDEN_AUSWAHL,"Auswahl nicht gültig");
	}

	public void checkGetraenk(String auswahl) {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				return;
			}
		}
		throw new GetraenkException(ErrorCode.NICHT_GEFUNDEN_AUSWAHL,"Auswahl nicht gültig");
	}

	public Getraenk getGetraenk(String auswahl) {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				Getraenk zuruck = getraenkBox.getGetraenke().get(0);
				getraenkBox.getGetraenke().remove(0);
				return zuruck;
			}
		}
		throw new GetraenkException(ErrorCode.NICHT_GEFUNDEN_AUSWAHL,"Auswahl nicht gültig");
	}
}
