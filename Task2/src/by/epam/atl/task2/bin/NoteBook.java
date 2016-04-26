package by.epam.atl.task2.bin;

import java.util.ArrayList;
import java.util.List;

public class NoteBook {
	private List<Note> noteBook;
	
	public NoteBook(){
		noteBook = new ArrayList<Note>();
	}
	
	public NoteBook(List<Note> ntb){
		noteBook = ntb;
	}
	
	public void setNoteBook(List<Note> ntb){
		noteBook = ntb;
	}
	
	public List<Note> getNoteBook(){
		return noteBook;
	}
	
	@Override
	public int hashCode(){
		final int prime = 47;
		int result = 1;
		
		result = prime*result + ((noteBook == null) ? 0 : noteBook.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj) {return true; }
		if ( obj == null) {return false;}
		if (getClass() != obj.getClass()) {return false;}
		
		NoteBook ntb = (NoteBook) obj;
		
		if (ntb.getNoteBook().size() == 0) {
			if (noteBook.size() != 0) return false;
		}
		
		if ( !ntb.getNoteBook().equals(noteBook)) { return false;}
		
		return true;
	}
	
	@Override
	public String toString(){
		String result = "";
		
		result = this.getClass().getName()+" @ noteBook: "+ noteBook.toString();
		
		return result;
		
	}
	
}
