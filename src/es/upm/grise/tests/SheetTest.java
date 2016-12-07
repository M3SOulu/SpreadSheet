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
		testSheet.set("A1", "'ciao'");
		
		assertEquals("ciao", testSheet.evaluate("A1"));
	}
	
	@Test
	public void evalutate_have_error() {
		testSheet.set("A1", "=");
		testSheet.set("A2", ",");
		testSheet.set("A3", "b11");
		testSheet.set("A4", " ");
		
		assertEquals("#Error", testSheet.evaluate("A1"));
		assertEquals("#Error", testSheet.evaluate("A2"));
		assertEquals("#Error", testSheet.evaluate("A3"));
		assertEquals("#Error", testSheet.evaluate("A4"));
	}
	
	
	

}
