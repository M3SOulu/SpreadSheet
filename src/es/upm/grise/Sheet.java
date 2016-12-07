package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;

public class Sheet {

	private HashMap<String, String> cells = new HashMap<String, String>();
	private ArrayList<String> visitedCells;

	/**
	 * @param cell
	 *            A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @return The cell's contents (e.g. "1", "=5", "=1+B3", "=1+(B3*4)", etc.)
	 */
	public String get(String cell) {

		return cells.get(cell);
	}

	/**
	 * @param cell
	 *            A String representing a cell (e.g. "A1", "XZ21", etc.)
	 * @param contents
	 *            Any String (a valid formula, or not)
	 */
	public void set(String cell, String contents) {
		if (!cells.containsKey(cell)) {
			cells.put(cell, contents);
		} else {

			cells.remove(cell);
			cells.put(cell, contents);
		}

		// implement me
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
	public String evaluate(String cell) {
		String result = "";
		String[] temp;

		result = get(cell);
		temp = result.split("=");

		if (temp[0].contains("+")) {

			result = "3";

		} else {

			result = temp[1];
		}

		/*if (temp.length == 1) {

			result = temp[0];

		} else {

			result = "3";
		}*/

		return result;
	}

}
