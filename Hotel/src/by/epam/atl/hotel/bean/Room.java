package by.epam.atl.hotel.bean;

import java.io.Serializable;

public class Room implements Serializable{
	// Constants ------------------------------------
	private static final long serialVersionUID = 1L;

	// Properties -----------------------------------
	private int id;
	private int number;
	private TypeRoom type;
	private int capacity=1;
	private boolean available;
	private boolean smoke;

	public Room(){

	}

	// Getters/setters -------------------------------
	public void setId(int roomId){
		id = roomId;
	}

	public void setNumber(int roomNumber){
		number = roomNumber;
	}

	public void setType(TypeRoom tr){
		type = tr;
	}

	public void setCapacity(int cap){
		capacity = cap;
	}

	public void setAvailable(boolean isAvalable){
		available = isAvalable;
	}

	public void setSmoke(boolean canSmoke){
		smoke = canSmoke;
	}

	public int getId(){
		return id;
	}

	public int getNumber(){
		return number;
	}

	public TypeRoom getType(){
		return type;
	}

	public int getCapacity(){
		return capacity;
	}

	public boolean isAvailable(){
		return available;
	}

	public boolean isSmoke(){
		return smoke;
	}

	@Override
	public int hashCode(){
		int prime = 47;
		int result = 1;

		result = result * prime + id;
		result = result * prime + number;
		result = result * prime + ((type == null) ? 0 : type.hashCode());
		result = result * prime + capacity;
		result = result * prime + (available ? 1231 : 1237);
		result = result * prime + (smoke ? 1231 : 1237);

		return result;
	}

	@Override
	public String toString(){
		StringBuffer result = new StringBuffer(getClass().getName()+" @ [");

		result.append(" id: "+id);
		result.append(",\n number: "+ number);
		result.append(",\n type: "+((type == null) ? "-": type.toString() ));
		result.append(",\n capacity: "+((capacity == 0) ? "-": capacity ));
		result.append(",\n available: "+(available ? "yes": "no" ));
		result.append(",\n smoke: "+(smoke ? "yes ]": "no ]" ));

		return result.toString();
	}


	@Override
	public boolean equals(Object obj){

		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (getClass() != obj.getClass()){
			return false;
		}

		Room other = (Room) obj;

		if (other.id != id){
			return false;
		}

		if (other.number != number){
			return false;
		}

		if (other.type != type){
			return false;
		}

		if (other.capacity != capacity){
			return false;
		}

		if (other.available != available) {
			return false;
		}


		if (other.smoke != smoke) {
			return false;
		}

		return true;

	}
}
