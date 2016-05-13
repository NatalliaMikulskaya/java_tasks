package by.epam.atl.marshalling.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType (namespace="http://www.example.org/personSchema")
@XmlAccessorType ( XmlAccessType.FIELD)
/*@XmlType( propOrder = { "authorData:firstName",
						"authorData:secondName", 
						"authorData:parentName" })*/
@XmlRootElement(name = "author")
public class Person {
	@XmlElementWrapper(name="author")
	
	@XmlElement(name = "firstName", required = true)
	private String firstName="";
	
	
	@XmlElement(name = "secondName", required = true)
	private String secondName="";
	
	
	@XmlElement(name = "parentName" , required = false)
	private String parentName="";

	public Person(){
		
	}
	
	public Person(String fName, String sName, String pName){
		
		firstName = fName;
		secondName = sName;
		parentName = pName;
		
	}
	
	public void setFirstName(String fName){
		firstName = fName;
	}
	
	public void setSecondName(String sName){
		secondName = sName;
	}
	
	public void setParentName(String pName){
		parentName = pName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getSecondName(){
		return secondName;
	}
	
	public String getParentName(){
		return parentName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 47;
		int result = 1;
		
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((parentName == null) ? 0 : parentName.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		
		return result;
	}

	@Override
	public String toString() {
		return getClass().getName()+"@  [firstName=" + firstName +
									"\n		 secondName=" + secondName + 
									"\n		 parentName=" + parentName + "]";
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		Person other = (Person) obj;
		
		if (firstName == null) {
			if (other.firstName != null){
				return false;
			}
		} else if (!firstName.equals(other.firstName)){
			return false;
			}
		
		if (parentName == null) {
			if (other.parentName != null) {
				return false;
			}
		} else if (!parentName.equals(other.parentName)) {
			return false;
			}
		
		if (secondName == null) {
			if (other.secondName != null) {	
				return false; 
			}
			
		} else if (!secondName.equals(other.secondName)) {	
			return false; 
			}
		
		return true;
	}
}
