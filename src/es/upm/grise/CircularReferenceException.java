package es.upm.grise;

@SuppressWarnings("serial")
public class CircularReferenceException extends Exception {
	
	public CircularReferenceException(String msg){
		super(msg);
	}

}
