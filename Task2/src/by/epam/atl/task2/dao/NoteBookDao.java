package by.epam.atl.task2.dao;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.dao.exception.DAOException;

public interface NoteBookDao {
	
	NoteBook loadNoteBookFromFile(String fileName) throws DAOException;    //create new notebook from file
	File saveNoteBookIntoFile(NoteBook ntb, String fileName) throws DAOException; // save existing notebook into file
	
}
