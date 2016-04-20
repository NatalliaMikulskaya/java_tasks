package environment;

public class CheckParameters {
	private static String NUMBER = "number"; 
	private String message;
	
	public CheckParameters(){
		message = "";
	}
	
	public String getMessage(){
		return message;
	}
	
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
			if(type_param.equals(NUMBER)){
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
