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
	public void value_is_simpre_formula() {
		testSheet.set("C1", "=88");
		
		assertEquals("88", testSheet.evaluate("C1"));
	}
	
	@Test
	public void evalutate_have_error() {
		testSheet.set("E1", "=");	//Formula senza parametri
		testSheet.set("E2", ",");	//carattere casuale non accettato
		testSheet.set("E3", "b11");	//Stringa non racchiusa tra singoli apici
		testSheet.set("E4", "'Hi");	//Stringa non racchiusa tra singoli apici
		testSheet.set("E5", "Call'");	//Stringa non racchiusa tra singoli apici
		testSheet.set("E6", " ");	//vuoto
		
		assertEquals("#Error", testSheet.evaluate("E1"));
		assertEquals("#Error", testSheet.evaluate("E2"));
		assertEquals("#Error", testSheet.evaluate("E3"));
		assertEquals("#Error", testSheet.evaluate("E4"));
		assertEquals("#Error", testSheet.evaluate("E5"));
		assertEquals("#Error", testSheet.evaluate("E6"));
	}
	
	
	

}
