package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.upm.grise.CircularReferenceException;
import es.upm.grise.ComputationErrorException;
import es.upm.grise.Sheet;

public class SheetTest {

	@Test
	public void testErrorOneDivZero() throws ComputationErrorException, CircularReferenceException{
	//arrange
	Sheet mySheet = new Sheet();	
	//act
	String cell = "1/0";
	try {
		String result = mySheet.evaluate("1/0");
	} catch (ComputationErrorException e) {
		//assert
		assertEquals("#Error",mySheet.evaluate(cell));
		e.printStackTrace();
	}	

	}
	
	

}
