package by.epam.atl.task2.bean;

import java.util.ArrayList;
import java.util.List;

public class NoteBook {
	private List<Note> listNotes;
	
	public NoteBook(){
		this.listNotes = new ArrayList<Note>();
	}
	
	public NoteBook(List<Note> listNotes){
		this.listNotes = listNotes;
	}
	
	public void setNoteBook(List<Note> listNotes){
		this.listNotes = listNotes;
	}
	
	public List<Note> getListNotes(){
		return this.listNotes;
	}
	
	public void addNote(Note newNote){
		this.listNotes.add(newNote);
	}
	
	public int getSizeNoteBook(){
		return this.listNotes.size();
	}
	
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		
		result = prime*result + ((listNotes == null) ? 0 : listNotes.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		if (this == obj) {
			return true; 
		}
		if ( obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		NoteBook ntb = (NoteBook) obj;
		
		if (ntb.getListNotes().size() == 0) {
			if (listNotes.size() != 0) {
				return false;
			}
		}
		
		if ( !ntb.getListNotes().equals(listNotes)) { return false;}
		
		return true;
	}
	
	@Override
	public String toString(){
		String result = "";
		
		result = getClass().getName()+" @ noteBook: "+ listNotes.toString();
		
		return result;
		
	}
	
}
