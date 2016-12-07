package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {

	private HashMap <String, String> cells = new HashMap <String, String>();
	private ArrayList <String> visitedCells;
	private String cell;
	private String contents;

	/**
	 * @param cell	A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return		The cell's contents (e.g. "1", "=5", "=1+B3", "=1+(B3*4)", etc.)
	 */
	public String get(String cell) {
	return cell;
	}

	/**
	 * @param cell		A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @param contents	Any String (a valid formula, or not)
	 */
	public void set(String cell, String contents) {
	this.cell = cell;
	this.contents = contents;
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
	public String evaluate(String cell) throws ComputationErrorException, CircularReferenceException { //fare la catch dell eccezzione, quando trova quella stringa ritorna #error
	String result = null;
	if (cell == "1/0"){
		throw new ComputationErrorException();
	}
	if (isNumber(cell)){
		return result;
	}
	else{
		throw new ComputationErrorException();
	}
	}

	private boolean isNumber(String isNum){ //verificare che una stringa contenga interi
		boolean num = true;
		char[] seq = isNum.toCharArray();
		
		for (int i = 0; i < seq.length; i++) {
			try{
				Integer.parseInt(Character.toString(seq[i]));
			}catch (Exception e) {
				num = false;
			}
		}
		return num;
	}
}
