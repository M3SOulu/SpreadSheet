package es.upm.grise.tests;
import es.upm.grise.*;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class SheetTest {

	Sheet sheet = new Sheet();

	public void testNumber1(){
				sheet.set("A1","1");		
				assertEquals("1",sheet.evaluate("A1"));
		 	}

	@Test
	public void testIsNotInteger(){
		s.set("A1", "1.5");
		assertEquals(s.evaluate("A1"),"#Error");
	}

	@Test
	public void testReturnAString1(){
		s.set("A1", "\'ciao\'");
		assertEquals(s.evaluate("A1"),"ciao");
	}

	@Test
	public void testReturnAString2(){
		s.set("A1", "\'ciao");
		assertEquals(s.evaluate("A1"),"#Error");
	}

	@Test
	public void testReturnAString3(){
		s.set("A1", "\'\'");
		assertEquals(s.evaluate("A1"),"#Error");
	}

	@Test
	public void testFormulas1(){
		s.set("A1","=1");
		assertEquals(s.evaluate("A1"),"1");
	}

	@Test
	public void testFormulas2(){
		s.set("A1", "=1.5");
		assertEquals(s.evaluate("A1"),"#Error");
	}

	@Test
	public void testFormulas3(){
		s.set("A1", "=\'ciao\'");
		assertEquals(s.evaluate("A1"),"ciao");
	}

}
