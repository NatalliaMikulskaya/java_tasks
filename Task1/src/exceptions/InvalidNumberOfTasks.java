package exceptions;

public class InvalidNumberOfTasks extends Exception {
	
	private static final long serialVersionUID = 7L;

	public InvalidNumberOfTasks(String message){
		super(message);
	}
	
	public String getMessage(){
		return this.getMessage();
	}
}
