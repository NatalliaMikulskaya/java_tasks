package by.epam.atl.hotel.bean;

public class DbProperties {

	private String location="";
	private String login="";
	private String password="";
	
	public DbProperties(){
		
	}
	
	public void setLocation(String loc){
		
		location = loc;
	}
	
	public void setLogin(String log){
		
		login = log;
	}
	
	public void setPassword(String pas){
		
		password = pas;
	}

}
