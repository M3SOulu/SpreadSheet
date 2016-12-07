package es.upm.grise;

//import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {

	private HashMap<String, String> cells = new HashMap<String, String>();
	// private ArrayList <String> visitedCells;

	/**
	 * @param cell
	 *            A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return The cell's contents (e.g. "1", "=5", "=1+B3", "=1+(B3*4)", etc.)
	 * @throws ComputationErrorException
	 */
	public String get(String cell) throws ComputationErrorException {
		if (cells.containsKey(cell)) {
			return cells.get(cell);
		} else {
			throw new ComputationErrorException("#Error");
		}
	}

	/**
	 * @param cell
	 *            A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @param contents
	 *            Any String (a valid formula, or not)
	 */
	public void set(String cell, String contents) {
		cells.put(cell, contents);
	}

	/**
	 * @param cell
	 *            A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return The evaluation of the cell's contents (e.g. if the cell contains
	 *         "1" then returns "1"; if the cell contains "=1+2" returns "3"; if
	 *         the cell contains "=1+B3", then a recursive evaluation is
	 *         performed). If the evaluation gives an incorrect value (e.g.:
	 *         "=1/0") return "#Error". In case of circular references, return
	 *         #Circular
	 * @throws CircularReferenceException
	 */
	public String evaluate(String cell) throws CircularReferenceException {
		String value = null;
		if (cells.containsKey(cell)) {
			String valueCell = cells.get(cell);
			for (int j = 0; j < valueCell.length(); j++) {
				if (!(Character.isDigit(valueCell.charAt(j)) || Character.isAlphabetic(valueCell.charAt(j))) && valueCell.charAt(j) != '='
						&& valueCell.charAt(j) != '+' && valueCell.charAt(j) != '-' && valueCell.charAt(j) != '*'
						&& valueCell.charAt(j) != '/' && valueCell.charAt(j) != '\'') {
					throw new CircularReferenceException("#Error.");
				}
			}
			if (valueCell.charAt(0) == '=') {
				int result = 0;
				for (int i = 0; i < valueCell.length(); i++) {
					if (valueCell.charAt(i) == '+') {
						String a = String.valueOf(i - 1);
						String b = String.valueOf(i + 1);
						result = Integer.parseInt(a) + Integer.parseInt(b);
						return value = String.valueOf(result);
					} else if (valueCell.charAt(i) == '-') {
						String a = String.valueOf(i - 1);
						String b = String.valueOf(i + 1);
						result = Integer.parseInt(a) - Integer.parseInt(b);
						return value = String.valueOf(result);
					} else if (valueCell.charAt(i) == '*') {
						String a = String.valueOf(i - 1);
						String b = String.valueOf(i + 1);
						result = Integer.parseInt(a) * Integer.parseInt(b);
						return value = String.valueOf(result);
					} else if (valueCell.charAt(i) == '/') {
						String a = String.valueOf(i - 1);
						String b = String.valueOf(i + 1);
						result = Integer.parseInt(a) / Integer.parseInt(b);
						return value = String.valueOf(result);
					}
				}
			} else {
				return value = cells.get(cell);
			}
		} else {
			throw new CircularReferenceException();
		}
		return value;
	}

}
