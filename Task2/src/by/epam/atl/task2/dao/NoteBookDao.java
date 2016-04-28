package by.epam.atl.task2.dao;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;

public interface NoteBookDao {
	
	NoteBook loadNoteBookFromFile(String fileName);    //create new notebook from file
	File saveNoteBookIntoFile(NoteBook ntb, String fileName); // save existing notebook into file
	
}
