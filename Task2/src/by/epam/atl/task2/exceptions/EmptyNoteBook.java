package by.epam.atl.task2.exceptions;

public class EmptyNoteBook extends Exception {
	private static final long serialVersionUID = 1L;
	
	public EmptyNoteBook(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
