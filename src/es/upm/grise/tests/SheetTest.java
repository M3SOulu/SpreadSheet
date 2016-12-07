package es.upm.grise.tests;
import es.upm.grise.*;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class SheetTest {
	
	HashMap <String, String> cells = new HashMap <String, String>();

	@Test
	public void testContentCell() {
		
		HashMap <String, String> cells = new HashMap <String, String>();
		cells.put("A1", "=5");
		String result = cells.get("A1");
		assertEquals("=5", result);
	}
	
	@Test
	public void testNumber(){
		cells.put("B1", "=15");
		int result = Integer.parseInt("=15");
		assertEquals(15, result);
	}

}
