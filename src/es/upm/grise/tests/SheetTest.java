package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import es.upm.grise.CircularReferenceException;
import es.upm.grise.ComputationErrorException;
import es.upm.grise.Sheet;

public class SheetTest {

	private Sheet sheet;
	@Before
	public void setUp() {
		sheet = new Sheet();
	}
	
	@Test
	public void testSetAndGetCellStringValue() {
		sheet.set("A1", "'qwerty'");
		assertEquals("'qwerty'", sheet.get("A1"));
	}

	@Test
	public void testSetAndGetCellIntegerValue() {
		sheet.set("A1", "123");
		assertEquals("123", sheet.get("A1"));
	}
	
	@Test
	public void testEvaluateStringValue() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "'qwerty'");
		assertEquals("qwerty", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateIntegerValue() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "123");
		assertEquals("123", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateNegativeIntegerValue() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "-123");
		assertEquals("-123", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateBadIntegerValue() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "1.23");
		assertEquals("#Error", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateBadStringValue() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "'testBadString");
		assertEquals("#Error", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateSimpleIntegerFormula() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "=1");
		assertEquals("1", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateSimpleStringFormula() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "='qwerty'");
		assertEquals("qwerty", sheet.evaluate("A1"));
	}
	
	@Test
	public void testEvaluateSimpleCellReferenceFormula() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "'qwerty'");
		sheet.set("A2", "=A1");
		assertEquals("qwerty", sheet.evaluate("A2"));
	}
	
	@Test
	public void testEvaluateSimpleCellReferenceErrorFormula() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "'testBadString");
		sheet.set("A2", "=A1");
		assertEquals("#Error", sheet.evaluate("A2"));
	}
	
	@Test
	public void testEvaluateSimpleCellReferenceCircularErrorFormula() throws CircularReferenceException, ComputationErrorException{
		sheet.set("A1", "=A3");
		sheet.set("A2", "=A1");
		sheet.set("A3", "=A4");
		sheet.set("A4", "='test'");
		assertEquals("test", sheet.evaluate("A2"));
	}

}
