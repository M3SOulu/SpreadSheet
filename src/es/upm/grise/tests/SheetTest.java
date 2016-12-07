package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.upm.grise.CircularReferenceException;
import es.upm.grise.ComputationErrorException;
import es.upm.grise.Sheet;

public class SheetTest {
	
	Sheet spreadSheet = new Sheet();

	@Test
	public void basicTest() throws ComputationErrorException, CircularReferenceException {
		spreadSheet.set("A1", "6");
		assertEquals("6",spreadSheet.evaluate("A1"));
	}
	
	@Test
	public void testABasicFormula() throws ComputationErrorException, CircularReferenceException {
		spreadSheet.set("A1", "=1-2");
		assertEquals("-1",spreadSheet.evaluate("A1"));
	}
	
	@Test
	public void testADoubleFormula() throws ComputationErrorException, CircularReferenceException {
		spreadSheet.set("A2", "=1-2");
		spreadSheet.set("A1", "=2+A2");
		assertEquals("1",spreadSheet.evaluate("A1"));
	}

	@Test (expected = CircularReferenceException.class)
	public void testADoubleFormulaBis() throws ComputationErrorException, CircularReferenceException {
		spreadSheet.set("A2", "=1-A1");
		spreadSheet.set("A1", "=A2+2");
		assertEquals("1",spreadSheet.evaluate("A1"));
	}
	
	@Test (expected = ComputationErrorException.class)
	public void testAWrongFormula() throws ComputationErrorException, CircularReferenceException {
		spreadSheet.set("A2", "=1-2");
		spreadSheet.set("A1", "=((A2)+2");
		assertEquals("1",spreadSheet.evaluate("A1"));
	}
	
	@Test
	public void testATripleFormula() throws ComputationErrorException, CircularReferenceException {
		spreadSheet.set("A2", "=A3*2");
		spreadSheet.set("A3", "=1-2");
		spreadSheet.set("A1", "=2+A2");
		assertEquals("0",spreadSheet.evaluate("A1"));
	}
}
