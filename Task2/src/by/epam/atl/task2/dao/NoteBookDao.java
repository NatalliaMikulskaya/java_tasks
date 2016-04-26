package by.epam.atl.task2.dao;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;

public interface NoteBookDao {
	
	NoteBook loadNoteBookFromFile(String file_name);    //create new notebook from file
	File saveNoteBookIntoFile(NoteBook ntb); // save existing notebook into file
	
}
