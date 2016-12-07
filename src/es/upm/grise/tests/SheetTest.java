package es.upm.grise.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import es.upm.grise.CircularReferenceException;
import es.upm.grise.ComputationErrorException;
import es.upm.grise.Sheet;

public class SheetTest {

	Sheet spreadSheet;
	
	@Before
	public void setUp(){
		spreadSheet = new Sheet();
		spreadSheet.set("A1", "55");
		spreadSheet.set("X33", "=2+2");
		spreadSheet.set("L2", "2.2");
	}
	
	@Test
	public void testFunctionGet() throws ComputationErrorException {
		Assert.assertEquals("55", spreadSheet.get("A1"));
	}
	
	@Test
	public void testFunctionEvaluateWithASingleNumber() throws CircularReferenceException {
		Assert.assertEquals("55", spreadSheet.evaluate("A1"));
	}

	@Test
	public void testFunctionEvaluateWithAnExpression() throws CircularReferenceException {
		Assert.assertEquals("4", spreadSheet.evaluate("X33"));
	}
	
	@Test (expected = CircularReferenceException.class)
	public void testExceptionEvaluate() throws CircularReferenceException {
		Assert.assertEquals("2", spreadSheet.evaluate("B4"));
	}
	
	@Test 
	public void testFunctionEvaluateNumberNotInteger() throws CircularReferenceException {
		Assert.assertEquals("2.2", spreadSheet.evaluate("L2"));
	}
}
