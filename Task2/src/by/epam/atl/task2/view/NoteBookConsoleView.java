package by.epam.atl.task2.view;

import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;

public class NoteBookConsoleView {
	
	//print whole notebook
	public void print(NoteBook ntb){
		
		List<Note> notes = ntb.getListNotes();
		
		for (Note note: notes){
			System.out.println(note.toString());
		}
	}
	
	//print one note
	public void print (Note note){
		System.out.println(note.toString());
	}
	
	//print array of notes
	public void print(Note...notes){
		for (Note note: notes){
			System.out.println(note.toString());
		}
	}
	
	//print list of notes
	public void print (List<Note> notes){
		for (Note note: notes){
			System.out.println(note.toString());
		}
	}
}
