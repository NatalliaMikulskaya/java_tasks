package by.epam.atl.task1.exceptions;

public class NotEnoughParameters extends Exception{

	private static final long serialVersionUID = 5L;
	private int numberExpected;
	private int numberReceived;
	
	public NotEnoughParameters(String message, int nExpected, int nReceived){
		super(message);
		numberExpected = nExpected;
		numberReceived = nReceived;
	}
	
	public String getMessage(){
		return super.getMessage();
	}
	
	public String toString(){
		return (this.getClass().getName()+": Parameters number expected: "+numberExpected+"\n Parameters were received: "+numberReceived);
	}
}
