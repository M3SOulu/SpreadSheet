package es.upm.grise.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import es.upm.grise.Sheet;

public class SheetTest {
	private HashMap <String, String> cells = new HashMap <String, String>();
	
	
	@Test
	public void cellcontainsoneintegernumber() {
		//Sheet number = new Sheet();
		assertEquals("A1",cells.get("valid"));
	}
	

}
