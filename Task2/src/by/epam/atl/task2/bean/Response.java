package by.epam.atl.task2.bean;

import java.util.List;

public class Response {
	private NoteBook noteBook;
	private Note note;
	private List<Note> listNotes;
	private String errorMessage;
	private String message;
	
	public Response(){
		
	}
	
	public String getMessage(){
		return this.message;
	} 
	
	public String gerErrorMessage(){
		return this.errorMessage;
	}
	
	public void setMessage(String inMessage){
		this.message = inMessage;
	}
	
	public void setErrorMessage(String inErrorMessage){
		this.errorMessage = inErrorMessage;
	}
	
	public Note getNote(){
		return this.note;
	}
	
	public void setNote(Note nt){
		this.note = nt;
	}
	
	public NoteBook getNoteBook(){
		return this.noteBook;
	}
	
	public void setNoteBook(NoteBook ntb){
		this.noteBook = ntb;
	}
	
	public void setNotes(List<Note> notes){
		this.listNotes = notes;
	}
	
	public List<Note> getNotes(){
		return this.listNotes;
	}
	
}
