package by.epam.atl.task2.command.impl;

import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class FindNotesByContent implements Command {

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		NoteBook ntb = request.getNoteBook();
		String str = request.getContent();
		
		List<Note> notes = noteBookService.findNotesByContent(str);
		
		Response rsp = new Response();
		rsp.setErrorMessage(null);
		rsp.setMessage("Searching is done.");
		rsp.setNotes(notes);
		
		return rsp;
	}

}
