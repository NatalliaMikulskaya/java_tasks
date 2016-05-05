package by.epam.atl.task1.exception;

public class NoEnoughDigits extends Exception{

	private static final long serialVersionUID = 17L;
	private int numberExpected;
	private int numberReceived;
	
	public NoEnoughDigits(String message, int nExpected, int nReceived){
		super(message);
		numberExpected = nExpected;
		numberReceived = nReceived;
	}
	
	public String getMessage(){
		return super.getMessage();
	}
	
	public String toString(){
		return (this.getClass().getName()+": At least number of digits are expected: "+numberExpected+"\n Number of digits were received: "+numberReceived);
	}
}
