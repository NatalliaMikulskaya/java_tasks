package by.epam.atl.task2.exception;

public class EmptyString extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmptyString(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
