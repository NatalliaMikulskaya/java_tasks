package by.epam.atl.task2.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.dao.DAOFactory;
import by.epam.atl.task2.dao.NoteBookDao;
import by.epam.atl.task2.service.NoteBookService;

public class NoteBookServiceImpl implements NoteBookService {

	@Override
	public List<Note> getListNotes() {
		return NoteBookProvider.getInstance().getListNotes();
	}

	@Override
	public NoteBook createNoteBook() {
		
		//create empty list iof notes
		List<Note> newNoteList = new ArrayList<Note>();
		
		//set list of notes into notebook
		NoteBookProvider.getInstance().setNoteBook(newNoteList);
		
		return NoteBookProvider.getInstance();
		
	}

	@Override
	public List<Note> findNotesByDate(Date data) {
		List<Note> resultNotes = new ArrayList<Note>();
		
		//get notes from notebook
		List<Note> notebook = getListNotes();
		
		//iterate notes
		for(Note note: notebook){
			if (note.getDate().equals(data)){
				resultNotes.add(note);
				
			}
		}
		
		return resultNotes;
	}

	@Override
	public List<Note> findNotesByContent(String searchString) {
		List<Note> resultNotes = new ArrayList<Note>();
		
		//get notes from notebook
		List<Note> notebook = getListNotes();
		
		//iterate notes
		for(Note note: notebook){
			if (note.getNote().contains(searchString)){
				resultNotes.add(note);
				
			}
		}
		
		return resultNotes;
	}

	@Override
	public void addNoteToNoteBook(Note nt) {
		
		//set list of notes into notebook
		NoteBookProvider.getInstance().addNote(nt); 
				
	}

	@Override
	public NoteBook loadNoteBookFromFile(String fileName) {
		
		NoteBookDao ntbDao = DAOFactory.getInstance().getNoteBookDao();

		NoteBook ntb = ntbDao.loadNoteBookFromFile(fileName);
		
		return ntb;
	}

	@Override
	public void unloadBookIntoFile(String fileName) {
		
		NoteBookDao ntbDao = DAOFactory.getInstance().getNoteBookDao();
		
		NoteBook ntb = NoteBookProvider.getInstance();
		
		ntbDao.saveNoteBookIntoFile(ntb, fileName);
		
	}

	@Override
	public Note createNote(Date date, String noteContent) {
		//create empty note
		Note newNote = new Note(date, noteContent);
				
		return newNote;
	}
	
	
}
