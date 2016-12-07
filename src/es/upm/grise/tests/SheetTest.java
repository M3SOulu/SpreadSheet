package es.upm.grise.tests;
import es.upm.grise.*;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class SheetTest {
	
	HashMap <String, String> cells = new HashMap <String, String>();

	@Test
	public void testContentCell() {
		
		cells.put("A1", "=5");
		String result = cells.get("A1");
		assertEquals("=5", result);
	}
	
	@Test
	public void testEvaluateSingleNumber(){
		cells.put("B1", "1");
		String result = evaluate("B1");
		assertEquals("1", result);
	}
	
	@Test
	public void testSingleNumberWithSingleQuotes(){
		cells.put("F7", '4');
		String result = evaluate("F7");
		assertEquals(4, result);
	}

}
