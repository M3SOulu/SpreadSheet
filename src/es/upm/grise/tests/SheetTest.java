package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.upm.grise.Sheet;

public class SheetTest {

	private Sheet testSheet;
	
	public SheetTest(){
		testSheet = new Sheet();
	}
	
	@Test
	public void evalutate_is_1() {
		testSheet.set("A1", "1");
		assertEquals("1", testSheet.evaluate("A1"));
	}

}
