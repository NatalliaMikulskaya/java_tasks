package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
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
		
		noteBookService.addNoteToNoteBook(nt, ntb);
		
		Response rsp = new Response();
		rsp.setErrorMessage(null);
		rsp.setNoteBook(ntb);
		
		return rsp;
	}

}
