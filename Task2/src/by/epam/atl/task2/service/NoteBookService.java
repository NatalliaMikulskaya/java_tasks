package by.epam.atl.task2.service;

import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.service.exception.ServiceException;

public interface NoteBookService {

	List<Note> getListNotes(); //return notebook notes
	NoteBook createNoteBook();    //create new notebook
	NoteBook loadNoteBookFromFile(String file_name) throws ServiceException;   //load notebook from file
	void unloadBookIntoFile(String file_name) throws ServiceException; //load notebook into file
	List<Note> findNotesByDate(Date dt) throws ServiceException;
	List<Note> findNotesByContent(String str) throws ServiceException;
	Note createNote(Date dt, String str);    //create new note
	void addNoteToNoteBook(Note nt) throws ServiceException;
}
