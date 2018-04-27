package musicbox;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import musicbox.Getraenk;
import musicbox.GetraenkBox;
import musicbox.GetraenkeBox;
import musicbox.GetraenkException;
import musicbox.KeinKapazitaetMehrException;
import musicbox.NichtGueltigGetraenkException;

public class GetraenkeBoxTest {

	Getraenk getraenkeCola, getraenkeMezzoMix, getraenkeCola2, getraenkeCola3, getraenkeColaZero;
	List<Getraenk> getraenkeListMezzoMix, getraenkeListColas;
	GetraenkBox getranekeColasBox, getraneMezzoBox;
	List<GetraenkBox> getraenkeBoxList;
	List<Double> preise;
	int getraenkBoxes, getraenkBoxKapazitaet;

	@Before
	public void before() {
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

		this.preise = Arrays.asList(new Double(1.2), new Double(1.5), new Double(0.9));
	}

	@Test
	public void befuellen()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, GetraenkException {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 3;
		GetraenkeBox getraenkeBox = new GetraenkeBox(this.getraenkBoxes, this.getraenkBoxKapazitaet, this.preise);
		getraenkeBox.befuellen(this.getraenkeListColas);

		Object[] maschinegetraenke = getraenkeBox.getGetraenke().toArray();
		Object[] initialGetraenke = this.getraenkeListColas.toArray();
		Arrays.sort(maschinegetraenke);
		Arrays.sort(initialGetraenke);
		assertArrayEquals(maschinegetraenke, initialGetraenke);
	}

	@Test(expected = KeinKapazitaetMehrException.class)
	public void befuellenErrorKeinPlatz()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, GetraenkException {
		this.getraenkBoxes = 2;
		this.getraenkBoxKapazitaet = 2;
		GetraenkeBox getraenkeBox = new GetraenkeBox(this.getraenkBoxes, this.getraenkBoxKapazitaet, this.preise);
		getraenkeBox.befuellen(this.getraenkeListColas);
		getraenkeBox.befuellen(this.getraenkeListMezzoMix);
		// Nur Platz für 2 Colas
		List<Getraenk> glist3 = new ArrayList<Getraenk>();
		glist3.add(this.getraenkeCola3);

		getraenkeBox.befuellen(glist3);
	}

	@Test
	public void entleeren()
			throws NichtGueltigGetraenkException, KeinKapazitaetMehrException, GetraenkException {
		this.getraenkBoxes = 3;
		this.getraenkBoxKapazitaet = 2;
		GetraenkeBox getraenkeBox = new GetraenkeBox(this.getraenkBoxes, this.getraenkBoxKapazitaet, this.preise);
		getraenkeBox.befuellen(this.getraenkeListColas);
		getraenkeBox.befuellen(this.getraenkeListMezzoMix);
		List<Getraenk> expected = new ArrayList<Getraenk>();
		assertFalse(Arrays.equals(expected.toArray(), getraenkeBox.getGetraenke().toArray()));
		getraenkeBox.entleeren();
		assertArrayEquals(expected.toArray(), getraenkeBox.getGetraenke().toArray());
	}

}
