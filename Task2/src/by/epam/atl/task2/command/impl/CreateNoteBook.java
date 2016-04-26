package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class CreateNoteBook implements Command
{

	@Override
	public Response execute(Request request) {

		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		NoteBook ntb = noteBookService.createNoteBook();
		
		Response rsp = new Response();
		rsp.setErrorMessage(null);
		rsp.setMessage("Notebook was created.");
		rsp.setNoteBook(ntb);
		
		return rsp;
	}

}
