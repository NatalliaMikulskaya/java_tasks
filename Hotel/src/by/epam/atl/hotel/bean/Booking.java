package by.epam.atl.hotel.bean;

import java.util.Date;

public class Booking {
	
	private int orderID;
	private Room room;
	private User user;
	private String customerName;
	private Date dateFrom;
	private Date dateTo;
	
	public Booking(){
		
	}
	
	public void setOrderID(int orderNumber){
		orderID = orderNumber;
	}
	
	public void setRoom(Room room){
		this.room = room; 
	}
	
	public void setUser(User user){
		this.user = user; 
	}
	
	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	
	public void setDateFrom(Date date){
		dateFrom = date;
	}
	
	public void setDateTo(Date date){
		dateTo = date;
	}

	public int getOrderID(){
		return orderID;
	}
	
	public Room getRoom(){
		return room;
	}
	
	public User getUser(){
		return user;
	}	
	
	public String getCustomerName(){
		return customerName;
	}
	
	public Date getDateFrom(){
		return dateFrom;
	}
	
	public Date getDateTo(){
		return dateTo;
	}

	@Override
	public int hashCode() {
		final int prime = 47;
		int result = 1;
		
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + orderID;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj){
			return true;
		}
		
		if (obj == null){
			return false;
		}
		
		if (getClass() != obj.getClass()){
			return false;
		}
		
		Booking other = (Booking) obj;
		
		if (customerName == null) {
			if (other.customerName != null){
				return false;
				}
		} else if (!customerName.equals(other.customerName)){
			return false;
			}
		
		if (dateFrom == null) {
			if (other.dateFrom != null){
				return false;
				}
		} else if (!dateFrom.equals(other.dateFrom)){
			return false;
			}
		
		if (dateTo == null) {
			if (other.dateTo != null){
				return false;
			}
		} else if (!dateTo.equals(other.dateTo)){
			return false;
		}
		
		if (orderID != other.orderID){
			return false;
		}
		
		if (room == null) {
			if (other.room != null){
				return false;
			}
		} else if (!room.equals(other.room)){
			return false;
		}
		
		if (user == null) {
			if (other.user != null){
				return false;
			}
		} else if (!user.equals(other.user)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder result=new StringBuilder(getClass().getName()+" @ ");
		
		result.append("Booking [orderID = " + orderID+",\n");
		result.append("room = " + ((room == null) ? "not specified" : room.toString()) + ",\n");
		result.append("user = " + ((user == null) ? "not specified" : user.toString()) + ",\n");
		result.append("customerName = " + customerName + ",\n");
		result.append("dateFrom = " + dateFrom.toString() + ",\n");
		result.append("dateTO = " + dateTo.toString() + "]");
		
		
		return result.toString();
	}
	
	
	
	
}
