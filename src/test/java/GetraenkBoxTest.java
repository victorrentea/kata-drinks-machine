package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.Getraenk;
import main.java.GetraenkBox;
import main.java.KeinKapazitaetMehrException;
import main.java.NichtGueltigGetraenkException;

public class GetraenkBoxTest {

	Getraenk getraenkeCola, getraenkeMezzoMix, getraenkeCola2, getraenkeCola3;
	List<Getraenk> getraenkeListMix, getraenkeListColas;

	@Before
	public void before() {
		this.getraenkeCola = new Getraenk("Cola", 0.5);
		this.getraenkeMezzoMix = new Getraenk("MezzoMix", 0.5);
		this.getraenkeCola2 = new Getraenk("Cola", 0.5);
		this.getraenkeCola3 = new Getraenk("Cola", 0.5);
		this.getraenkeListMix = new ArrayList<Getraenk>();
		this.getraenkeListMix.add(this.getraenkeCola);
		this.getraenkeListMix.add(this.getraenkeMezzoMix);
		this.getraenkeListColas = new ArrayList<Getraenk>();
		this.getraenkeListColas.add(this.getraenkeCola);
		this.getraenkeListColas.add(this.getraenkeCola2);
	}

	@Test(expected = NichtGueltigGetraenkException.class)
	public void addGetraenkeErrorGetraenkArt() throws Exception {
		GetraenkBox getraenkBox = new GetraenkBox("01", 10, 1.20);
		getraenkBox.befuellen(this.getraenkeListMix);
	}

	@Test
	public void addGetraenke() throws NichtGueltigGetraenkException, KeinKapazitaetMehrException {
		GetraenkBox getraenkBox = new GetraenkBox("01", 10, 1.20);
		getraenkBox.befuellen(this.getraenkeListColas);
		assertArrayEquals(this.getraenkeListColas.toArray(), getraenkBox.getGetraenke().toArray());
	}

	@Test(expected = KeinKapazitaetMehrException.class)
	public void addMehereGetraenkeAlsMoeglich() throws Exception {
		GetraenkBox getraenkBox = new GetraenkBox("01", 1, 1.20);
		getraenkBox.befuellen(getraenkeListColas);
	}

	@Test
	public void entleeren() throws NichtGueltigGetraenkException, KeinKapazitaetMehrException {
		GetraenkBox getraenkBox = new GetraenkBox("01", 2, 1.20);
		getraenkBox.befuellen(getraenkeListColas);
		List<Getraenk> expected = new ArrayList<Getraenk>();
		assertFalse(Arrays.equals(expected.toArray(), getraenkBox.getGetraenke().toArray()));
		getraenkBox.entleeren();
		assertArrayEquals(expected.toArray(), getraenkBox.getGetraenke().toArray());
	}

}
