package by.epam.atl.task2.dao;

import java.io.File;

import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.dao.exception.DAOException;

public interface NoteBookDao {
	
	NoteBook loadNoteBookFromFile(String fileName) throws DAOException;    //create new notebook from file
	File saveNoteBookIntoFile(NoteBook ntb, String fileName) throws DAOException; // save existing notebook into file
	
}
