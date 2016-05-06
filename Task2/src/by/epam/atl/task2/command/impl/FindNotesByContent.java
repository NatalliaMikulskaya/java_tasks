package by.epam.atl.task2.command.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.task2.bean.Note;

import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.service.NoteBookService;
import by.epam.atl.task2.service.ServiceFactory;
import by.epam.atl.task2.service.exception.ServiceException;

public class FindNotesByContent implements Command {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Response execute(Request request) {
		ServiceFactory factory = ServiceFactory.getInstance();
		NoteBookService noteBookService = factory.getNoteBookService();
		
		String searchString = request.getContent();
		
		Response rsp = new Response();
		try{
			List<Note> notes = noteBookService.findNotesByContent(searchString);
		
			if (notes.size() != 0 ){
				rsp.setErrorMessage(null);
				rsp.setMessage("Searching is done. "+notes.size()+" noted were founded.");
				rsp.setNotes(notes);
			}
		}
		catch(ServiceException e){
			rsp.setErrorMessage("Nothing was founded.");
			rsp.setMessage(null);
			LOG.error("Exception: "+e+" "+e.getCause());
			
		}
		
		return rsp;
	}

}
