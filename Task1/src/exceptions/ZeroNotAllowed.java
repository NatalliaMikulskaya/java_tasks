package exceptions;

public class ZeroNotAllowed extends Exception {

	private static final long serialVersionUID = 11L;

	public ZeroNotAllowed(String message){
		super(message);
	}
	
	public String getMessage(){
		return this.getMessage();
	}
}
