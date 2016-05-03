package exceptions;

public class NegativeNotAllowed extends Exception {

	private static final long serialVersionUID = 13L;

	public NegativeNotAllowed(String message){
		super(message);
	}
}
