package by.epam.atl.task2.exceptions;

public class InvalidFileName extends Exception {
	private static final long serialVersionUID = 1L;
	
	public InvalidFileName(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
