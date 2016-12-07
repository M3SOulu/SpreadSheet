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
		cells.put("B1", "=1+1");
		int result = Integer.parseInt("");
		assertEquals(2, result);
	}

}
