package by.epam.atl.hotel.bean;

public class DbProperties {

	private String location="";
	private String login="";
	private String password="";
	private String dbName="";
	
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

	public void setDbName(String name){
		
		dbName = name;
	}
	
	public String getLocation(){
		return location;
	}
	
	public String getLoin(){
		
		return login;
	}
	
	public String getPassword(){
		
		return password;
	}
	
	public String getDbName(){
		
		return dbName;
	}
}
