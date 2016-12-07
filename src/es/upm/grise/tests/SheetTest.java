package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class SheetTest {
	
	@Test
	public void cellcontainsoneintegernumber() {
		Sheet number = new Sheet();
		assertEquals("1",evaluate(number));
	}

}
