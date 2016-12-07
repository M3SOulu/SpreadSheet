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
		sheet.evaluate("A1");
		Assert.assertEquals("1", "1");
	}
}
