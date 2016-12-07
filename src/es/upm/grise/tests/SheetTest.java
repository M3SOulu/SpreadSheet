package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.upm.grise.Sheet;

public class SheetTest {

	private Sheet testSheet;
	
	public SheetTest(){
		testSheet = new Sheet();
	}
	
	@Test
	public void value_is_decimal_number() {
		testSheet.set("A1", "0");
		testSheet.set("A2", "-999");
		
		assertEquals("0", testSheet.evaluate("A1"));
		assertEquals("-999", testSheet.evaluate("A2"));
	}
	
	@Test
	public void value_is_string() {
		testSheet.set("B1", "'ciao'");
		
		assertEquals("ciao", testSheet.evaluate("B1"));
	}
	
	@Test
	public void evalutate_have_error() {
		testSheet.set("E1", "=");
		testSheet.set("E2", ",");
		testSheet.set("E3", "b11");
		testSheet.set("E4", " ");
		
		assertEquals("#Error", testSheet.evaluate("E1"));
		assertEquals("#Error", testSheet.evaluate("E2"));
		assertEquals("#Error", testSheet.evaluate("E3"));
		assertEquals("#Error", testSheet.evaluate("E4"));
	}
	
	
	

}
