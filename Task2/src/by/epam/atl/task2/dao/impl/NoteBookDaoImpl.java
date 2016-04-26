package by.epam.atl.task2.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.dao.NoteBookDao;

public class NoteBookDaoImpl implements NoteBookDao {


	@Override
	public NoteBook loadNoteBookFromFile(String file_name) {
		NoteBook nt = new NoteBook();
		
		return nt;
	}

	@Override
	public File saveNoteBookIntoFile(NoteBook ntb) {
		// TODO Auto-generated method stub
		return null;
	}

}
