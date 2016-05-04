package exceptions;

public class InvalidValue extends Exception {
	
	private static final long serialVersionUID = 27L;

	public InvalidValue(String message){
		super(message);
	}
	
	public String getMessage(){
		return this.getMessage();
	}
}
