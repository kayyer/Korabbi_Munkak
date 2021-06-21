package kellekek;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class HuszarTest {
	Huszar lo;
	Tabla tesztTabla;
	
	@Before
	public void setUp() {
		tesztTabla = new Tabla();
		lo = new Huszar(tesztTabla.getMezo(7, 0),"vilagos");
		
	}
	
	@Test
	public void  testHova_lephet() {
		lo.hova_lephet();
		Mezo ide[] = new Mezo[] {tesztTabla.getMezo(5, 1),tesztTabla.getMezo(6, 2)};
		Assert.assertArrayEquals(ide, lo.lephet.toArray());
	}
	
}
