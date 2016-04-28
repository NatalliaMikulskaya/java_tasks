package by.epam.atl.task2.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.dao.DAOFactory;
import by.epam.atl.task2.dao.impl.NoteBookDaoImpl;
import by.epam.atl.task2.service.NoteBookService;

public class NoteBookServiceImpl implements NoteBookService {

	@Override
	public List<Note> getNoteBook() {
		return NoteBookProvider.getInstance().getNoteBook();
	}

	@Override
	public NoteBook createNoteBook() {
		
		//create empty list iof notes
		List<Note> new_notebook = new ArrayList<Note>();
		
		//set list of notes into notebook
		NoteBookProvider.getInstance().setNoteBook(new_notebook);
		
		return NoteBookProvider.getInstance();
		
	}

	@Override
	public List<Note> findNotesByDate(Date dt) {
		List<Note> result_notes = new ArrayList<Note>();
		
		//get notes from notebook
		List<Note> notebook = getNoteBook();
		
		//iterate notes
		for(Note note: notebook){
			if (note.getDate().equals(dt)){
				result_notes.add(note);
				
			}
		}
		
		return result_notes;
	}

	@Override
	public List<Note> findNotesByContent(String str) {
		List<Note> result_notes = new ArrayList<Note>();
		
		//get notes from notebook
		List<Note> notebook = getNoteBook();
		
		//iterate notes
		for(Note note: notebook){
			if (note.getNote().contains(str)){
				result_notes.add(note);
				
			}
		}
		
		return result_notes;
	}

	@Override
	public void addNoteToNoteBook(Note nt) {
		
		//get list of notes from notebook
		List<Note> notebook = getNoteBook();
		
		//add new note
		notebook.add(nt);
		
		//set list of notes into notebook
		NoteBookProvider.getInstance().setNoteBook(notebook);
				
	}

	@Override
	public NoteBook loadNoteBookFromFile(String file_name) {
		
		NoteBookDaoImpl ntb_dao = new NoteBookDaoImpl();
		
		NoteBook ntb = ntb_dao.loadNoteBookFromFile(file_name);
		
		return ntb;
	}

	@Override
	public void unloadBookIntoFile(String file_name) {
		NoteBookDaoImpl ntb_dao = new NoteBookDaoImpl();
		NoteBook ntb = NoteBookProvider.getInstance();
		
		ntb_dao.saveNoteBookIntoFile(ntb, file_name);
		
	}

	@Override
	public Note createNote(Date dt, String str) {
		//create empty note
		Note new_note = new Note(dt, str);
				
		return new_note;
	}
	
	
}
