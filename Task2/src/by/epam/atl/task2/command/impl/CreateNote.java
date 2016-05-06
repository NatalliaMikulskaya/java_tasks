package by.epam.atl.task2.command.impl;

import java.util.Date;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class CreateNote implements Command {

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		Date date = request.getDate();
		String content = request.getContent();
		
		Note nt = noteBookService.createNote(date, content);
				
		Response rsp = new Response();
		
		if (nt != null ){
			rsp.setErrorMessage(null);
			rsp.setMessage("Note was created.");
			rsp.setNote(nt);
		} else{
			rsp.setErrorMessage("Error occured while note was created.");
			rsp.setMessage(null);
		}
		
		return rsp;
	}

}
