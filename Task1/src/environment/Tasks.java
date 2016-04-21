package environment;

public class Tasks {
	private final static String TYPE_NUMBER = "number"; 
	private String message;

	public Tasks(){
		message = "";
	}
	
	public String getMessage(){
		return message;
	}
	
	/*
	 * Create a linear program, the print is true, if the above statement is true and false - otherwise:
	 *The sum of the first two digits of a given four-digit number
	 *is the sum of his last two digits.
	 */
	public void doTask1(String[] param){
		
		//checking parameters before processing them
		if (ifValidParameters(1,TYPE_NUMBER, param)) {
		
			//task condition - number length have to be bigger than 3
			//before checking number length split it by "" and "."
			String newString = param[1].replaceAll("[^0-9]", ""); //replace all non_digit symbol from string
			String[] str_array = newString.split(""); //split string to array
			
			if (str_array.length < 4) {
				message = "Number of digits in the second parameter has to be equal or bigger than 4!";
				return;
			}
			
			//calculate sum
			try {
				//convert to integer the two first digits and sum them
				int sum1 = Integer.parseInt(str_array[0]) + Integer.parseInt(str_array[1]);
				//convert to integer the two last digits and sum them
				int sum2 = Integer.parseInt(str_array[str_array.length-2]) + Integer.parseInt(str_array[str_array.length-1]);
				
				if ( sum1 == sum2 ){
					message = "true";
				}
				else {
					message = "false";
				}
			}
			catch (NumberFormatException e) {
				message = "Invalid the second argument!";
			}
			
		}
	}
	
	/*
	 * Calculate the value of expression by (all variables are real values):
	 * (b + sqrt(b*b + 4ac))/(2a) + a*a*a*c - sqrt(b)
	 * 
	 */
	public void doTask2(String[] param){
		
		//checking parameters before processing them
		if (ifValidParameters(3,TYPE_NUMBER, param)) {
			
			try {
				
				//parse parameters into number
				double a = Double.parseDouble(param[1]);
				
				//check the first number. It can't be equal zero
				if (a == 0.0) {
					message = "The first number can't be equal 0!";
					return;
				}
				
				double b = Double.parseDouble(param[2]);
				//check the second number. It can't be less than 0
				if ( b < 0 ){
					message = "The second number can't be less than 0!";					
					return;
				}
				
				double c = Double.parseDouble(param[3]);
				
				//calculate sqrt(b*b+4ac)
				double number1 = b*b + 4*a*c;
				if ( number1 < 0){
					message = "Result of calculating (b*b+4ac) is less than 0. Program can't operate with complex numbers!";
					return;
				}
				
				double sqrt1 = Math.sqrt(number1);
				
				//calculate b+sqrt(b*b+4ac)
				double dividend = b + sqrt1;
				
				//calculate the first summand
				double summand1 = dividend / (2*a);
				
				//calculate the third summand
				double summand3 = Math.sqrt(b);
				
				double result = summand1 - a*a*a*c + summand3;
				message = String.valueOf(result);
				
			}
			catch (NumberFormatException e) {
				message = "Invalid arguments!";
			}
			
		}
	}
	
	
	/*
	 * Calculate the P and area of a right triangle of two of the legs (a and b).
	 */
	public void doTask3(String[] param){
		
		//checking parameters before processing them
		if (ifValidParameters(2,TYPE_NUMBER, param)) {
			//parse parameters into number
			double a = Double.parseDouble(param[1]);
			
			//check the first number. It can't be equal or less than zero
			if (a <= 0.0) {
				message = "The first number can't be equal or less than 0!";
				return;
			}
			
			double b = Double.parseDouble(param[2]);
			if (b <= 0.0) {
				message = "The second number can't be equal or less than 0!";
				return;
			}
			
			//calculate	hypotenuse
			double hyp = Math.sqrt(a*a + b*b);
			
			//calculate perimeter
			double perimeter = a + b + hyp;
			
			//calculate area
			double area = a*b/2;
			
			message = "Perimeter = "+perimeter+"  ;  Area = " + area;
		}
	}
	
