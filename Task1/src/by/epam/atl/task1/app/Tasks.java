package by.epam.atl.task1.app;

import by.epam.atl.task1.exceptions.EvenExpected;
import by.epam.atl.task1.exceptions.InvalidValue;
import by.epam.atl.task1.exceptions.NegativeAndZeroNotAllowed;
import by.epam.atl.task1.exceptions.NegativeNotAllowed;
import by.epam.atl.task1.exceptions.NoEnoughDigits;
import by.epam.atl.task1.exceptions.NotEnoughParameters;
import by.epam.atl.task1.exceptions.ZeroNotAllowed;

public class Tasks {
		
	public Tasks(){
	}
	
	/*
	 * Create a linear program, the print is true, if the above statement is true and false - otherwise:
	 * The sum of the first two digits of a given four-digit number
	 * is the sum of his last two digits.
	 * Method operates with 1 number
	 */
	public String doTask1(String number) throws NotEnoughParameters, NoEnoughDigits{
		String result = "";
		
		//task condition - number length have to be bigger than 3
		//before checking number length split it by "" and "."
		String newString = number.replaceAll("[^0-9]", ""); //replace all non_digit symbol from string
		String[] tempArray = newString.split(""); //split string to array - every digit will be as array elements
			
		if (tempArray.length < 4) {
			
			throw new NoEnoughDigits("Number of digits in the second parameter has to be equal or bigger than 4!",4,tempArray.length );
		
		}
			
		//calculate sum
		try {
			//convert to integer the two first digits and sum them
			int sum1 = Integer.parseInt(tempArray[0]) + Integer.parseInt(tempArray[1]);
			//convert to integer the two last digits and sum them
			int sum2 = Integer.parseInt(tempArray[tempArray.length-2]) + Integer.parseInt(tempArray[tempArray.length-1]);
			
			if ( sum1 == sum2 ){
				result = "Task1: Number : "+number+" / does sum of the first two digits is the sum of his last two digits? true";
			}
			else {
				result = "Task1: Number : "+number+" / does sum of the first two digits is the sum of his last two digits? false";
			}
		}
		catch (NumberFormatException e) {
			throw new  NumberFormatException("Invalid the second argument! Can't convert to number "+number);
		}
			
		return result;
	}
	
	/*
	 * Calculate the value of expression by (all variables are real values):
	 * (b + sqrt(b*b + 4ac))/(2a) + a*a*a*c - sqrt(b)
	 * Method operates with 3 numbers
	 * 
	 */
	public String doTask2(String number1, String number2, String number3) throws ZeroNotAllowed, NegativeNotAllowed{
		String resultString = "";
		
		try {
				
			//parse parameters into number
			double a = Double.parseDouble(number1);
				
			//check the first number. It can't be equal zero
			if (a == 0.0) {

				throw new ZeroNotAllowed("The first number can't be equal 0!");

			}
				
			double b = Double.parseDouble(number2);
			//check the second number. It can't be less than 0
			if ( b < 0 ){

				throw new NegativeNotAllowed("The second number can't be less than 0!");					
					
			}
				
			double c = Double.parseDouble(number3);
				
			//calculate sqrt(b*b+4ac)
			double tempNumber = Math.pow(b, 2) + 4*a*c;
			if ( tempNumber < 0){

				throw new NegativeNotAllowed("Result of calculating (b*b+4ac) is less than 0. Program can't operate with complex numbers!");

			}
				
			double sqrt1 = Math.sqrt(tempNumber);
				
			//calculate b+sqrt(b*b+4ac)
			double dividend = b + sqrt1;
				
			//calculate the first summand
			double summand1 = dividend / (2*a);
				
			//calculate the third summand
			double summand3 = Math.sqrt(b);
				
			double result = summand1 - Math.pow(a, 3)*c + summand3;
			resultString = String.valueOf(result);
				
		}
		catch (NumberFormatException e) {
			return "Invalid arguments!";
		}
		
		return resultString;
	}
	
	/*
	 * Calculate the P and area of a right triangle of two of the legs (a and b).
	 * Method operates with 2 numbers - triangle legs
	 */
	public String doTask3(String number1, String number2) throws NegativeAndZeroNotAllowed{
		String resultString = "";
		
		//parse parameters into number
		double a = Double.parseDouble(number1);
			
		//check the first number. It can't be equal or less than zero
		if (a <= 0.0) {
			throw new NegativeAndZeroNotAllowed("The first number can't be equal or less than 0!");
		}
			
		double b = Double.parseDouble(number2);
		if (b <= 0.0) {
			throw new NegativeAndZeroNotAllowed("The second number can't be equal or less than 0!");
		}
			
		//calculate	hypotenuse
		double hyp = Math.sqrt(a*a + b*b);
			
		//calculate perimeter
		double perimeter = a + b + hyp;
			
		//calculate area
		double area = a*b/2;
			
		resultString = "Perimeter = "+perimeter+"  ;  Area = " + area;

		return resultString;
	}
	
