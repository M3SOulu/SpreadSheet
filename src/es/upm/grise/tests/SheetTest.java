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
	public void evalutate_is_not_expressin() {
		testSheet.set("A1", "1");
		testSheet.set("A2", "-12");
		
		assertEquals("1", testSheet.evaluate("A1"));
		assertEquals("-12", testSheet.evaluate("A2"));
	}
	
	@Test
	public void evalutate_have_error() {
		testSheet.set("A1", "=");
		testSheet.set("A2", ",");
		testSheet.set("A3", "b11");
		
		assertEquals("#Error", testSheet.evaluate("A1"));
		assertEquals("#Error", testSheet.evaluate("A2"));
		assertEquals("#Error", testSheet.evaluate("A3"));
	}

}
