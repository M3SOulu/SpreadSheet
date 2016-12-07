package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.upm.grise.Sheet;
import junit.framework.Assert;

public class SheetTest {
	Sheet sheet;
	
	@Before
	public void setup() {
		sheet = new Sheet();
	}

	@Test
	public void testNumber1(){
		sheet.set("A1","1");		
		Assert.assertEquals("1",sheet.evaluate("A1"));
	}
	
	@Test
	public void testNumberPositive1(){
		sheet.set("A1","+1");		
		Assert.assertEquals("1",sheet.evaluate("A1"));
	}
	
	@Test
	public void testNumberNegative1(){
		sheet.set("A1","-1");		
		Assert.assertEquals("-1",sheet.evaluate("A1"));
	}
	
	@Test
	public void testFloatNumber(){
		sheet.set("A1","1.2");		
		Assert.assertEquals("#Error",sheet.evaluate("A1"));
	}
	
	@Test
	public void testStringWithQuotes(){
		sheet.set("A1","'hello'");		
		Assert.assertEquals("hello",sheet.evaluate("A1"));
	}
	
	@Test
	public void testSumOfTwoNumbers(){
		sheet.set("A1","=1+1");		
		Assert.assertEquals("2",sheet.evaluate("A1"));
	}
	
}
