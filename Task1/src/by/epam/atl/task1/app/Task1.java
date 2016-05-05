package by.epam.atl.task1.app;

import org.apache.log4j.Logger;

import by.epam.atl.task1.exception.EvenExpected;
import by.epam.atl.task1.exception.InvalidNumberOfTasks;
import by.epam.atl.task1.exception.InvalidValue;
import by.epam.atl.task1.exception.NegativeAndZeroNotAllowed;
import by.epam.atl.task1.exception.NegativeNotAllowed;
import by.epam.atl.task1.exception.NoEnoughDigits;
import by.epam.atl.task1.exception.NotEnoughParameters;
import by.epam.atl.task1.exception.ZeroNotAllowed;

public class Task1 {
	private static Logger log = Logger.getLogger(Task1.class.getName());

	public static void main(String[] args) {
	
		String result = "";
		try{
			if ( args.length < 1){
				throw new NotEnoughParameters("There are not enough parameters!", 2, 1);
			}
		}
		catch(NotEnoughParameters e){
			log.error("Exception: "+ e.toString());
			return;

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
					log.info("Task 1: "+result);
					
				}
				catch (NotEnoughParameters|NoEnoughDigits|NumberFormatException e){
					log.error("Task1: "+ e.toString());
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
				
					log.info("Task 2: "+result);
				}
				catch (NotEnoughParameters|NegativeNotAllowed|ZeroNotAllowed e){
					log.error("Task 2: "+ e.toString());
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
				
					log.info("Task 3: "+result);
				}
				catch(NotEnoughParameters|NegativeAndZeroNotAllowed e){
					log.error("Task 3: "+ e.toString());
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
				
					log.info("Task 4: "+result);
				}
				catch (NotEnoughParameters e){
					log.error("Task 4: "+ e.toString());
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
				
					log.info("Task 5: "+result);
				}
				catch(NotEnoughParameters e){
					log.error("Task 5: "+ e.toString());
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
				
					log.info("Task 6:"+result);
				}
				catch (NotEnoughParameters e){
					log.error("Task6: "+ e.toString());
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
				
					log.info("Task 7:"+result);
				}
				catch(NotEnoughParameters|NegativeAndZeroNotAllowed|InvalidValue e){
					log.error("Task 7: "+ e.toString());
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
						log.info("Task 8:"+result);
					
					}else {
						throw new NotEnoughParameters("Task 8 has to have at least 3 arguments: task number, divider and array with at least one element.",3,args.length);
					}
					
				}
				catch (NotEnoughParameters|ZeroNotAllowed e){
					log.error("Task8: : "+ e.toString());
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
				
					log.info("Task 9:"+result);
				}
				catch(NotEnoughParameters|InvalidValue|NegativeNotAllowed e){
					log.error("Task 9: "+ e.toString());
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
				
					log.info("Task 10: "+result);
				}
				catch(NotEnoughParameters|EvenExpected e){
					log.error("Task 10: "+ e.toString());
					
				}
				
				break;
			}
			
			default:{
				try{
					throw new InvalidNumberOfTasks("Invalid number of task. There are can be tasks from 1 to 10.", args[0]);
				}
				catch(InvalidNumberOfTasks e){
					log.error("Exception: "+ e.toString());
				}
			}
		}

	}
	
	

}
