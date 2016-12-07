package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {

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
	public String evaluate(String cell) {
		String contentValue = get(cell);
		String result = "#Error";
		
		// caso in cui non è una formula
		// quindi la stringa valore non inizia con '\'' o '='
		if(contentValue.charAt(0) != '=' && contentValue.charAt(0) != '\''){			
			
			//controllo dei caratteri non accettati
			try{
				for(int i = 0; i < contentValue.length(); i++){
					
					// lancio eccezione nel caso in cui ci sono caratteri diversi dalle 10 cifre o diversi dal '-' 
					// altrimenti la stringa è corretta					
					if((contentValue.charAt(i) < '0' || contentValue.charAt(i) > '9') && contentValue.charAt(i) != '-'){					
						throw new ComputationErrorException();
					}else
						result = contentValue;
				}
			}catch(ComputationErrorException e){
				result = "#Error";
			}

		// caso in cui è una stringa
		}else if (contentValue.charAt(0) == '\'' && contentValue.charAt(contentValue.length()-1) == '\'')
			
			//restituisco la stringa senza apici 
			result = contentValue.substring(1, contentValue.length()-1);
		
		// caso in cui è una formula
		else {
			result = contentValue.substring(1, contentValue.length());
		}

		
		return result;
	}

}