	/*
	 * For these areas create a linear program that prints true, if the point with coordinates (x, y) 
	 * belongs to the shaded area, and false - otherwise
	 * Method operates with 2 parameters - point co-ordinates
	 */
	public String doTask4(String number1, String number2){
		String resultString = "";
		//border upper rectangle (set upper right co-ordinate). Other will be pertaining to mirror by real axis y
		int xUpper = 2;
		int yUpper = 4;
		
		//border lower rectangle (set lower right co-ordinate). Other will be pertaining to mirror by real axis y
		int xLower = 4;
		int yLower = -3;
		
		double x;
		double y;
		
		try{
			x = Double.parseDouble(number1);
			y = Double.parseDouble(number2);
		}
		catch (NumberFormatException e){
			return "Invalid parameters! Can't convert them into numbers!";
		}
			
		//if co-ordinate y bigger than 0, work with upper rectangle
		if ( y > 0.0 ) {
				
			if ( (x <= xUpper) && ( x >= -xUpper )&& 
				( y <= yUpper)	){
				return "true";
			}
		}else{
		
			//if co-ordinate y equal or less than 0, work with lower rectangle
			if ( (x <= xLower) && ( x >= -xLower )&& 
				( y >= yLower)	){
				return "true";
			}
		}
			
		resultString = "false";
	
		return resultString;
		
	}
	
	/*
	 * Given three real numbers. To square those whose values are non-negative, and in the fourth degree - negative.
	 * Method operates with 3 numbers
	 */
	public String doTask5(String[] numbers){
		double degreeNonNegative = 2;
		double degreeNegative = 4;
		String result = "";
		
		for (int i = 1; i<numbers.length; i++){
			try {
					
				double number = Double.parseDouble(numbers[i]);
					 
				//non-negative number square
				if (number >=0) {
					result += String.valueOf(Math.pow(number,degreeNonNegative))+"   ";
				}
				else {
					result += String.valueOf(Math.pow(number,degreeNegative))+"   ";
				}
					
			}
			catch(NumberFormatException e){
				return "Parameter "+ i +" can't be converted into double! ";
			}
		}
	
		return result;
	}
	
	/*
	 * Write a program to find the sum of the larger and smaller of the three numbers.
	 * Method operates with 3 numbers
	 */
	public String doTask6(String[] param){
		double min;
		double max;
		
		try{
			//setup min and max value
			double number = Double.parseDouble(param[1]);
			max = number;
			min = number;
				
			for (int i = 2; i< param.length; i++){
				number = Double.parseDouble(param[i]);
				
				if (number > max){
					max = number;
				}
				
				if (number < min) {
					min = number;
				} 
			}
				
			return max+" + "+min+" = "+String.valueOf(max+min);
		}
		catch(NumberFormatException e){
			
			return "One or more parameters can't be converted into double!";
		
		}
		
	}
	
	/*
	 * Write a program to calculate the values of the function F(x) on [a, b] with a step h. 
	 * The result is displayed as a table, the first column of which - the value of the argument, 
	 * the second - the corresponding values of the function
	 * F(x) = sin(x)*sin(x) - cos(2x)
	 * Method operates with 3 numbers: start position, end position and step
	 */
	public String doTask7(String param1, String param2, String param3) throws NegativeAndZeroNotAllowed, InvalidValue{
		String resultString = "";
		
		try{
			double startPosition = Double.parseDouble(param1);
			double endPosition = Double.parseDouble(param2);
			double step = Double.parseDouble(param3);
				
			//start of interval can't be less than end
			if (startPosition > endPosition){
				throw new InvalidValue("End of interval less than beginning. It is incorrect!");
			}
				
			//step can't be negative or equal zero
			if ( step <= 0){
				throw new NegativeAndZeroNotAllowed("Step has to be bigger than zero!");
			}
				
			for (double i = startPosition; i <= endPosition; i+=step){
				double result = Math.pow(Math.sin(i),2) - Math.cos(2*i);
				resultString += i+"		"+result+"\n";
			}
		}
		catch(NumberFormatException e){

			return "One or more parameters can't be converted into double! ";
		
		}
	
		return resultString;
	}
	
