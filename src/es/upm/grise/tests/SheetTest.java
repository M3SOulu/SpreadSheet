package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import es.upm.grise.Sheet;

public class SheetTest {
	
	Sheet s = new Sheet();
	
	
	@Test
	public void testReturnTheContent() {
		s.set("A1","1");
		assertEquals(s.evaluate("A1"),"1");
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
		assertEquals(s.evaluate("A1"),"#Errora");
	}
	
	@Test
	public void testFormulas3(){
		s.set("A1", "=\'ciao\'");
		assertEquals(s.evaluate("A1"),"ciao");
	}

}
