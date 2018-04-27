package musicbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Precision;

import musicbox.GetraenkException.ErrorCode;

public class Muenzbeutel {
	private Map<Double, List<Muenze>> muenzenMap;

	public Muenzbeutel() {
		this.muenzenMap = new HashMap<Double, List<Muenze>>();
		for (Muenze wert : Muenze.values()) {
			this.muenzenMap.put(wert.getWert(), new ArrayList<Muenze>());
		}
	}

	public void befuellen(List<Muenze> muenzen) {
		this.addMunzenZumBeutel(muenzen);
	}

	public void addMunzenZumBeutel(List<Muenze> muenzen) {
		for (Muenze muenze : muenzen) {
			this.addMunzeZumBeutel(muenze);
		}
	}

	private void addMunzeZumBeutel(Muenze muenze) {
		this.muenzenMap.get(muenze.getWert()).add(muenze);
	}

	public void entleeren() {
		muenzenMap.clear();
	}

	public List<Muenze> getMuenzen() {
		List<Muenze> muenzen = new ArrayList<Muenze>();
		for (Map.Entry<Double, List<Muenze>> entry : this.muenzenMap.entrySet()) {
			List<Muenze> value = entry.getValue();
			muenzen.addAll(value);
		}
		return muenzen;
	}

	public Map<Double, List<Muenze>> getMuenzenMap() {
		return muenzenMap;
	}

	public void setMuenzenMap(Map<Double, List<Muenze>> muenzen) {
		this.muenzenMap = muenzen;
	}

	public List<Muenze> getWechselgeld(Double geld)  {
		List<Muenze> zurueck = new ArrayList<Muenze>();
		Double restlich = Precision.round(geld, 2);
		for (Map.Entry<Double, List<Muenze>> entry : this.muenzenMap.entrySet()) {
			Double muenzeWert = Precision.round(entry.getKey(), 2);
			int muenzeMenge = entry.getValue().size();
			int index = 0;
			while (muenzeWert <= restlich && restlich > 0 && muenzeMenge > 0) {
				if (muenzeWert <= restlich && muenzeMenge > 0) {
					restlich = Precision.round(restlich - muenzeWert, 2);
					muenzeMenge--;
					zurueck.add(entry.getValue().get(index));
					index++;
				}
			}
		}
		if (restlich != 0) {
			throw new GetraenkException(ErrorCode.KEIN_WECHSELGELD,"Kein Wechselngeld");
		}
		this.removeMuenzen(zurueck);
		return zurueck;
	}

	private void removeMuenzen(List<Muenze> muenzen) {
		for (Muenze muenze : muenzen) {
			this.muenzenMap.get(muenze.getWert()).remove(0);
		}
	}

}
