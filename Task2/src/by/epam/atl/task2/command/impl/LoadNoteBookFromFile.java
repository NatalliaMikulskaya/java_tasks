package by.epam.atl.task2.command.impl;

import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;

public class LoadNoteBookFromFile implements Command{

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		String fileName = request.getFileName();
		
		NoteBook ntb = noteBookService.loadNoteBookFromFile(fileName);
		
		Response rsp = new Response();
		if (ntb.getSizeNoteBook() == 0) {
			rsp.setErrorMessage("Notebook is empty. Check file.");
			rsp.setMessage(null);
		}else{
			rsp.setErrorMessage(null);
			rsp.setMessage("Notebook was loaded.");
		
		}
		rsp.setNoteBook(ntb);
		
		return rsp;
	}

}