	/*
	 * For these areas create a linear program that prints true, if the point with coordinates (x, y) 
	 * belongs to the shaded area, and false - otherwise
	 */
	public void doTask4(String[] param){
		//border upper rectangle (set upper right co-ordinate). Other will be pertaining to mirror by real axis y
		int x_upper = 2;
		int y_upper = 4;
		
		//border lower rectangle (set lower right co-ordinate). Other will be pertaining to mirror by real axis y
		int x_lower = 4;
		int y_lower = -3;
		
		double x;
		double y;
		
		//checking parameters before processing them
		if (ifValidParameters(2,TYPE_NUMBER, param)) {
		
			try{
				x = Double.parseDouble(param[1]);
				y = Double.parseDouble(param[2]);
			}
			catch (NumberFormatException e){
				message = "Invalid parameters! Can't convert them into numbers!";
				return;
			}
			
			//if co-ordinate y bigger than 0, work with upper rectangle
			if ( y > 0.0 ) {
				
				if ( (x <= x_upper) && ( x >= -x_upper )&& 
					( y <= y_upper)	){
					message = "true";
					return;
				}
			}
						
			//if co-ordinate y equal or less than 0, work with lower rectangle
			if ( y <=0.0 ) {
				if ( (x <= x_lower) && ( x >= -x_lower )&& 
						( y >= y_lower)	){
					message = "true";
					return;
				}
			}
			
			message = "false";
		
		}
	}
	
	/*
	 * Given three real numbers. To square those whose values are non-negative, and in the fourth degree - negative.
	 */
	public void doTask5(String[] param){
		double degree_non_negative = 2;
		double degree_negative = 4;
		String result = "";
		
		//checking parameters before processing them
		if (ifValidParameters(3,TYPE_NUMBER, param)) {
			
			for (int i = 1; i<param.length; i++){
				try {
					
					double number = Double.parseDouble(param[i]);
					 
					//non-negative number square
					if (number >=0) {
						result += String.valueOf(Math.pow(number,degree_non_negative))+"   ";
					}
					else {
						result += String.valueOf(Math.pow(number,degree_negative))+"   ";
					}
					
					message = result;
				}
				catch(NumberFormatException e){
					message = "Parameter "+ i +" can't be converted into double! ";
					return;
				}
			}
		}
	}
	
