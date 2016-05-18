package by.epam.atl.hotel.view;

import java.util.List;

import by.epam.atl.hotel.bean.Room;

public class RoomsConcoleView {

	public void print(Room room){
		System.out.println("Room # " + room.getId() + "\n"
				+ "		capacity: " + room.getCapacity() + "\n"
				+ "		type: " + room.getType() + "\n"
				+ "		can smoke: " + (room.isSmoke() ? "yes" : "no") + "\n"
				+ "		available: " + (room.isAvailable() ? "yes" : "np"));
	}
	
	public void print(List<Room> rooms){
		
		for (Room r : rooms){
			print(r);
		}
		
	}
	
	public void print(Room... rooms){
		
		for (Room r : rooms){
			print(r);
		}
		
	}
}
