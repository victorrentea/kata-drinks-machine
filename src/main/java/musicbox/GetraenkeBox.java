package musicbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import musicbox.GetraenkException.ErrorCode;

public class GetraenkeBox {
	final List<GetraenkBox> getraenkeBoxes = new ArrayList<GetraenkBox>();

	public GetraenkeBox(int littleBoxCount, int maxGleicheGetraenke){
		for (int i = 0; i < littleBoxCount; i++) {
			String code = String.format("%02d", i); // 0 padded if 1 digit only
			getraenkeBoxes.add(new GetraenkBox(code, maxGleicheGetraenke));
		}
	}

	public void befuellen(List<Getraenk> getraenke) {
		for (Getraenk drink : getraenke) {
			addOneGetraenk(drink);
		}
	}

	/**
	 * for each drink in the list:
	 * 1) find if there are any little boxes with this drink already and refill (++) if there is space
	 * 2) if there is no box with that drink OR all the little boxes with that drink are FULL 
	 * 		==> fill an empty little box ; if there is no additional empty little box ==> THROW 
	 */
	private void addOneGetraenk(Getraenk getraenk) {
		Optional<GetraenkBox> boxOpt = findNotFullBoxForThisGetraenk(getraenk);
		if (boxOpt.isPresent()) {
			boxOpt.get().addOne();
			return;
		}
		boxOpt = findEmptyBox();
		GetraenkBox boxToAddTo = boxOpt.orElseThrow(() ->  new GetraenkException(ErrorCode.KEIN_KAPAZITAET_MEHR,"Kein Platz Mehr"));
		boxToAddTo.addFirst(getraenk);
	}
	
	private Optional<GetraenkBox> findEmptyBox() {
		return getraenkeBoxes.stream()
				.filter(box -> box.isEmpty())
				.findFirst();
	}
	
	private Optional<GetraenkBox> findNotFullBoxForThisGetraenk(Getraenk getraenk) {
		return getraenkeBoxes.stream()
				.filter(box -> box.getGetraenk().equals(getraenk))
				.filter(box -> !box.isFull())
				.findFirst();
	}
	
	List<Getraenk> getGetraenke() {
		List<Getraenk> getraenke = new ArrayList<Getraenk>();
		for (GetraenkBox getraenkBox : getraenkeBoxes) {
			getraenke.addAll(getraenkBox.getGetraenke());
		}
		// flatMap here :)
		return getraenke;
	}

	public void entleeren() {
		for (GetraenkBox getraenkBox : getraenkeBoxes) {
			getraenkBox.entleeren();
		}
	}

	public Double getPreisByAuswahl(String auswahl) {
		return getBoxByAuswahl(auswahl).getPrice();
	} 
	
	private GetraenkBox getBoxByAuswahl(String auswahl) { 
		for (GetraenkBox getraenkBox : getraenkeBoxes) {
			if (auswahl.equals(getraenkBox.getCode())) {
				return getraenkBox;
			}
		}
		throw new GetraenkException(ErrorCode.NICHT_GEFUNDEN_AUSWAHL,"Auswahl nicht gültig");
	}

	public Getraenk takeGetraenk(String auswahl) {
		return getBoxByAuswahl(auswahl).removeOne();
	}
}
