package by.epam.atl.hotel.view;

import java.util.List;

import by.epam.atl.hotel.bean.User;

public class UsersConsoleView {

	public void print(User user){
		
		System.out.println(" Userid: " + user.getUserID() + "\n"
						+ " login: " + user.getUserLogin() + "\n"
						+ " password: " + user.getUserPassword() + "\n"
						+ " name: " + user.getUserName() + "\n"
						+ " email: " + user.getEmail() + "\n"
						+ " type: " + user.getType() + "\n"
						+ " banned: " + (user.isBanned() ? "yes" : "no"));
		
	}
	
	public void print(List<User> users){
		
		for (User usr : users){
			print(usr);
		}
		
	}
	
	public void print(User... users){
		
		for (User usr : users){
			print(usr);
		}
		
	}
	
}
