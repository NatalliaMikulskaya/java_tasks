package by.epam.atl.task2.command.impl;

import java.lang.invoke.MethodHandles;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class CreateNote implements Command {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

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
