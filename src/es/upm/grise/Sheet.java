package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {

	private HashMap <String, String> cells = new HashMap <String, String>();
	private ArrayList <String> visitedCells;

	/**
	 * @param cell	A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return		The cell's contents (e.g. "1", "=5", "=1+B3", "=1+(B3*4)", etc.)
	 * @throws Exception 
	 */
	public String get(String cell) throws Exception {
		String formula = "";
		formula = cells.get(cell);
		if(formula != null)
			return formula;
		else
			throw new Exception();
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
	 * @throws Exception 
	 * @throws CircularReferenceException 
	 */
	public String evaluate(String cell) throws Exception {
		Integer operation = 0;
		String total = "";
		char[] formula = get(cell).toCharArray();
		for(char c : formula)
			if(c != '='){
				total = "" + c;
			}else{
				if(c > '0' || c < '9'){
					operation = Integer.parseInt(""+c);
					continue;
				}
				else if(c > 'A' || c < 'Z'){
					if(c > 'A' || c < 'Z'){
					}
				}
			if(c == '+')
				total = "" + (operation + Integer.parseInt("" + c));
			else if(c == '-')
				total = "" + (operation - Integer.parseInt(""+ c));
			
			}
		return total;
	}
}
