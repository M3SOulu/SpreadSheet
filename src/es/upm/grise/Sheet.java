package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {
	private static final String[] SPECIAL_CHARACTERS = new String[]{"\\", "/" , "*" , "?" , ":" , "[" , "]", "."};
	private static final String[] OPERATOR_CHARACTERS = new String[]{"+", "-", "/", "*", "%", "&"};
	public static final String STRING_OPERATOR_CHARACTER = "&";
	public static final String SUM = "+", SUB = "-", DIV = "/", MUL = "*", MOD ="%";
	public static final String CIRCULAR_ERROR = "#Circular", DEFAULT_ERROR = "#Error";
	
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
	public String evaluate(String cell) {
		String result = DEFAULT_ERROR;
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

	private String evaluateFormula(String formula) {
		String result = DEFAULT_ERROR; //If formula tries to concatenate strings with numbers (+ and & in the same formula)
		if (formula.contains(STRING_OPERATOR_CHARACTER)){ //String concat
			result = "";
			String[] strings = formula.split(STRING_OPERATOR_CHARACTER);
			for (String str : strings){
				result += evaluateValue(str);
			}
			if (result.contains(DEFAULT_ERROR)){
				result = DEFAULT_ERROR;
			}
		}
		else{ //Integer operations
			result = "";
			if (formula.replace("(", "").length() != formula.replace(")", "").length())
				return DEFAULT_ERROR;

			boolean readyForOperation = false;
			String number = "", operator = "";
			String newFormula = formula.contains(" ") ? formula.replaceAll(" ", "") : formula;
			for (Character c : newFormula.toCharArray()){
				if (!result.equals(DEFAULT_ERROR)){
					if (isOperator(c.toString())){
						if (readyForOperation){
							result = doOperation(result, number, operator);
							number = "";
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
			if (!result.equals(DEFAULT_ERROR)){
				result = doOperation(result, number, operator);
			}
		}
		return result;
	}
	
	private String evaluateValue(String value) {
		String result = DEFAULT_ERROR;
		if (isString(value)){ //String
			result = value.replaceAll("'", "");
		}
		else if (isNumber(value)){ //Integer
			result = value;
		}
		else if (isCell(value)){ //Cell
			result = visitedCells.contains(value) ? CIRCULAR_ERROR : evaluate(value);
			visitedCells.clear();
		}
		return result;
	}
	
	private String doOperation(String a, String b, String op){
		String result = DEFAULT_ERROR;
		if (isCell(b)){
			b = evaluateValue(b);
			if (b.equals(CIRCULAR_ERROR)){
				result = CIRCULAR_ERROR;
			}
		}
		int numA = 0, numB = 0;
		try{
			numA = (Integer.parseInt(a));
			numB = (Integer.parseInt(b));
			
			switch(op){
			case SUM:
				result = numA + numB + "";
				break;
			case SUB:
				result = numA - numB + "";
				break;
			case DIV:
				result = numB != 0 ? numA / numB + "" : DEFAULT_ERROR;
				break;
			case MUL:
				result = numA * numB + "";
				break;
			case MOD:
				result = numA % numB + "";
				break;
			default:
				result = DEFAULT_ERROR;
				break;
			}
			
		} catch(NumberFormatException e){
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
	
	private boolean isCell(String value){ //TODO expand range of cells
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
	
	private boolean isOperator(String c){
		boolean result = false;
		for (String sc : OPERATOR_CHARACTERS){
			result |= c.equals(sc);
		}
		return result;
	}

}
