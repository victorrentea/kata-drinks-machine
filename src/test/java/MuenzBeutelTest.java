package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.Muenzbeutel;
import main.java.Muenze;

public class MuenzBeutelTest {

	List<Muenze> muenzen;
	Muenzbeutel muenzbeutel;

	@Before
	public void before() {
		Muenze m1 = Muenze.M01;
		Muenze m2 = Muenze.M01;
		Muenze m3 = Muenze.M02;
		Muenze m4 = Muenze.M05;
		Muenze m5 = Muenze.M10;
		this.muenzen = new ArrayList<Muenze>();
		muenzen.add(m5);
		muenzen.add(m1);
		muenzen.add(m2);
		muenzen.add(m3);
		muenzen.add(m4);
		this.muenzbeutel = new Muenzbeutel();
	}

	@Test
	public void befuellen() throws Exception {
		this.muenzbeutel.befuellen(this.muenzen);
		Object[] maschineMuenzen = this.muenzbeutel.getMuenzen().toArray();
		Object[] initialMuenzen = this.muenzen.toArray();
		Arrays.sort(maschineMuenzen);
		Arrays.sort(initialMuenzen);
		assertArrayEquals(maschineMuenzen, initialMuenzen);
	}

	@Test
	public void entleeren() throws Exception {
		this.muenzbeutel.befuellen(this.muenzen);
		List<Muenze> expected = new ArrayList<Muenze>();
		assertFalse(Arrays.equals(expected.toArray(), this.muenzbeutel.getMuenzen().toArray()));
		this.muenzbeutel.entleeren();
		assertArrayEquals(expected.toArray(), this.muenzbeutel.getMuenzen().toArray());
	}

	@Test
	public void getWechselgeld() throws Exception {
		// 1.0, 0.5, 0.2, 0.1, 0.1
		this.muenzbeutel.befuellen(this.muenzen);

		Muenze muenze01 = Muenze.M01;
		Muenze muenze02 = Muenze.M02;
		Muenze muenze10 = Muenze.M10;

		List<Muenze> expecteds = new ArrayList<Muenze>();
		expecteds.add(muenze10);
		expecteds.add(muenze02);
		expecteds.add(muenze01);

		Double geld = new Double(1.3);
		List<Muenze> actuals = this.muenzbeutel.getWechselgeld(geld);
		assertArrayEquals(expecteds.toArray(), actuals.toArray());
	}

	@Test(expected = Exception.class)
	public void getWechselgeldError() throws Exception {
		this.muenzbeutel.befuellen(this.muenzen);

		Double zueruck = new Double(2.0);
		this.muenzbeutel.getWechselgeld(zueruck);
	}

}
