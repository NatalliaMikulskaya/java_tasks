package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class LoadNoteBookFromFile implements Command{

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		String file_name = request.getFileName();
		
		NoteBook ntb = noteBookService.loadNoteBookFromFile(file_name);
		
		Response rsp = new Response();
		if (ntb.getNoteBook().size() == 0) {
			rsp.setErrorMessage("Notebook is empty. Check file.");
			rsp.setMessage(null);
		}else{
			rsp.setErrorMessage(null);
			rsp.setMessage("Notebook was loaded.");
		
			rsp.setNoteBook(ntb);
		}
		
		return rsp;
	}

}
