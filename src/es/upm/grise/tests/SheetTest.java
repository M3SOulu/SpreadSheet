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
		foglio.set("A1", "1");
		foglio.set("A2", "=1+2");
		foglio.set("A3", "=1+2+5");
		foglio.set("A4", "=2-1");
		foglio.set("A5", "=2-1-2");
		foglio.set("A6", "=2*1");
		foglio.set("A7", "=2*1*2");
		foglio.set("A8", "=2/1");
	}

	@Test
	public void evalutateCellOneValue() {
		assertEquals("1", foglio.evaluate("A1"));
	}
	
	@Test
	public void evalutateCellWhithSumTwoNumber() {
		assertEquals("3", foglio.evaluate("A2"));
	}
	
	@Test
	public void evalutateCellWhithSumTwoThree() {
		assertEquals("8", foglio.evaluate("A3"));
	}
	
	@Test
	public void evalutateCellWhithSubtractionTwoNumber() {
		assertEquals("1", foglio.evaluate("A4"));
	}
	
	@Test
	public void evalutateCellWhithSubtractionThreeNumber() {
		assertEquals("-1", foglio.evaluate("A5"));
	}
	
	@Test
	public void evalutateCellWhithMultiplicationTwoNumber() {
		assertEquals("2", foglio.evaluate("A6"));
	}
	
	@Test
	public void evalutateCellWhithMultiplicationThreeNumber() {
		assertEquals("4", foglio.evaluate("A7"));
	}
	
	@Test
	public void evalutateCellWhithDivisionTwoNumber() {
		assertEquals("2", foglio.evaluate("A8"));
	}
	
	

}
