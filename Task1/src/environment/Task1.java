package environment;

import exceptions.EvenExpected;
import exceptions.InvalidNumberOfTasks;
import exceptions.InvalidValue;
import exceptions.NegativeAndZeroNotAllowed;
import exceptions.NegativeNotAllowed;
import exceptions.ZeroNotAllowed;
import exceptions.NotEnoughParameters;

public class Task1 {

	public static void main(String[] args) {
		String result = "";
		try{
			if ( args.length < 1){
				throw new NotEnoughParameters("There are not enough parameters!");
			}
		}
		catch(NotEnoughParameters e){
			System.out.println(result);
		}
		
		Tasks ts = new Tasks();
		switch(args[0]){
			//Task 1 - sum the first two digits and the last two digits of number
			case ("1"):{
				//checking parameters before processing them
				try {
					result = ts.ifValidParameters(1, args);
					if (result.length() == 0){
						
						result = ts.doTask1(args[1]);
						
					} 
					System.out.println(result);
				}
				catch (NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				
				break;
			}
			//Task 2 - calculating expression
			case ("2"):{
				try{
					//checking parameters before processing them
					result = ts.ifValidParameters(3, args);

					if (result.length() == 0){
						
						result = ts.doTask2(args[1], args[2], args[3]);
					
					}
				
				System.out.println(result);
				}
				catch (NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				catch (NegativeNotAllowed e){
					System.err.println(e.getMessage());
				}
				catch (ZeroNotAllowed e){
					System.err.println(e.getMessage());
				}
				break;
			}
			
			//Task 3 - calculating perimeter and area of triangle
			case ("3"):{
				try {
					//checking parameters before processing them
					result = ts.ifValidParameters(2, args);

					if (result.length() == 0){
				
						result = ts.doTask3(args[1], args[2]);
					}
				
					System.out.println(result);
				}
				catch(NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				catch(NegativeAndZeroNotAllowed e){
					System.err.println(e.getMessage());
				}
				break;
			}
			
			//Task 4 - checking point is in area
			case ("4"):{
				try{
					//checking parameters before processing them
					result = ts.ifValidParameters(2, args);

					if (result.length() == 0){
					
						result = ts.doTask4(args[1], args[2]);
					}
				
					System.out.println(result);
				}
				catch (NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				break;
			}
			
			//Task 5 - raising to the power non-negative and negative numbers
			case ("5"):{
				try {
					//checking parameters before processing them
					result = ts.ifValidParameters(3, args);

					if (result.length() == 0){
				
						String[] parameters = {args[1], args[2], args[3]};
						result = ts.doTask5(parameters);
					}
				
					System.out.println(result);
				}
				catch(NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				break;
			}
			
			//Task 6 - sum the largest and the smallest numbers 
			case ("6"):{
				try {
					//checking parameters before processing them
					result = ts.ifValidParameters(3, args);

					if (result.length() == 0){
					
						String[] parameters = {args[1], args[2], args[3]};
						result = ts.doTask6(parameters);
					}
				
					System.out.println(result);
				}
				catch (NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				break;
			}
			
			//Task 7 - values of the function with given step
			case ("7"):{
				try {
				
					//checking parameters before processing them
					result = ts.ifValidParameters(3, args);

					if (result.length() == 0){
					
						result = ts.doTask7(args[1], args[2], args[3]);
					}
				
					System.out.println(result);
				}
				catch(NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				catch(NegativeAndZeroNotAllowed e){
					System.err.println(e.getMessage());
				}
				catch (InvalidValue e){
					System.err.println(e.getMessage());
				}
				
				break;
				
			}
			
			//Task 8 - sum array elements which are divisible by given number
			case ("8"):{
				try{
					//checking parameters before processing them

					if (args.length >= 3){
					
						String[] array = {};
						//make array (start from the third parameter of args)
						for (int i=2; i<args.length; i++){
							array[i-3] = args[i];
						}
					
						result = ts.doTask8(args[1], array);
						System.out.println(result);
					
					}else {
						throw new NotEnoughParameters("Task 8 has to have at least 3 arguments: task number, divider and array with at least one element.");
					}
					
				}
				catch (NotEnoughParameters e){
					System.err.println(e.getMessage());
				}
				catch(ZeroNotAllowed e){
					
				}
				
				
				break;
			}
			//Task 9 - merging arrays 
			case ("9"):{
				try{
					//checking parameters before processing them
					result = ts.ifValidParameters(1, args);

					if (result.length() == 0){
						result = ts.doTask9(args[1]);
					}
				
					System.out.println(result);
				}
				catch(NotEnoughParameters e){
					System.out.println(result);
				}
				catch(InvalidValue e){
					System.out.println(result);
				}
				catch(NegativeNotAllowed e){
					System.out.println(result);
				}
				break;
				
			}
			//Task 10 - generating matrix
			case ("10"):{
				try {
					//checking parameters before processing them
					result = ts.ifValidParameters(1, args);

					if (result.length() == 0){
						result = ts.doTask10(args[1]);
					}
				
					System.out.println(result);
				}
				catch(NotEnoughParameters e){
					System.out.println(result);
					
				}
				catch(EvenExpected e){
					System.out.println(result);
				}
				break;
			}
			
			default:{
				try{
					throw new InvalidNumberOfTasks("Invalid number of task. There are can be tasks from 1 to 10.");
				}
				catch(InvalidNumberOfTasks e){
					System.out.println(result);
				}
			}
		}

	}
	
	

}
