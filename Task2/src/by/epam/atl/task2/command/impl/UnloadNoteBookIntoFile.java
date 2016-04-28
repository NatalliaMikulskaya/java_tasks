package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class UnloadNoteBookIntoFile implements Command {

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		String fileName = request.getFileName();
		
		noteBookService.unloadBookIntoFile(fileName);
		
		Response rsp = new Response();
		rsp.setErrorMessage(null);
		rsp.setMessage("Notebook was unloaded.");
				
		return rsp;
	}

}
