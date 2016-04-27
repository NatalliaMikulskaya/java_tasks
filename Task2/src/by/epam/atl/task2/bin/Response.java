package by.epam.atl.task2.bin;

import java.util.List;

public class Response {
	private NoteBook noteBook;
	private Note note;
	private List<Note> notes;
	private String errorMessage;
	private String message;
	
	public Response(){
		
	}
	
	public String getMessage(){
		return message;
	} 
	
	public String gerErrorMessage(){
		return errorMessage;
	}
	
	public void setMessage(String msg){
		this.message = msg;
	}
	
	public void setErrorMessage(String err_msg){
		this.errorMessage = err_msg;
	}
	
	public Note getNote(){
		return note;
	}
	
	public void setNote(Note nt){
		this.note = nt;
	}
	
	public NoteBook getNoteBook(){
		return noteBook;
	}
	
	public void setNoteBook(NoteBook ntb){
		this.noteBook = ntb;
	}
	
	public void setNotes(List<Note> nts){
		this.notes = nts;
	}
	
	public List<Note> getNotes(){
		return this.notes;
	}
	
}
