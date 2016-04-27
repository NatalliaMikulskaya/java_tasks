package by.epam.atl.task2.service;

import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;

public interface NoteBookService {

	List<Note> getNoteBook(); //return notebook
	NoteBook createNoteBook();    //create new notebook
	List<Note> findNotesByDate(Date dt);
	List<Note> findNotesByContent(String str);
	void addNoteToNoteBook(Note nt);
}
