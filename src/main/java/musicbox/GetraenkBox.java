package musicbox;

import java.util.ArrayList;
import java.util.List;

import musicbox.GetraenkException.ErrorCode;
// it is the responsibility of this class to ensure the following invariant is always TRUE:
// - currentCount <= maxGetRaenke
// - an EMPTY box has null getraenke
public class GetraenkBox {
	private final String code; //that you enter in the keypad
	private final int maxGetraenke; // max count
	
	private int currentGetraenkeCount = 0; 
 	private Getraenk getraenk; // the drink that the customer gets in hand

	// distinction between what CHANGES and what NOT.

 	public GetraenkBox(String code, int maxGetraenke) {
 		this.code = code;
 		this.maxGetraenke = maxGetraenke;
 	}
 	
 	public boolean isFull() {
 		return currentGetraenkeCount == maxGetraenke;
 	}
 	
 	public boolean isEmpty() {
 		return currentGetraenkeCount == 0;
 	}

 	public void entleeren() {
		currentGetraenkeCount = 0;
		getraenk = null;
	}
	
	public void addFirst(Getraenk getraenk) {
		this.getraenk = getraenk;
		currentGetraenkeCount = 1;
	}
	
	public void addOne() {
		if (isFull()) {
			throw new GetraenkException(ErrorCode.KEIN_KAPAZITAET_MEHR,"Es gibt kein Platz für so viele Getränke");
		}
		currentGetraenkeCount ++;
	}
	public void removeOne() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		currentGetraenkeCount --;
		if (currentGetraenkeCount == 0) {
			getraenk = null;
		}
	}


	public Getraenk getGetraenk() {
		return getraenk;
	}

	public String getCode() {
		return code;
	}

	public List<Getraenk> getGetraenke() {
		List<Getraenk> list = new ArrayList<>();
		for (int i = 0; i < currentGetraenkeCount; i++) {
			list.add(getraenk);
		}
		return list; 
	}

	public Double getPrice() {
		if (isEmpty()) {
			throw new IllegalArgumentException();
		}
		return getraenk.getPreis(); 
	}
}
