package kellekek;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KiralyTest {
	Tabla tesztTabla;
	Kiraly alany;
	Bastya tamado;
	
	@Before
	public void setUp() {
		tesztTabla = new Tabla();
		alany = new Kiraly(tesztTabla.getMezo(7, 4),"sotet");
		tamado = new Bastya(tesztTabla.getMezo(7,2),"vilagos");
	
		alany.felrak();
		tamado.hova_lephet();
		tamado.felrak();
	}
	
	@Test
	public void tesztTamadja() {
		Gyalog csapatTars = new Gyalog(tesztTabla.getMezo(3, 5),"sotet");
		
		csapatTars.felrak();
		csapatTars.hova_lephet();
		alany.Tamadja(tamado);
		Mezo hovaLehet[] = new Mezo[] {tesztTabla.getMezo(7,2),tesztTabla.getMezo(7,3)};
		Assert.assertArrayEquals(hovaLehet, Babu.koztuk.toArray());
		Assert.assertTrue(tesztTabla.getSakk().equals(alany.szin));
		Assert.assertEquals(0, csapatTars.lephet.size());
			
	}
	
}
