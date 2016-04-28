package by.epam.atl.task2.service;

import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;

public interface NoteBookService {

	List<Note> getListNotes(); //return notebook notes
	NoteBook createNoteBook();    //create new notebook
	NoteBook loadNoteBookFromFile(String file_name);   //load notebook from file
	void unloadBookIntoFile(String file_name); //load notebook into file
	List<Note> findNotesByDate(Date dt);
	List<Note> findNotesByContent(String str);
	Note createNote(Date dt, String str);    //create new note
	void addNoteToNoteBook(Note nt);
}
