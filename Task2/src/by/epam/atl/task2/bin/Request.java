package by.epam.atl.task2.bin;

import java.util.Date;

public class Request {
	private Date date;
	private String content;
	private String commandName;
	private NoteBook notebook;
	private Note note;
	
	public Request(){
		date = new Date();
	}
	
	public Request(Date dt, String str, NoteBook ntb){
		date = dt;
		content = str;
		this.notebook = ntb;
	}
	
	public void setDate(Date dt){
		this.date = dt;
	}
	
	public void setContent(String str){
		this.content = str;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setNoteBook(NoteBook ntb){
		this.notebook = ntb;
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
	
	public String getCommandName() {
		return this.commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	
	
}
