package environment;

public class Task1 {

	public static void main(String[] args) {
		if ( args.length < 1){
			System.err.print("There are not enough parameters!");
			System.exit(0);
		}
		
		Tasks ts = new Tasks();
		switch(args[0]){
			//Task 1 - sum the first two digits and the last two digits of number
			case ("1"):{
				ts.doTask1(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 2 - calculating expression
			case ("2"):{
				ts.doTask2(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 3 - calculating perimeter and area of triangle
			case ("3"):{
				ts.doTask3(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 4 - checking point is in area
			case ("4"):{
				ts.doTask4(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 5 - raising to the power non-negative and negative numbers
			case ("5"):{
				ts.doTask5(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 6 - sum the largest and the smallest numbers 
			case ("6"):{
				ts.doTask6(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 7 - values of the function with given step
			case ("7"):{
				ts.doTask7(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 8 - sum array elements which are divisible by given number
			case ("8"):{
				ts.doTask8(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 9 - merging arrays 
			case ("9"):{
				ts.doTask9(args);
				System.out.println(ts.getMessage());
				break;
			}
			//Task 10 - generating matrix
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
