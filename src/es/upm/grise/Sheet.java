package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {

	private HashMap <String, String> cells = new HashMap <String, String>();
	private ArrayList <String> visitedCells = new ArrayList<>();

	/**
	 * @param cell	A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return		The cell's contents (e.g. "1", "=5", "=1+B3", "=1+(B3*4)", etc.)
	 * @throws Exception 
	 */
	public String get(String cell){
		String formula = "";
		formula = cells.get(cell);

		return formula;
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
	 * @throws ComputationErrorException 
	 */
	public String evaluate(String cell) throws CircularReferenceException, ComputationErrorException{
		Integer operation = 0;
		String total = "";
		String formula = get(cell);
		formula.replaceAll(" ", "");
		visitedCells.add(cell);
		for (int i = 0; i < formula.length(); i++){
			int countOP=0;
			int countCL=0;
			if(formula.charAt(i) == '(')
				countOP++;
			if(formula.charAt(i) == ')')
				countCL++;
			if(countOP != countCL)
				throw new ComputationErrorException("#Error");
		}
		for (int i = 0; i < formula.length(); i++) {
			if (formula.charAt(0) == '=') {
				if (formula.charAt(i) >= '0' && formula.charAt(i) <= '9') {
					operation = Integer.parseInt("" + formula.charAt(1));
				}
				if ((formula.charAt(i) >= 'A') && (formula.charAt(i) <= 'Z')){
					int count = i;
					while((formula.charAt(count)!='+')&&
						  (formula.charAt(count)!='-')&&
						  (formula.charAt(count)!='*')&&
						  (formula.charAt(count)!='/')&&
						  (formula.charAt(count)!='%')){
						count ++;
					}
					if(visitedCells.contains(formula.substring(i, count)))
						throw new CircularReferenceException("#Circular");
					operation = Integer.parseInt(evaluate(formula.substring(i, count)));
					i = count;
				}		
				if (formula.charAt(i) == '+'){
					if(((formula.charAt(i+1) >= 'A') && (formula.charAt(i+1) <= 'Z')))
						total = "" + (operation + Integer.parseInt(evaluate(formula.substring(++i))));
					else
						total = "" + (operation + Integer.parseInt("" + formula.charAt(++i)));
				}
				else if (formula.charAt(i) == '-'){
					if(((formula.charAt(i+1) >= 'A') && (formula.charAt(i+1) <= 'Z')))
						total = "" + (operation - Integer.parseInt(evaluate(formula.substring(++i))));
					else
						total = "" + (operation - Integer.parseInt("" + formula.charAt(++i)));
				}
				else if (formula.charAt(i) == '*'){
					if(((formula.charAt(i+1) >= 'A') && (formula.charAt(i+1) <= 'Z')))
						total = "" + (operation - Integer.parseInt(evaluate(formula.substring(++i))));
					else
						total = "" + (operation * Integer.parseInt("" + formula.charAt(++i)));
				}
				else if (formula.charAt(i) == '/') {
					if(((formula.charAt(i+1) >= 'A') && (formula.charAt(i+1) <= 'Z')))
						total = "" + (operation - Integer.parseInt(evaluate(formula.substring(++i))));
					else if (formula.charAt(i + 1) != '0')
						total = "" + (operation / Integer.parseInt("" + formula.charAt(++i)));
					else
						throw new ComputationErrorException("#Error");
				}else if (formula.charAt(i) == '%')
					if (formula.charAt(i + 1) != '0')
						total = "" + (operation % Integer.parseInt("" + formula.charAt(++i)));
					else
						throw new ComputationErrorException("#Error");
			} else {
				total = formula;
			}
		}
		return total;
	}

}