	/*
	 * Write a program to find the sum of the larger and smaller of the three numbers.
	 */
	public void doTask6(String[] param){
		double min;
		double max;
		
		//checking parameters before processing them
		if (ifValidParameters(3,TYPE_NUMBER, param)) {
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
				
				message = max+" + "+min+" = "+String.valueOf(max+min);
			}
			catch(NumberFormatException e){
				message = "One or more parameters can't be converted into double!";
				return;
			}
		}
	}
	
	/*
	 * Write a program to calculate the values of the function F(x) on [a, b] with a step h. 
	 * The result is displayed as a table, the first column of which - the value of the argument, 
	 * the second - the corresponding values of the function
	 * F(x) = sin(x)*sin(x) - cos(2x)
	 */
	public void doTask7(String[] param){
		
		//checking parameters before processing them
		if (ifValidParameters(3,TYPE_NUMBER, param)) {
			try{
				double start_pos = Double.parseDouble(param[1]);
				double end_pos = Double.parseDouble(param[2]);
				double step = Double.parseDouble(param[3]);
				
				//start of interval can't be less than end
				if (start_pos > end_pos){
					message = "End of interval less than beginning. It is incorrect!";
					return;
				}
				
				//step can't be negative or equal zero
				if ( step <= 0){
					message = "Step has to be bigger than zero!";
					return;
				}
				
				for (double i = start_pos; i <= end_pos; i+=step){
					double result = Math.pow(Math.sin(i),2) - Math.cos(2*i);
					message += i+"		"+result+"\n";
				}
			}
			catch(NumberFormatException e){
				message = "One or more parameters can't be converted into double! ";
				return;
			}
		}
	}
	
	/*
	 * The array A[N] entered natural numbers. Find the sum of the elements that are multiples of a given K.
	 */
	public void doTask8(String[] param){
		long sum=0;
		
		if (param.length < 3){
			message = "Task 8 has to have at least 3 arguments: task number, divider and array with at least one element.";
			return;
		}
		
		try {
			long divider = Long.parseLong(param[1]);
			if ( divider == 0) {
				message = "Divider can't be equal zero!";
				return;
			}
			
			for (int i = 2; i < param.length; i++) {
				long array_el =  Long.parseLong(param[i]);
				if (array_el % divider == 0){
					sum += array_el;
				}
			}
			message = String.valueOf(sum);
		}
		catch(NumberFormatException e){
			message = "One or more parameters can't be converted into long! ";
			return;
		}
	}
	
	/*
	 * Two-dimensional array is set with a different number of elements and a natural number k. 
	 *  Merge them into a single array, including a second array between k-th and (k + 1)-th elements
	 *  of the first array.
	 */
	public void doTask9(String[] param){
		int[] array1 = {1, 2, 3, 4, 5, 6};
		int[] array2 = {11,12,13};
		
		if (ifValidParameters(1,TYPE_NUMBER, param)){
			try {
				int position = Integer.parseInt(param[1]);
				
				if (array1.length <= position ){
					message = "Size of array1 is "+array1.length+". It is impossible insert the second array in position "+position;
					return;
				}
				
				int size_new_array = array1.length + array2.length;
				int[] array3 = new int[size_new_array];
				
				System.arraycopy(array1, 0, array3, 0, position);
				System.arraycopy(array2, 0, array3, position, array2.length);
				System.arraycopy(array1, position, array3, position+array2.length, array1.length-position);
				for (int i=0; i < size_new_array; i++){
					message +=array3[i]+" ";
				}
								
			}
			catch(NumberFormatException e){
				message = "The second parameter can't be converted into integer! ";
				return;
			}
		}
	}
	
	/*
	 * Form a square matrix of order n for a given sample (n - even)
	 */
	public void doTask10(String[] param){
		
		if (ifValidParameters(1,TYPE_NUMBER, param)){
			try{
				int order = Integer.parseInt(param[1]);
				
				//checking if order is even
				if (order % 2 != 0) {
					message = "Matrix order has to be even!";
					return;
				}
				
				int[][] matrix = new int[order][order];
				for (int i = 0; i < order; i++) {
					for (int j = 0; j < order; j++){
						if ( i % 2 == 0){
							matrix[i][j] = j +1;
						} else {
							matrix[i][j] = order - j;
						}
						message += matrix[i][j]+" ";
					}
					message +="\n";
				}

			}
			catch(NumberFormatException e){
				message = "The second parameter can't be converted into integer! ";
				return;
			}
		}
	}
	
	/*---------------------------------------
	 	
	/*
	 * Checking for valid number of parameters in array {param}
	 * number_param - valid number of parameters in array {param} for checking. When do check
	 * 					it is important to add 1 to length because the first element of {param}
	 * 					is always contains task number 
	 * type_param  - type for checking parameter
	 * param	   - array with parameters for checking
	 */
	public boolean ifValidParameters(int number_param, String type_param, String[] param){
		//checking array length
		if (param.length != number_param+1){
			message = "Invalid number of arguments!";
			return false;
		}
		
		//checking type of parameters
		for (int i=1; i<(number_param+1) ; i++){
			if(type_param.equals(TYPE_NUMBER)){
				if (! isNumber(param[i])){
					message = "Argument number "+(i+1)+" has to be numeric!";
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	/*
	 * Do checking if {str_for_check} is a number
	 */
	private boolean isNumber(String str_for_check){
		//try to parse string into integer. 
		try {
			double res_number = Double.parseDouble(str_for_check);
		}
		catch (NumberFormatException e){
			//it is not integer number
			return false;
		}
		
		return true;
	}
}
