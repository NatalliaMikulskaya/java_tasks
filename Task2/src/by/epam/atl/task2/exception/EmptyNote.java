package by.epam.atl.task2.exception;

public class EmptyNote extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmptyNote(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
