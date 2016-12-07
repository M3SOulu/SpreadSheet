package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {
	private static final String[] SPECIAL_CHARACTERS = new String[]{"\\", "/" , "*" , "?" , ":" , "[" , "]", "."};
	private HashMap <String, String> cells = new HashMap <String, String>();
	private ArrayList <String> visitedCells;

	/**
	 * @param cell	A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return		The cell's contents (e.g. "1", "=5", "=1+B3", "=1+(B3*4)", etc.)
	 */
	public String get(String cell) {
		return cells.get(cell);
	}

	/**
	 * @param cell		A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @param contents	Any String (a valid formula, or not)
	 */
	public void set(String cell, String contents) {
		cells.put(cell, contents);
	}

	/**
	 * @param cell	A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return		The evaluation of the cell's contents (e.g. if the cell contains "1"
	 * 				then returns "1"; if the cell contains "=1+2" returns "3"; if the
	 * 				cell contains "=1+B3", then a recursive evaluation is performed). If
	 * 				the evaluation gives an incorrect value (e.g.: "=1/0") return "#Error".
	 *				In case of circular references, return #Circular
	 * @throws CircularReferenceException 
	 */
	public String evaluate(String cell) throws CircularReferenceException, ComputationErrorException {
		String result = "#Error";
		String value = get(cell);
		
		if (value.startsWith("=")){ //Formula
			
		}
		else if (value.startsWith("'") && value.endsWith("'")){ //String
			result = value.replaceAll("'", "");
		}
		else if (!containsSpecialCharacters(value)){ //Integer
			result = value;
		}
		return result;
	}
	
	public boolean containsSpecialCharacters(String str){
		boolean result = false;
		for (String sc : SPECIAL_CHARACTERS){
			result |= str.contains(sc);
		}
		return result;
	}

}
