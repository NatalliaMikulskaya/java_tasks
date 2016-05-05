package by.epam.atl.task2.exception;

public class EmptyCommandList extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmptyCommandList(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
