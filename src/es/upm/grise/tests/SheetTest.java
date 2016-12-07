package es.upm.grise.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import es.upm.grise.Sheet;
import sun.org.mozilla.javascript.internal.EvaluatorException;

public class SheetTest {
	private HashMap <String, String> cells = new HashMap <String, String>();
	 @Before
	 public void test(){
		 cells.put("A1","valid");
		 cells.put("XZ21", "valid");
		 cells.put("/A1", "not valid");
		 cells.put(".21A", "not valid");
	 }
	
	@Test
	public void cellContainsOneIntegerNumber() {
		//Sheet number = new Sheet();
		assertEquals("A1",cells.get("valid"));
		assertEquals("XZ21",cells.get("valid"));
		assertEquals("/A1",cells.get("not valid"));
	}
	
	@Before
	public void test2(){
		
	}

	

}
