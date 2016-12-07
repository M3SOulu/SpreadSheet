package es.upm.grise.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import es.upm.grise.CircularReferenceException;
import es.upm.grise.ComputationErrorException;
import es.upm.grise.Sheet;

public class SheetTest {

	@Test
	public void testError(){
	//arrange
	Sheet mySheet = new Sheet();	
	String result = null;
	//act
	try {
		result = mySheet.evaluate("1/0");
	} catch (ComputationErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	//assert
	assertEquals("#Error",result);
	}

}