	/*
	 * The array A[N] entered natural numbers. Find the sum of the elements that are multiples of a given K.
	 * Method operates with parameters:
	 * param 1 = number task (it is skipped in method)
	 * param 2 = divider
	 * param 3...n = elements of array
	 */
	public String doTask8(String param1, String[] array) throws ZeroNotAllowed{
		long sum=0;
		
		try {
			long divider = Long.parseLong(param1);
			if ( divider == 0L) {
				throw new ZeroNotAllowed("Divider can't be equal zero!");
			}
			
			for (int i = 0; i < array.length; i++) {
				long array_el =  Long.parseLong(array[i]);
				
				//array element is divided by divider without excess?
				if (array_el % divider == 0){
					
					sum += array_el;
					
				}
				
			}
			return String.valueOf(sum);
		}
		catch(NumberFormatException e){

			return "One or more parameters can't be converted into long! ";
			
		}
	}
	
	/*
	 * Two-dimensional array is set with a different number of elements and a natural number k. 
	 *  Merge them into a single array, including a second array between k-th and (k + 1)-th elements
	 *  of the first array.
	 *  Method operates with 1 parameter - number of position
	 */
	public String doTask9(String param) throws NegativeNotAllowed, InvalidValue{
		int[] array1 = {1, 2, 3, 4, 5, 6};
		int[] array2 = {11,12,13};
		String resultString = "";
		
		try {
			int position = Integer.parseInt(param);
				
			if (position < 0) {
					
				throw new NegativeNotAllowed("Number of position can't be less than 0!");
			}
				
			if (array1.length <= position ){
					
				throw new InvalidValue("Size of array1 is "+array1.length+". It is impossible insert the second array in position "+position);
			}
				
			int sizeNewArray = array1.length + array2.length;
			int[] array3 = new int[sizeNewArray];
				
			//put first part of array1 into array3
			System.arraycopy(array1, 0, array3, 0, position);
			//put array2 into array2 from {position}
			System.arraycopy(array2, 0, array3, position, array2.length);
			//put the rest of array1 into array3
			System.arraycopy(array1, position, array3, position + array2.length, array1.length - position);
			//print out result array
			for (int i=0; i < sizeNewArray; i++){
				resultString +=array3[i]+" ";
			}
								
		}
		catch(NumberFormatException e){
			return "The second parameter can't be converted into integer! ";
		}
	
		return resultString;
	}
	
	/*
	 * Form a square matrix of order n for a given sample (n - even)
	 * Method operates with 1 number - n
	 */
	public String doTask10(String param) throws EvenExpected{
		String result = "";
		
		try{
			int order = Integer.parseInt(param);
				
			//checking if order is even
			if (order % 2 != 0) {
				throw new EvenExpected("Matrix order has to be even!", order);
			}
			
			//make matrix with order {order}
			result = makeMatrix(order);
			
		}
		catch(NumberFormatException e){

			return "The second parameter can't be converted into integer! ";

		}
		
		return result;
	}
	
	private String makeMatrix(int order){
		String result="";
		
		int[][] matrix = new int[order][order];
		
		for (int i = 0; i < order; i++) {
			for (int j = 0; j < order; j++){
				
				matrix[i][j] = generateMatrixElement(i, j, order);
				
				result += matrix[i][j]+" ";
			}
			result +="\n";
		}
		
		return result;
	}
	
	private int generateMatrixElement(int number1, int number2, int order){
		int element=0;
		
		if ( number1 % 2 == 0){
			element = number2 +1;
		} else {
			element = order - number2;
		}
		
		return element;
		
	}
	
	/*---------------------------------------
	 	
	/*
	 * Checking for valid number of parameters in array {param} and type of parameters
	 * numberParam - valid number of parameters in array {param} for checking. When do check
	 * 					it is important to add 1 to length because the first element of {param}
	 * 					is always contains task number 
	 * typeParam  - type for checking parameter
	 * param	   - array with parameters for checking
	 */
	public String ifValidParameters(int numberParam, String[] param) throws NotEnoughParameters{
		//checking array length
		if (param.length != numberParam+1){

			throw new NotEnoughParameters("Invalid number of arguments!",numberParam+1, param.length);
		}
		
		//checking type of parameters
		for (int i=1; i<(numberParam+1) ; i++){
			
			if (! isNumber(param[i])){
				
				throw new NumberFormatException("Argument number "+(i+1)+" has to be numeric!");
			}
			
		}
		
		return "";
	}
	
	/*
	 * Do checking if {stringForCheck} is a number
	 */
	private boolean isNumber(String stringForCheck){
		
		//try to parse string into float. 
		try {
			float resultNumber = Float.parseFloat(stringForCheck);
		}
		catch (NumberFormatException e){
			//it is not a number
			return false;
		}
		
		return true;
	}
}
