package exceptions;

public class NegativeAndZeroNotAllowed extends Exception {

	private static final long serialVersionUID = 13L;

	public NegativeAndZeroNotAllowed(String message){
		super(message);
	}
	
	public String getMessage(){
		return this.getMessage();
	}
}
