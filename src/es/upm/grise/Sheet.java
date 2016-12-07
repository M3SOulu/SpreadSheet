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
		return null;
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
		String content = cells.get(cell);
		int a=0;
		int b=0;
		int startIndex = 1;
		if(content.charAt(0)=='='){
			if(content.charAt(1)=='-') startIndex = 2;
			for(int i=startIndex; i<content.length(); i++){
				int num = 1;
				if(num==1){
					if(content.charAt(i)=='+') num=2;
					if(content.charAt(i)=='-') num=3;
					if(content.charAt(i)>='0'||content.charAt(i)>='9'){
						a+=(int) (Character.getNumericValue(content.charAt(i))*Math.pow(10,i-startIndex));
					}	
				}
				else{
					if(content.charAt(i)>='0'||content.charAt(i)>='9'){
						if(num==2)
							b+=(int) (Character.getNumericValue(content.charAt(i))*Math.pow(10,i-startIndex-1));
						else 
							b-=(int) (Character.getNumericValue(content.charAt(i))*Math.pow(10,i-startIndex-1));
					}
				}
			}
			System.out.println(a+b);
			if (startIndex==1)
				return ""+ (a+b);
			else
				return ""+ (-a+b);
		}
		for(int i=0; i<content.length(); i++){
			if(content.charAt(i)=='\'') return content.replaceAll("\'","");
			if(content.charAt(i)=='.') return "#Error";
			if(content.charAt(i)=='+') return ""+content.charAt(i+1);
			if(content.charAt(i)=='-') return ""+content.charAt(i)+content.charAt(i+1);
		}
		return content;
	}

}
