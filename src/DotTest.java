import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class DotTest {
	
	@Rule
    public ExpectedException exception = ExpectedException.none();

	@Test
	public void testRandomConstructor() {
		Dot random = new Dot();
		assertTrue(random.getColor() >= 1 && random.getColor() <= 5);
	}
	
	@Test
	public void testDotConstructor() {
		Dot d = new Dot(3);
		Dot o = new Dot(5);
		assertTrue(d.isColor(3));
		assertEquals(o.getColor(), 5);
		try {
			Dot t = new Dot(7);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		try {
			Dot s = new Dot(0);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetColor() {
		Dot d = new Dot(4);
		assertTrue(d.getColor() == 4);
		try {
			assertTrue((new Dot(7).getColor()) == 7);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
		
	
	@Test
	public void testIsSameColor() {
		Dot d = new Dot(1);
		Dot o = new Dot(5);
		Dot t = new Dot(1);
		assertEquals(d.isSameColor(t), d.isSameColor(d));
		assertFalse(d.isSameColor(o));
	}
	
	@Test
	public void testIsColor() {
		Dot d = new Dot(2);
		assertTrue (d.isColor(2));
		assertFalse(d.isColor(4));
		try {
			assertTrue(d.isColor(7));
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}
