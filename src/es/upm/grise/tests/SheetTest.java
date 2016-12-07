package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.upm.grise.Sheet;

public class SheetTest {
	

	@Test
	public void cellcontainsoneintegernumber() {
		Sheet number = new Sheet();
		assertEquals("1",cells.set("1"));
	}

}
