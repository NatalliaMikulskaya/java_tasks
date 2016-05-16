package by.epam.atl.hotel.bean;

import java.util.ArrayList;
import java.util.List;

public class Permission {
	private List<String> rights;
	
	public Permission(){
		rights = new ArrayList<String>();
	}
	
	public void setRights(List<String> listRights){
		rights  = listRights;
	}
	
	public void addRight(String right){
		rights.add(right);
	}
	
	public List<String> getRights(){
		return rights;
	}
	
	@Override
	public int hashCode(){
		int prime = 47;
		int result = 1;
		
		result = result * prime + ((rights == null ? 0 : rights.hashCode()));
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if (obj == null) {
			return false;
		}
		
		if (this == obj){
			return false;
		}
		
		if (getClass() != obj.getClass()){
			return false;
		}
		
		Permission other = (Permission) obj;
		
		if (other.rights == null){
			if (rights != null){
				return false;
			}
		}else if (!rights.equals(other.rights)){
			return false;
		}
		
		return true;
		
	}
	
	@Override
	public String toString(){
		String result = "";
		
		result = getClass().getName()+" @ [ rights: " + ((rights == null) ? "" : rights.toString());
		
		return result;
	}
}
