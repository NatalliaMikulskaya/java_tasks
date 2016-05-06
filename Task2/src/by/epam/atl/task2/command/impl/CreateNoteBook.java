package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
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
		if (ntb != null) {
			rsp.setErrorMessage(null);
			rsp.setMessage("Notebook was created.");
			rsp.setNoteBook(ntb);
			
		} else {
			rsp.setErrorMessage("Error occured while notebook was created");
			rsp.setMessage(null);
			 
		}
		
		return rsp;
	}

}
