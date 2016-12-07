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
	public String evaluate(String cell) {
		String s = "";
		char[] array;
		array = this.get(cell).toCharArray();
		if (array[0] == '=') {
			String arg = this.get(cell).substring(1);
			if (cells.containsKey(arg)) {
				visitedCells = new ArrayList<>();
				if (!visitedCells.contains(arg)) {
					visitedCells.add(arg);
					s = evaluate(arg);
				} else {
					s = "#Circular";
				}
			} else {
				s = valutaNumAndString(arg);
			}

		} else {
			s = valutaNumAndString(this.get(cell));
		}

		return s;
	}

	public String valutaNumAndString(String content) {
		char[] array;
		String s;
		array = content.toCharArray();
		if (array[0] == '\'' && array[array.length - 1] == '\'') {
			if (content.substring(1, array.length - 1).isEmpty())
				s = "#Error";
			else
				s = content.substring(1, array.length - 1);
		} else {
			try {
				Integer.parseInt(content);
				s = content;
			} catch (NumberFormatException e) {
				s = "#Error";
			}

		}
		return s;
	}

}
