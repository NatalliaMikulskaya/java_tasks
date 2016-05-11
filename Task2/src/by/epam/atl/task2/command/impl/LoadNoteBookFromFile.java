package by.epam.atl.task2.command.impl;

import java.lang.invoke.MethodHandles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;
import by.epam.atl.task2.service.exception.ServiceException;

public class LoadNoteBookFromFile implements Command{
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		String fileName = request.getFileName();
		Response rsp = new Response();
		
		try{
			NoteBook ntb = noteBookService.loadNoteBookFromFile(fileName);
		
			if (ntb.getSizeNoteBook() > 0) {
				rsp.setErrorMessage(null);
				rsp.setMessage("Notebook was loaded.");
				rsp.setNoteBook(ntb);
			}else{
				LOG.error("Notebook is empty. Check file.");
			}
		}
		catch (ServiceException e){
			rsp.setErrorMessage("Notebook is empty. Check file.");
			rsp.setMessage(null);
			
			LOG.error("Exception: "+e+" "+e.getCause());
		}
		
		return rsp;
	}

}
