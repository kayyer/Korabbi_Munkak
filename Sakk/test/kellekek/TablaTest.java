package kellekek;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TablaTest {
	Tabla tesztTabla;
	
	@Before
	public void setUp() {
		tesztTabla = new Tabla();
	}
	
	@Test
	public void testBabuBeallitas() {
		tesztTabla.BabuBeallitas();
		Assert.assertEquals(16, tesztTabla.getVilagos().size());
		Assert.assertEquals(16, tesztTabla.getSotet().size());
		Assert.assertFalse(tesztTabla.getVilagos() == tesztTabla.getSotet());
		Assert.assertEquals(tesztTabla.getMezo(1, 0), tesztTabla.getSotet().elementAt(0).getHolvan());
		
	}
	@Test
	public void testLeutottek() {
		tesztTabla.BabuBeallitas();
		tesztTabla.babukFelrakasa();
		Babu aldozat = tesztTabla.getSotet().elementAt(0);
		tesztTabla.leutottek(aldozat);
		Assert.assertNull(tesztTabla.getMezo(1, 0).getRajta());
		Assert.assertNotSame(tesztTabla.getSotet().elementAt(0), aldozat);
	}
	@Test
	public void testUjBabu() {
		tesztTabla.BabuBeallitas();
		tesztTabla.babukFelrakasa();
		Vezer uj = new Vezer(tesztTabla.getMezo(4, 4),"vilagos");
		tesztTabla.ujBabu(uj);
		Assert.assertTrue(tesztTabla.getVilagos().contains(uj));
		Assert.assertEquals(tesztTabla.getMezo(7, 4), uj.getKiralyomHelye());
	}
	
}
