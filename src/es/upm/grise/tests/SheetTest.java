package es.upm.grise.tests;
import es.upm.grise.*;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class SheetTest {

	@Test
	public void testContentCell() {
		
		HashMap <String, String> cells = new HashMap <String, String>();
		String result = cells.get("A1");
		assertEquals("=5", result);
	}

}
