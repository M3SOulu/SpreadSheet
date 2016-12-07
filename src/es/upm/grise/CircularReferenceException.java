package es.upm.grise;

public class CircularReferenceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -463664405524414739L;

	CircularReferenceException(){
		super();
	}
	
	CircularReferenceException(String error){
		System.err.println(error);
	}
}
