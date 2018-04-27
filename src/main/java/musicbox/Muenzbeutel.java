package musicbox;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.util.Precision;

import musicbox.GetraenkException.ErrorCode;

public class Muenzbeutel {
	private final Map<Muenze, Integer> muenzenMap = 
			new TreeMap<>(comparing(Muenze::getWert).reversed());
//			(o1, o2) -> - Double.compare(o1.getWert(), o2.getWert())

	public Muenzbeutel() {
		for (Muenze muenze : Muenze.values()) {
			muenzenMap.put(muenze, 0);
		}
	}

	public void befuellen(List<Muenze> muenzen) {
		for (Muenze muenze: muenzen) {
			Integer oldValue = muenzenMap.get(muenze);
			muenzenMap.put(muenze, oldValue + 1);
		}
	}


	private void addMunzeZumBeutel(Muenze muenze) {
		befuellen(asList(muenze));
	}

	public void entleeren() {
		muenzenMap.clear();
	}

	List<Muenze> getMuenzen() {
		List<Muenze> muenzen = new ArrayList<Muenze>();
		for (Map.Entry<Muenze, Integer> entry : muenzenMap.entrySet()) {
			Integer count = entry.getValue();
			Muenze munze = entry.getKey();
			for (int i = 0; i<count; i++) {
				muenzen.add(munze);
			}
		}
		return muenzen;
	}

	public List<Muenze> getWechselgeld(List<Muenze> muenzen, Double getraenkPreis) {
		Double eingegebenePreis = vonMuenzeZuGeld(muenzen);
		if (eingegebenePreis < getraenkPreis) {
			throw new GetraenkException(ErrorCode.NICHT_GENUG_GELD, "Nicht genug Geld");
		}
		return getWechselgeld(Precision.round(eingegebenePreis - getraenkPreis, 2));
	}
	
	public List<Muenze> getWechselgeld(List<Muenze> muenzen)  {
		return getWechselgeld(vonMuenzeZuGeld(muenzen));
	}
	
	private List<Muenze> getWechselgeld(Double geld)  {
		Map<Muenze, Long> usedCoins = new HashMap<>();
		Double remainingToReturn = Precision.round(geld, 2);
		
		for (Muenze munze: muenzenMap.keySet()) {
			Integer coinCount = muenzenMap.get(munze);
			Double coinValue = munze.getWert();

			long numberOfCoinsToTake = Math.round(Math.min(coinCount, remainingToReturn / coinValue)); 
			
			remainingToReturn -= numberOfCoinsToTake * coinValue;
			
			usedCoins.put(munze, numberOfCoinsToTake);
		}
		if (Precision.round(remainingToReturn, 2) != 0) {
			throw new GetraenkException(ErrorCode.KEIN_WECHSELGELD,"Kein Wechselngeld");
		}
		return removeMuenzen(usedCoins);
	}
	
	public static void main(String[] args) {
		Map<Double, List<Muenze>> muenzenMap2 = 
//				new LinkedHashMap<>();
				new TreeMap<Double, List<Muenze>>(Comparator.reverseOrder());
		for (Muenze wert : Muenze.values()) {
			muenzenMap2.put(wert.getWert(), new ArrayList<Muenze>());
		}
		System.out.println(muenzenMap2);
		
		
	}

	private List<Muenze> removeMuenzen(Map<Muenze, Long> usedCoins) {
		List<Muenze> listOfCoins = new ArrayList<>();
		for (Muenze muenze : usedCoins.keySet()) {
			Long coinCount = usedCoins.get(muenze);
			Integer oldCount = muenzenMap.get(muenze);
			muenzenMap.put(muenze, (int) (oldCount - coinCount));
			for (int i =0;i<coinCount;i++) {
				listOfCoins.add(muenze);
			}
		}
		return listOfCoins;
	}

	
	
	private Double vonMuenzeZuGeld(List<Muenze> muenzen) { // Coins to money amount
		Double zurueck = 0d;
		for (Muenze muenze : muenzen) {
			zurueck += muenze.getWert();
		}
		return Precision.round(zurueck, 2);
	}
}
