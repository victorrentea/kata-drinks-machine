package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetraenkeBox {
	private List<GetraenkBox> getraenkeBoxes;

	public GetraenkeBox(int maxArtGetranke, int maxGleicheGetraenke, List<Double> preise) throws IncorrectProvidedParameterException {
		this.checkPreiseMenge(maxArtGetranke, preise);
		this.getraenkeBoxes = new ArrayList<GetraenkBox>();
		for (int i = 0; i < maxArtGetranke; i++) {
			GetraenkBox getraenkBox = new GetraenkBox(Integer.toString(i), maxGleicheGetraenke, preise.get(i));
			this.getraenkeBoxes.add(getraenkBox);
		}
	}

	private void checkPreiseMenge(int maxArtGetranke, List<Double> preise) throws IncorrectProvidedParameterException {
		if(maxArtGetranke != preise.size()) {
			throw new IncorrectProvidedParameterException("Jedes Getränk solltet einen Preis haben");
		}
	}

	public void befuellen(List<Getraenk> getraenke) throws NichtGueltigGetraenkException, KeinKapazitaetMehrException {
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
					throw new KeinKapazitaetMehrException("Kein Platz Mehr");
				}
				throw new NichtGueltigGetraenkException("Getränkart nicht gültig");
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

	public Double getPreis(String auswahl) throws NichtGefundenAuswahlException {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				return getraenkBox.getPreis();
			}
		}
		throw new NichtGefundenAuswahlException("Auswahl nicht gültig");
	}

	public void checkGetraenk(String auswahl) throws NichtGefundenAuswahlException {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				return;
			}
		}
		throw new NichtGefundenAuswahlException("Auswahl nicht gültig");
	}

	public Getraenk getGetraenk(String auswahl) throws NichtGefundenAuswahlException {
		for (GetraenkBox getraenkBox : this.getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				Getraenk zuruck = getraenkBox.getGetraenke().get(0);
				getraenkBox.getGetraenke().remove(0);
				return zuruck;
			}
		}
		throw new NichtGefundenAuswahlException("Auswahl nicht gültig");
	}
}
