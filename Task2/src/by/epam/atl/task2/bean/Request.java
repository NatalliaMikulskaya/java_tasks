package by.epam.atl.task2.bean;

import java.util.Date;

public class Request {
	private Date date;
	private String content;
	private String commandName;
	private NoteBook notebook;
	private Note note;
	private String fileName;
	private int sizeNoteBook;
	
	public Request(){
		date = new Date();
	}
	
	public Request(Date dateNote, String stringNote, NoteBook ntb){
		this.date = dateNote;
		this.content = stringNote;
		this.notebook = ntb;
		this.sizeNoteBook = ntb.getSizeNoteBook();
	}
	
	public void setDate(Date dateNote){
		this.date = dateNote;
	}
	
	public void setContent(String stringNote){
		this.content = stringNote;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setNoteBook(NoteBook ntb){
		this.notebook = ntb;
		this.sizeNoteBook = ntb.getSizeNoteBook();
	}
	
	public NoteBook getNoteBook(){
		return this.notebook;
	}
	
	public void setNote(Note nt){
		this.note = nt;
	}
	
	public Note getNote(){
		return this.note;
	}
	
	public int getSizeNoteBook(){
		return this.sizeNoteBook;
	}
	
	public String getCommandName() {
		return this.commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	public void setFileName(String fName){
		this.fileName = fName;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
}
