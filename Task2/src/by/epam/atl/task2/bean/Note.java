package by.epam.atl.task2.bean;

import java.util.Date;

public class Note {
	private Date date;
	private String note;
	
	public Note(){
		this.date = new Date(); //by default set current date
		this.note = "";
	}
	
	public Note(Date dateNote, String content){
		this.date = dateNote;
		this.note = content.trim();
	}
	
	public void setDate (Date dateNote){
		this.date = dateNote;
	}
	
	public void setNote(String content){
		this.note = content.trim();
	}
	
	public Date getDate(){
		if (this.date != null) {
			return this.date;
		}
		return null;
	}
	
	public String getNote(){
		return this.note;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		
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
		
		Note other = (Note) obj;
		
		if (date == null) {
			if (other.date != null){
				return false;
			}
		} else if (!date.equals(other.date)){
			return false;
		}
		if (note == null) {
			if (other.note != null){
				return false;
			}
		} else if (!note.equals(other.note)){
			return false;
			}
		return true;
	}
	
	@Override
	public String toString(){
		StringBuilder result;
		
		result = new StringBuilder(getClass().getName());
		
		result.append(" @ \n	date: " +((date == null) ? null : date.toString()));
		result.append("\n	note: "+note);
		
		return result.toString();
	}
}

