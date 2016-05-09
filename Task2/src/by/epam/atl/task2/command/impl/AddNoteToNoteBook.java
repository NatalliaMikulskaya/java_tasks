package by.epam.atl.task2.command.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;
import by.epam.atl.task2.service.exception.ServiceException;
import java.lang.invoke.MethodHandles;

public class AddNoteToNoteBook implements Command{
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Response execute(Request request) {
		
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		Note nt = request.getNote();
		NoteBook ntb = request.getNoteBook();
		
		int oldSize = request.getSizeNoteBook();
		
		Response rsp = new Response();
		
		try{
			noteBookService.addNoteToNoteBook(nt);
		
			int newSize = ntb.getSizeNoteBook();
		
			if ((oldSize + 1) == newSize ) {
				rsp.setErrorMessage(null);
				rsp.setMessage("Note was added successfully.");
				rsp.setNoteBook(ntb);
			}
		}
		catch (ServiceException e){
			rsp.setErrorMessage("Error occured while trying to add new note.");
			rsp.setMessage(null);
			LOG.error("Exception:"+e+" "+e.getCause());
		}
		
		return rsp;
	}

}
