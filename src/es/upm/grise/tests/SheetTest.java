package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.upm.grise.Sheet;

public class SheetTest {
	Sheet foglio;
	
	@Before
	public void setUp() {
		foglio = new Sheet();
		foglio.set("A1", "=1");
		foglio.set("A2", "=1+2");
		foglio.set("A3", "1*2");
		foglio.set("A4", "1/2");
	}

	@Test
	public void evalutateCellOneValue() {
		assertEquals("1", foglio.evaluate("A1"));
	}
	
	@Test
	public void evalutateCellWhithSum() {
		assertEquals("3", foglio.evaluate("A2"));
	}

}
