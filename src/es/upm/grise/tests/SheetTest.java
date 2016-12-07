package es.upm.grise.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import es.upm.grise.CircularReferenceException;
import es.upm.grise.ComputationErrorException;
import es.upm.grise.Sheet;

public class SheetTest {
	
	private HashMap <String, String> cells = new HashMap <String, String>();
	private Object result;

	@Test
	public void testOneNumber() throws ComputationErrorException, CircularReferenceException{
	//arrange
	Sheet mySheet = new Sheet();	
	//act
	try {
		result = mySheet.evaluate("1");
	} catch (ComputationErrorException e) {
		//assert
		assertEquals("1",result);
	}	
	}
	
	@Test
	public void testErrorOneDivZero() throws ComputationErrorException, CircularReferenceException{
	//arrange
	Sheet mySheet = new Sheet();	
	//act
	try {
		result = mySheet.evaluate("1/0");
	} catch (ComputationErrorException e) {
		//assert
		assertEquals("#Error",result);
	}	
	}
	
	@Test
	public void testErrorCharather() throws ComputationErrorException, CircularReferenceException{
	//arrange
	Sheet mySheet = new Sheet();	
	//act
	try {
		result = mySheet.evaluate("aBcd");
	} catch (ComputationErrorException e) {
		//assert
		assertEquals("#Error",result);
	}	
	}
	
	@Test
	public void testErrorNumberDecimal() throws ComputationErrorException, CircularReferenceException{
	//arrange
	Sheet mySheet = new Sheet();	
	//act
	try {
		result = mySheet.evaluate("1,4");
	} catch (ComputationErrorException e) {
		//assert
		assertEquals("#Error",result);
	}	
	}

	@Test
	public void testCell() throws ComputationErrorException, CircularReferenceException{
		//arrange
		Sheet mySheet = new Sheet();
		String cell = cells.get(result);
		//act
		result = mySheet.evaluate("=1+1");
		//assert
		assertEquals("2",result);
	}
}
