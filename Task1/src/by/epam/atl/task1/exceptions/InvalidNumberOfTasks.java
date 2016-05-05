package by.epam.atl.task1.exceptions;

public class InvalidNumberOfTasks extends Exception {
	
	private static final long serialVersionUID = 7L;
	
	private String taskNumber="";

	public InvalidNumberOfTasks(String message){
		super(message);
	}
	
	public InvalidNumberOfTasks(String message, String nTask){
		super(message);
		taskNumber = nTask;
	}
	
	public String getMessage(){
		return super.getMessage();
	}
	
	public String toString(){
		return (this.getClass().getName()+": "+this.getMessage()+"\n Task number was received: "+taskNumber);
		
	}
}
