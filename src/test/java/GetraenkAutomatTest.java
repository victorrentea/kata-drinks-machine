package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.Getraenk;
import main.java.GetraenkBox;
import main.java.GetraenkUndWechselgeld;
import main.java.Getraenkeautomat;
import main.java.IncorrectProvidedParameterException;
import main.java.KeinKapazitaetMehrException;
import main.java.KeinWechselgeldException;
import main.java.Muenze;
import main.java.NichtGefundenAuswahlException;
import main.java.NichtGueltigGetraenkException;

public class GetraenkAutomatTest {
	List<Muenze> muenzen;
	Getraenk getraenkeCola, getraenkeMezzoMix, getraenkeCola2, getraenkeCola3, getraenkeColaZero;
	List<Getraenk> getraenkeListMezzoMix, getraenkeListColas, getraenkeListAll;
	GetraenkBox getranekeColasBox, getraneMezzoBox;
	List<GetraenkBox> getraenkeBoxList;
	List<Double> preise;
	int getraenkBoxes, getraenkBoxKapazitaet;

	@Before
	public void before() {
		Muenze m1 = Muenze.M01;
		Muenze m3 = Muenze.M02;
		Muenze m4 = Muenze.M05;
		this.muenzen = new ArrayList<Muenze>();
		muenzen.add(m1);
		muenzen.add(m3);
		muenzen.add(m4);

		this.getraenkeCola = new Getraenk("Cola", 0.5);
		this.getraenkeMezzoMix = new Getraenk("MezzoMix", 0.5);
		this.getraenkeCola2 = new Getraenk("Cola", 0.5);
		this.getraenkeCola3 = new Getraenk("Cola", 0.5);
		this.getraenkeColaZero = new Getraenk("Cola-Zero", 0.5);

		this.getraenkeListMezzoMix = new ArrayList<Getraenk>();
		this.getraenkeListMezzoMix.add(this.getraenkeMezzoMix);

		this.getraenkeListColas = new ArrayList<Getraenk>();
		this.getraenkeListColas.add(this.getraenkeCola);
		this.getraenkeListColas.add(this.getraenkeCola2);

		this.getraenkeListAll = new ArrayList<Getraenk>();
		this.getraenkeListAll.addAll(getraenkeListColas);
		this.getraenkeListAll.addAll(getraenkeListMezzoMix);

		this.preise = Arrays.asList(new Double(1.2), new Double(1.5), new Double(0.9));
	}

	@Test
	public void befuellen()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, IncorrectProvidedParameterException {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(this.getraenkBoxes, this.getraenkBoxKapazitaet,
				this.preise);
		getraenkeautomat.befuellen(this.getraenkeListAll, this.muenzen);

		Object[] maschinegetraenke = getraenkeautomat.getGetraenkeBox().getGetraenke().toArray();
		Object[] initialGetraenke = this.getraenkeListAll.toArray();
		Arrays.sort(maschinegetraenke);
		Arrays.sort(initialGetraenke);
		assertArrayEquals(maschinegetraenke, initialGetraenke);
		Object[] maschineMuenzen = getraenkeautomat.getMuenzbeutel().getMuenzen().toArray();
		Object[] initialMuenzen = this.muenzen.toArray();
		Arrays.sort(maschineMuenzen);
		Arrays.sort(initialMuenzen);
		assertArrayEquals(initialMuenzen, maschineMuenzen);
	}

	@Test(expected = KeinKapazitaetMehrException.class)
	public void befuellenKeinPlatz()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, IncorrectProvidedParameterException {
		this.getraenkBoxes = 1;
		this.getraenkBoxKapazitaet = 1;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(this.getraenkBoxes, this.getraenkBoxKapazitaet,
				this.preise);
		getraenkeautomat.befuellen(this.getraenkeListColas, this.muenzen);

	}

	@Test
	public void entleeren()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, IncorrectProvidedParameterException {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(this.getraenkBoxes, this.getraenkBoxKapazitaet,
				this.preise);
		getraenkeautomat.befuellen(this.getraenkeListColas, this.muenzen);

		List<Getraenk> expected = new ArrayList<Getraenk>();

		getraenkeautomat.entleeren();
		assertArrayEquals(expected.toArray(), getraenkeautomat.getGetraenkeBox().getGetraenke().toArray());
		assertArrayEquals(expected.toArray(), getraenkeautomat.getMuenzbeutel().getMuenzen().toArray());

	}

	@Test
	public void kaufen() throws Exception {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(this.getraenkBoxes, this.getraenkBoxKapazitaet,
				this.preise);
		getraenkeautomat.befuellen(this.getraenkeListAll, this.muenzen);

		Muenze m01 = Muenze.M01;
		Muenze m02 = Muenze.M02;
		Muenze m05 = Muenze.M05;
		Muenze m10 = Muenze.M10;
		List<Muenze> meinemuenzen = new ArrayList<Muenze>();
		meinemuenzen.add(m10);
		meinemuenzen.add(m05);

		List<Muenze> zurueck = new ArrayList<Muenze>();
		zurueck.add(m02);
		zurueck.add(m01);

		GetraenkUndWechselgeld expected = new GetraenkUndWechselgeld(this.getraenkeCola, zurueck);

		GetraenkUndWechselgeld actual = getraenkeautomat.kaufen("0", meinemuenzen);
		assertEquals(expected, actual);

	}

	@Test(expected = NichtGefundenAuswahlException.class)
	public void kaufenFalscheAuswahl() throws Exception {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(this.getraenkBoxes, this.getraenkBoxKapazitaet,
				this.preise);
		getraenkeautomat.befuellen(this.getraenkeListAll, this.muenzen);

		Muenze m05 = Muenze.M05;
		Muenze m10 = Muenze.M10;
		List<Muenze> meinemuenzen = new ArrayList<Muenze>();
		meinemuenzen.add(m10);
		meinemuenzen.add(m05);

		getraenkeautomat.kaufen("5", meinemuenzen);

	}

	@Test(expected = KeinWechselgeldException.class)
	public void kaufenKeinWechselGeld() throws Exception {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(this.getraenkBoxes, this.getraenkBoxKapazitaet,
				this.preise);
		getraenkeautomat.befuellen(this.getraenkeListAll, this.muenzen);

		Muenze m20 = Muenze.M20;
		List<Muenze> meinemuenzen = new ArrayList<Muenze>();
		meinemuenzen.add(m20);

		getraenkeautomat.kaufen("2", meinemuenzen);
	}

}
