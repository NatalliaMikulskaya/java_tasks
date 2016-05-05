package by.epam.atl.task2.command.impl;

import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class FindNotesByDate implements Command {

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		Date date = request.getDate();
		
		List<Note> notes = noteBookService.findNotesByDate(date);
		
		Response rsp = new Response();
		if (notes.size() != 0) {
			rsp.setErrorMessage(null);
			rsp.setMessage("Searching is done."+notes.size()+" noted were founded.");
		}else {
			rsp.setErrorMessage("Nothing was founded.");
			rsp.setMessage(null);
		}
		
		rsp.setNotes(notes);
		
		return rsp;
	}

}
