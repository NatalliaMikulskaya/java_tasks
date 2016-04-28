package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class AddNoteToNoteBook implements Command{

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		Note nt = request.getNote();
		NoteBook ntb = request.getNoteBook();
		int oldSize = request.getSizeNoteBook();
		
		noteBookService.addNoteToNoteBook(nt);
		
		int newSize = ntb.getSizeNoteBook();
		
		Response rsp = new Response();
		
		if ((oldSize + 1) == newSize ) {
			rsp.setErrorMessage(null);
			rsp.setMessage("Note was added successfully.");
		} else {
			rsp.setErrorMessage("Error occured while new note was added.");
			rsp.setMessage(null);
		}
		
		rsp.setNoteBook(ntb);
		
		return rsp;
	}

}
