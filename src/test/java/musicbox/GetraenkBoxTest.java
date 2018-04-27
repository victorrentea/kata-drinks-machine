package musicbox;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class GetraenkBoxTest {

	private Getraenk cola = new Getraenk("Cola", 0.5, 1d);
	private Getraenk getraenkeCola2 = new Getraenk("Cola", 0.5, 1d);
	List<Getraenk> getraenkeListColas = asList(cola, getraenkeCola2);

//	@Test(expected = NichtGueltigGetraenkException.class)
//	public void addGetraenkeErrorGetraenkArt() throws Exception {
//		List<Getraenk> getraenkeListMix = new ArrayList<Getraenk>();
//		getraenkeListMix.add(this.getraenkeCola);
//		getraenkeListMix.add(this.getraenkeMezzoMix);
//		GetraenkBox getraenkBox = new GetraenkBox("01", 10, 1.20);
//		getraenkBox.befuellen(this.getraenkeListMix);
//	}
	
//	@Rule ExpectedException // TODO read to know how to check the code within the expection
	
	private GetraenkBox box = new GetraenkBox("01", 10);

	public GetraenkBoxTest() {
		System.out.println("+1 instance");
		System.out.println(" a new box: " + box);
	}
	
	@Test(expected = GetraenkException.class)
	public void addGetraenkeErrorGetraenkArt() {
		box.addFirst(new Getraenk("Cola", 0.5, 1d));
		box.addFirst(new Getraenk("Pepsi", 0.5, 1d));
	}

	@Test
	public void addGetraenke() {
		box.addFirst(cola);
		assertEquals(asList(cola), box.getGetraenke());
	}
	@Test
	public void addGetraenkeTwice() {
		box.addFirst(cola);
		box.addOne();
		assertEquals(asList(cola, cola), box.getGetraenke());
	}
	
	@Test
	public void clear() {
		box.addFirst(cola);
		box.entleeren();
		assertEquals(emptyList(), box.getGetraenke());
	}

}
