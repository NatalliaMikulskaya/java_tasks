package by.epam.atl.task2.command.impl;

import java.util.Date;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class CreateNote implements Command {

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		Date dt = request.getDate();
		String str = request.getContent();
		
		Note nt = noteBookService.createNote(dt, str);
				
		Response rsp = new Response();
		rsp.setErrorMessage(null);
		rsp.setMessage("Note was created.");
		rsp.setNote(nt);
		
		return rsp;
	}

}
