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
		 cells.put("valid","A1");
		 cells.put("valid", "XZ21");
		 cells.put("not valid", "/A1");
		 cells.put("not valid", ".21A");
	 }
	
	@Test
	public void cellContainsOneIntegerNumber() {
		//Sheet number = new Sheet();
		assertEquals("A1",cells.get("valid"));
		assertEquals("XZ21",cells.get("valid"));
		assertEquals("/A1",cells.get("not valid"));
	}
	


	

}
