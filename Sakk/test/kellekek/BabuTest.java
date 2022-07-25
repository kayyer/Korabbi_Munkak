package kellekek;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class BabuTest {
	
	Tabla testTabla;
	Bastya felrakando;
	Futo tamado;
	@Before
	public void setUp() {
		testTabla = new Tabla();
		felrakando = new Bastya(testTabla.getMezo(1, 1),"vilagos");
		tamado = new Futo(testTabla.getMezo(3, 3),"sotet");
	}
	@Test
	public void testFelrak() {
		felrakando.felrak();
		Assert.assertEquals(felrakando, testTabla.getMezo(1, 1).getRajta());
	}
	
	@Test
	public void testLep() {
		felrakando.felrak();
		Mezo elotte = felrakando.hol_van;
		Mezo utana = testTabla.getMezo(2, 2);
		felrakando.lep(utana);
		Assert.assertEquals(utana, felrakando.hol_van);
		Assert.assertEquals(utana.getRajta(), felrakando);
		Assert.assertNull(elotte.getRajta());
	}
	
	@Test
	public void testTamadHelp() {
		felrakando.felrak();
		Babu tamadja[] = new Babu[] {tamado};
		Babu tamad[] = new Babu[] {felrakando};
		tamado.tamadHelp(felrakando.hol_van);
		Assert.assertArrayEquals(tamad,tamado.tamad.toArray());
		Assert.assertArrayEquals(tamadja, felrakando.tamadjak.toArray());
		Assert.assertEquals(0,felrakando.vedi.size());
		Assert.assertArrayEquals(tamadja, felrakando.kit_gatol.toArray());

	}
	
	
}
