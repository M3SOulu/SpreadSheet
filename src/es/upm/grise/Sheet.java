package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {
	private static final String[] SPECIAL_CHARACTERS = new String[]{"\\", "/" , "*" , "?" , ":" , "[" , "]", "."};
	private static final String[] OPERATOR_CHARACTERS = new String[]{"+", "-", "/", "*", "%", "&"};
	public static final String STRING_OPERATOR_CHARACTER = "&";
	public static final String SUM = "+", SUB = "-", DIV = "/", MUL = "*", MOD ="%";
	
	private HashMap <String, String> cells = new HashMap <String, String>();
	private ArrayList <String> visitedCells;
	
	public Sheet() {
		visitedCells = new ArrayList<String>();
	}

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
		String value = cells.containsKey(cell) ? get(cell) : "";
		visitedCells.add(cell);
		
		if (value.startsWith("=")){
			if (!containsOperators(value)){
				result = evaluateValue(value.replaceFirst("=", ""));
			}
			else{
				result = evaluateFormula(value.replaceFirst("=", ""));
			}
		}
		else{
			result = evaluateValue(value);
		}
		return result;
	}

	private String evaluateFormula(String formula) throws CircularReferenceException, ComputationErrorException{
		String result = "#Error"; //If formula tries to concatenate strings with numbers (+ and & in the same formula)
		if (formula.contains(STRING_OPERATOR_CHARACTER)){ //String concat
			result = "";
			String[] strings = formula.split(STRING_OPERATOR_CHARACTER);
			for (String str : strings){
				result += evaluateValue(str);
			}
			if (result.contains("#Error")){
				result = "#Error";
			}
		}
		else{ //Integer operations
			result = "";
			String newFormula = formula.replaceAll(" ", "");
			boolean readyForOperation = false;
			String number = "", operator = "";
			for (Character c : newFormula.toCharArray()){
				if (!result.equals("#Error")){
					if (isOperator(c)){
						if (readyForOperation){
							result = doOperation(result, number, operator);
						}
						operator = c.toString();
						readyForOperation = true;
					}
					else{
						if (readyForOperation){
							number += c;
						}
						else{
							result += c;
						}
					}
				}
			}
		}
		return result;
	}
	
	private String doOperation(String a, String b, String op){
		String result = (Integer.parseInt(a) + Integer.parseInt(b)) + "";
		return result;
	}
	
	private String evaluateValue(String value) throws CircularReferenceException, ComputationErrorException{
		String result = "#Error";
		if (isString(value)){ //String
			result = value.replaceAll("'", "");
		}
		else if (isNumber(value)){ //Integer
			result = value;
		}
		else if (isCell(value)){ //Cell
			result = visitedCells.contains(value) ? "#Circular" : evaluate(value);
			visitedCells.clear();
		}
		return result;
	}
	
	private boolean isString(String value){
		boolean result = value.startsWith("'") && value.endsWith("'");
		return result;
	}
	
	private boolean isNumber(String value){
		boolean result = !containsSpecialCharacters(value);
		try{
			Integer.parseInt(value);
		}
		catch(NumberFormatException e){
			result = false;
		}
		return result;
	}
	
	private boolean isCell(String value){
		int start = 0, end = value.length() - 1;
		boolean result = value.length() == 2;
		result &= value.charAt(start) >= 'A' && value.charAt(start) <= 'Z';
		result &= value.charAt(end) >= '1' && value.charAt(end) <= '9';
		return result;
	}
	
	private boolean containsSpecialCharacters(String str){
		boolean result = false;
		for (String sc : SPECIAL_CHARACTERS){
			result |= str.contains(sc);
		}
		return result;
	}
	
	private boolean containsOperators(String str){
		boolean result = false;
		for (String sc : OPERATOR_CHARACTERS){
			result |= str.contains(sc);
		}
		return result;
	}
	
	private boolean isOperator(Character c){
		boolean result = false;
		for (String sc : OPERATOR_CHARACTERS){
			result |= c.equals(sc);
		}
		return result;
	}

}
