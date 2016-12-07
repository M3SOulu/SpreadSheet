package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.upm.grise.Sheet;

public class SheetTest {

	private Sheet sheet;
	@Before
	public void setUp() {
		sheet = new Sheet();
	}
	
	@Test
	public void testSetAndGetCellStringValue() {
		sheet.set("A1", "qwerty");
		assertEquals("qwerty", sheet.get("A1"));
	}

	@Test
	public void testSetAndGetCellIntegerValue() {
		sheet.set("A1", "123");
		assertEquals(123, sheet.get("A1"));
	}

}
