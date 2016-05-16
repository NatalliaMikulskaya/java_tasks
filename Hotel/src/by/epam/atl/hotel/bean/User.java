package by.epam.atl.hotel.bean;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private int userID = 0;
	private String userLogin;
	private String userPassword;
	private List<Permission> access;
	private boolean isBanned = false;
	
	public User(){
		access = new ArrayList<Permission>();
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public void setUserID(int id){
		userID = id;
	}
	
	public void serUserLogin(String login){
		userLogin = login;
	}
	
	public void setUserPassword(String password){
		userPassword = password;
	}
	
	public void setAccess(List<Permission> rights){
		access = rights;
	}
	
	public void addAccess(Permission p){
		access.add(p);
	}
	
	public void setIsBanned(boolean flagBan){
		isBanned = flagBan;
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
	
	public List<Permission> getAccess(){
		return access;
	}
	
	public boolean isBanned(){
		return isBanned;
	}
	
	@Override
	public int hashCode(){
		int prime = 47;
		int result = 1;
		
		result = result * prime + ((userName == null) ? 0 : userName.hashCode()) ;
		result = result * prime + userID;
		result = result * prime + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = result * prime + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = result * prime + ((access == null) ? 0 : access.hashCode());
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
		if (access == null) {
			if (other.access != null) {
				return false;
			}
		} else if (!access.equals(other.access)) {
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
		result.append("[ isBanned:  "+ isBanned + ", \n");
		result.append("[ access:  "+ access.toString() + "] \n");
		
		return result.toString();
	}
}
