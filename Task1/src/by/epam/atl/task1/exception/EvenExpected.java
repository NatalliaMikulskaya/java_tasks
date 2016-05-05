package by.epam.atl.task1.exception;

public class EvenExpected extends Exception {
	
	private static final long serialVersionUID = 23L;

	private int number;
	
	public EvenExpected(String message, int num){
		super(message);
		number = num;
	}
	
	public String getMessage(){
		return super.getMessage();
	}
	
	public String toString(){
		return (this.getClass().getName()+": "+this.getMessage()+"\n Number was received: "+number);
	}
}
