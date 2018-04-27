package musicbox;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import musicbox.KeinKapazitaetMehrException;
import musicbox.KeinWechselgeldException;
import musicbox.NichtGefundenAuswahlException;
import musicbox.NichtGueltigGetraenkException;

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
		Muenze m1 = Muenze.M10;
		Muenze m3 = Muenze.M20;
		Muenze m4 = Muenze.M50;
		muenzen = new ArrayList<Muenze>();
		muenzen.add(m1);
		muenzen.add(m3);
		muenzen.add(m4);

		getraenkeCola = new Getraenk("Cola", 0.5);
		getraenkeMezzoMix = new Getraenk("MezzoMix", 0.5);
		getraenkeCola2 = new Getraenk("Cola", 0.5);
		getraenkeCola3 = new Getraenk("Cola", 0.5);
		getraenkeColaZero = new Getraenk("Cola-Zero", 0.5);

		getraenkeListMezzoMix = new ArrayList<Getraenk>();
		getraenkeListMezzoMix.add(getraenkeMezzoMix);

		getraenkeListColas = new ArrayList<Getraenk>();
		getraenkeListColas.add(getraenkeCola);
		getraenkeListColas.add(getraenkeCola2);

		getraenkeListAll = new ArrayList<Getraenk>();
		getraenkeListAll.addAll(getraenkeListColas);
		getraenkeListAll.addAll(getraenkeListMezzoMix);

		preise = Arrays.asList(new Double(1.2), new Double(1.5), new Double(0.9));
	}

	@Test
	public void befuellen()
			{
		getraenkBoxes = 3;
		getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(getraenkBoxes, getraenkBoxKapazitaet,
				preise);
		getraenkeautomat.befuellen(getraenkeListAll, muenzen);

		
		
		Object[] maschinegetraenke = getraenkeautomat.getGetraenkeBox().getGetraenke().toArray();
		Object[] initialGetraenke = getraenkeListAll.toArray();
		Arrays.sort(maschinegetraenke);
		Arrays.sort(initialGetraenke);
		assertArrayEquals(maschinegetraenke, initialGetraenke);
		
		
		
		Object[] maschineMuenzen = getraenkeautomat.getMuenzbeutel().getMuenzen().toArray();
		Object[] initialMuenzen = muenzen.toArray();
		Arrays.sort(maschineMuenzen);
		Arrays.sort(initialMuenzen);
		assertArrayEquals(initialMuenzen, maschineMuenzen);
	}

	@Test(expected = KeinKapazitaetMehrException.class)
	public void befuellenKeinPlatz()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, GetraenkException {
		getraenkBoxes = 1;
		getraenkBoxKapazitaet = 1;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(getraenkBoxes, getraenkBoxKapazitaet,
				preise);
		getraenkeautomat.befuellen(getraenkeListColas, muenzen);

	}

	@Test
	public void entleeren()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, GetraenkException {
		getraenkBoxes = 3;
		getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(getraenkBoxes, getraenkBoxKapazitaet,
				preise);
		getraenkeautomat.befuellen(getraenkeListColas, muenzen);

		List<Getraenk> expected = new ArrayList<Getraenk>();

		getraenkeautomat.entleeren();
		assertArrayEquals(expected.toArray(), getraenkeautomat.getGetraenkeBox().getGetraenke().toArray());
		assertArrayEquals(expected.toArray(), getraenkeautomat.getMuenzbeutel().getMuenzen().toArray());

	}

	@Test
	public void kaufen() throws Exception {
		getraenkBoxes = 3;
		getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(getraenkBoxes, getraenkBoxKapazitaet,
				preise);
		getraenkeautomat.befuellen(getraenkeListAll, muenzen);

		Muenze m01 = Muenze.M10;
		Muenze m02 = Muenze.M20;
		Muenze m05 = Muenze.M50;
		Muenze m10 = Muenze.M100;
		List<Muenze> meinemuenzen = new ArrayList<Muenze>();
		meinemuenzen.add(m10);
		meinemuenzen.add(m05);

		List<Muenze> zurueck = new ArrayList<Muenze>();
		zurueck.add(m02);
		zurueck.add(m01);

		GetraenkUndWechselgeld expected = new GetraenkUndWechselgeld(getraenkeCola, zurueck);

		GetraenkUndWechselgeld actual = getraenkeautomat.kaufen("0", meinemuenzen);
		assertEquals(expected, actual);

	}

	@Test(expected = NichtGefundenAuswahlException.class)
	public void kaufenFalscheAuswahl() throws Exception {
		getraenkBoxes = 3;
		getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(getraenkBoxes, getraenkBoxKapazitaet,
				preise);
		getraenkeautomat.befuellen(getraenkeListAll, muenzen);

		Muenze m05 = Muenze.M50;
		Muenze m10 = Muenze.M100;
		List<Muenze> meinemuenzen = new ArrayList<Muenze>();
		meinemuenzen.add(m10);
		meinemuenzen.add(m05);

		getraenkeautomat.kaufen("5", meinemuenzen);

	}

	@Test(expected = KeinWechselgeldException.class)
	public void kaufenKeinWechselGeld() throws Exception {
		getraenkBoxes = 3;
		getraenkBoxKapazitaet = 3;
		Getraenkeautomat getraenkeautomat = new Getraenkeautomat(getraenkBoxes, getraenkBoxKapazitaet,
				preise);
		getraenkeautomat.befuellen(getraenkeListAll, muenzen);

		Muenze m20 = Muenze.M200;
		List<Muenze> meinemuenzen = new ArrayList<Muenze>();
		meinemuenzen.add(m20);

		getraenkeautomat.kaufen("2", meinemuenzen);
	}

}
