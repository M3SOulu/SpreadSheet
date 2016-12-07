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
	public void testNumberPositive1(){
		sheet.set("A1","+1");		
		assertEquals("1",sheet.evaluate("A1"));
	}

	@Test
	public void testNumberNegative1(){
		sheet.set("A1","-1");		
		assertEquals("-1",sheet.evaluate("A1"));
	}

	@Test
	public void testFloatNumber(){
		sheet.set("A1","1.2");		
		assertEquals("#Error",sheet.evaluate("A1"));
	}

	@Test
	public void testStringWithQuotes(){
		sheet.set("A1","'hello'");		
		assertEquals("hello",sheet.evaluate("A1"));
	}

	@Test
	public void testSumOfTwoNumbers(){
		sheet.set("A1","=1+1");		
		assertEquals("2",sheet.evaluate("A1"));
	}

	@Test
	public void testFirstNumberNegative(){
		sheet.set("A1","=-1+1");		
		assertEquals("0",sheet.evaluate("A1"));
	}

	@Test
	public void testDifferenceBetweenTwoNumbers(){
		sheet.set("A1","=1-1");		
		assertEquals("0",sheet.evaluate("A1"));
	}

	@Test
	public void testDivisionBetweenTwoNumbers(){
		sheet.set("A1","=1/1");		
		assertEquals("1",sheet.evaluate("A1"));
	}

	@Test
	public void testDivisionByZero(){
		sheet.set("A1","=1/0");		
		assertEquals("#Error",sheet.evaluate("A1"));
	}

	@Test
	public void testSumWithBrakets(){
		sheet.set("A1","=(1)-1");		
		assertEquals("0",sheet.evaluate("A1"));
	}

	@Test
	public void testMoltiplicationBetweenTwoNumbers(){
		sheet.set("A1","=1*1");		
		assertEquals("1",sheet.evaluate("A1"));
	}

	@Test
	public void testSumWithSpaces(){
		sheet.set("A1","=1 + 1");		
		assertEquals("2",sheet.evaluate("A1"));
	}

}
