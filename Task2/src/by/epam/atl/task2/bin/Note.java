package by.epam.atl.task2.bin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
	private Date date;
	private String note;
	
	public Note(){
		date = new Date(); //by default set current date
		note = "";
	}
	
	public Note(Date dt, String str){
		date = dt;
		note = str.trim();
	}
	
	public void setDate (Date dt){
		
		date = dt;
		
	}
	
	public void setNote(String nt){
		note = nt;
	}
	
	public Date getDate(){
		if (date != null) return date;
		return null;
	}
	
	public String getNote(){
		return note;
	}

	@Override
	public int hashCode() {
		final int prime = 47;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String ret_str = "";
		
		 SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		
		ret_str += "date: "+date_format.format(date.getTime())+ "\n note: "+note;
		
		return ret_str;
	}
}

