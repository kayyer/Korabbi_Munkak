package kellekek;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BastyaTest {
	Tabla tesztTabla;
	Bastya tesztelt;
	
	@Before
	public void setUp() {
		tesztTabla = new Tabla();
		tesztelt = new Bastya(tesztTabla.getMezo(7, 0),"vilagos");
		tesztelt.hova_lephet();
	}
	
	@Test
	public void testHovaLephet() {
		
		Mezo ideKeneLepnie[] = new Mezo[14];
		int hor = 1;
		int ver = 1;
		for(int i = 0; i < 14; i++)
		{
			ideKeneLepnie[i] = tesztTabla.getMezo(7-ver, 0);
			i++;
			ideKeneLepnie[i] = tesztTabla.getMezo(7,0 + hor);
			hor++;
			ver++;
			
		}
		
		Assert.assertArrayEquals(ideKeneLepnie, tesztelt.lephet.toArray());
	}
	@Test
	public void testHogyJutszIdde() {
		Mezo ide1 = tesztTabla.getMezo(7, 3);
		Mezo ide2 = tesztTabla.getMezo(6, 3);
		Mezo kene[] = new Mezo[] {tesztTabla.getMezo(7, 0),tesztTabla.getMezo(7, 1),tesztTabla.getMezo(7, 2)};
		Assert.assertArrayEquals(kene, tesztelt.HogyJutszIde(ide1).toArray());
		Assert.assertNull(tesztelt.HogyJutszIde(ide2));
	}
}
