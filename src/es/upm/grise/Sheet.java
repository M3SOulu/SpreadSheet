package es.upm.grise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
		int sum = 0;
		result = get(cell);

		if (result.contains("=")) {

			temp = result.split("=");

			for (int i = 0; i < temp[1].length(); i++) {
				try {
					if (temp[1].charAt(i) <= ')' || temp[1].charAt(i) >= ':' || temp[1].charAt(i) == '.'
							|| temp[1].charAt(i) == ',') {
						throw new CircularReferenceException();
					}
				} catch (CircularReferenceException e) {
					return "#Error";
				}
			}

			if (temp[1].contains("+")) {

				result = arithmeticOperation(temp[1], "+");

			} else if (temp[1].contains("-")) {

				result = arithmeticOperation(temp[1], "-");
			} else if (temp[1].contains("*")) {
				result = arithmeticOperation(temp[1], "*");
			} else {
				result = arithmeticOperation(temp[1], "/");

			}
		}

		return result;
	}

	private String arithmeticOperation(String formula, String operation) {
		int sum;
		Scanner sc = new Scanner(formula);

		sc.useDelimiter("[+-\\/*]{1,}");

		sum = sc.nextInt();

		while (sc.hasNext()) {
			switch (operation) {
			case "+":
				sum = sum + sc.nextInt();
				break;
			case "-":
				sum = sum - sc.nextInt();
				break;
			case "*":
				sum = sum * sc.nextInt();
				break;
			case "/":
				sum = sum / sc.nextInt();
				break;

			}

		}

		return String.valueOf(sum);
	}

}
