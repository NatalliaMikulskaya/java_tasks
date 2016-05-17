package by.epam.atl.hotel.bean;

import java.io.Serializable;

public class User implements Serializable{
	
	// Constants ------------------------------------
    private static final long serialVersionUID = 1L;

    // Properties -----------------------------------
	private String userName;
	private int userID = 0;
	private String userLogin;
	private String userPassword;
	private TypeUser type;
	private String email;
	private boolean isBanned = false;
	
	public User(){
		
	}
	
	// Getters/setters -------------------------------
	public void setUserName(String name){
		userName = name;
	}
	
	public void setUserID(int id){
		userID = id;
	}
	
	public void setUserLogin(String login){
		userLogin = login;
	}
	
	public void setUserPassword(String password){
		userPassword = password;
	}
	
	public void setType(TypeUser userType){
		type = userType;
	}
	
	public void setIsBanned(boolean flagBan){
		isBanned = flagBan;
	}
	
	public void setEmail(String mail){
		email = mail;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public String getUserLogin(){
		return userLogin;
	}
	
	public String getUserPassword(){
		return userPassword;
	}
	
	public TypeUser getType(){
		return type;
	}
	
	public boolean isBanned(){
		return isBanned;
	}
	
	public String getEmail(){
		return email;
	}
	
	// Object overrides --------------------------
	@Override
	public int hashCode(){
		int prime = 47;
		int result = 1;
		
		result = result * prime + ((userName == null) ? 0 : userName.hashCode()) ;
		result = result * prime + userID;
		result = result * prime + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = result * prime + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = result * prime + ((type == null) ? 0 : type.hashCode());
		result = result * prime + ((email == null) ? 0 : email.hashCode());
		result = result * prime + (isBanned ? 1231 : 1237);
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		User other = (User) obj;
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		
		if (isBanned != other.isBanned) {
			return false;
		}
		
		if (userID != other.userID) {
			return false;
		}
		
		if (userLogin == null) {
			if (other.userLogin != null) {
				return false;
			}
		} else if (!userLogin.equals(other.userLogin)) {
			return false;
		}
		
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)){
			return false;
		}
		
		if (userPassword == null) {
			if (other.userPassword != null){
				return false;
			}
		} else if (!userPassword.equals(other.userPassword)){
			return false;
		}
		

		if (email == null) {
			if (other.email != null){
				return false;
			}
		} else if (!email.equals(other.email)){
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder result;
		
		result = new StringBuilder((getClass().getName()+ " @ "));
		result.append("[ userID:  "+ userID + ", \n");
		result.append("[ userName:  "+ userName + ", \n");
		result.append("[ userLogin:  "+ userLogin + ", \n");
		result.append("[ userPassword:  "+ userPassword + ", \n");
		result.append("[ email:  "+ email + ", \n");
		result.append("[ isBanned:  "+ isBanned + ", \n");
		result.append("[ access:  "+ type.toString() + "] \n");
		
		return result.toString();
	}
}
