package environment;

public class Task1 {

	public static void main(String[] args) {
		if ( args.length < 1){
			System.err.print("There are not enough parameters!");
			System.exit(0);
		}
		
		Tasks ts = new Tasks();
		switch(args[0]){
			case ("1"):{
				ts.doTask1(args);
				System.out.println(ts.getMessage());
				break;
			}
			case ("2"):{
				ts.doTask2(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("3"):{
				ts.doTask3(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("4"):{
				ts.doTask4(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("5"):{
				ts.doTask5(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("6"):{
				ts.doTask6(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("7"):{
				ts.doTask7(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("8"):{
				ts.doTask8(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("9"):{
				ts.doTask9(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			case ("10"):{
				ts.doTask10(args);
				System.out.println(ts.getMessage());
				break;
			}
			
			default:{
				System.err.println("Invalid number of task. There are can be tasks from 1 to 10.");
			}
		}

	}
	
	

}
