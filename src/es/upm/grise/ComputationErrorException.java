package es.upm.grise;

public class ComputationErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -361710548474170649L;

	ComputationErrorException(){
		super();
	}
	
	ComputationErrorException(String error){
		System.err.println(error);
	}
}
