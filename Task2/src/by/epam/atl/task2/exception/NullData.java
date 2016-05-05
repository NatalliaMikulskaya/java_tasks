package by.epam.atl.task2.exception;

public class NullData extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NullData(String message){
		super(message);
	}
	
	public String getMessage(){
		return super.getMessage();
	}

}
