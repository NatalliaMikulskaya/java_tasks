package by.epam.atl.task2.main;

import java.util.Date;
import org.apache.log4j.Logger;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.controller.Controller;
import by.epam.atl.task2.exceptions.EmptyCommandList;
import by.epam.atl.task2.exceptions.EmptyNote;
import by.epam.atl.task2.exceptions.EmptyNoteBook;
import by.epam.atl.task2.exceptions.EmptyString;
import by.epam.atl.task2.exceptions.InvalidFileName;
import by.epam.atl.task2.exceptions.NullData;
import by.epam.atl.task2.view.NoteBookConsoleView;

public class Executor {
		
	private String[] commandList;
	private String fileNameIn = "";
	private String fileNameOut = "";
	private Date date = null;
	private String content = "";
	private String search = "";
	private Logger log = MainApp.LOG;
	
	private NoteBook noteBook = null;
	private Note note = null;
	
	public void setCommandList(String... commands){
		commandList = commands;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public void setContent(String text){
		content = text;
	}
	
	public void setFileNameIn(String fName){
		fileNameIn = fName;
	}
	
	public void setFileNameOut(String fName){
		fileNameOut = fName;
	}
	
	public void setSearch(String forSearch){
		search = forSearch;
	}
	
	public void execute() throws EmptyCommandList, InvalidFileName, EmptyNoteBook, NullData, EmptyNote, EmptyString{
		Controller controller = new Controller();
		
		
		if (commandList.length == 0){
			throw new EmptyCommandList("Empty list of command!");
			
		}
		
		boolean previousCommandResult = true;
		int i=0;
		
		//process in case previous command execute successful and command array is not ended
		while (previousCommandResult && (i < commandList.length )) {
			
			if (commandList[i].length() == 0) {
				continue; //skip if command is missed
			}
			
			previousCommandResult = execCommand(controller, commandList[i]);
			
			i++;
		}

	}

	/*
	 * Execute particular command
	 */
	private boolean execCommand(Controller controller, String commandName) throws InvalidFileName, EmptyNoteBook, NullData, EmptyNote, EmptyString{
		Request request;
		Response response;
		boolean commandExecuteResult = false;
		NoteBookConsoleView ntb_console;
		
		switch(commandName){
			case("CREATE_NOTEBOOK_COMMAND"):{

				//create empty notebook
				request = new Request();
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);
				
				noteBook = response.getNoteBook();
				return commandExecuteResult;
			}
			
			case ("LOAD_NOTEBOOK_FROM_FILE_COMMAND"):{
				
				if (fileNameIn.length() == 0){
					throw new InvalidFileName("File for loading notebook is not determined.");
				}
				
				//load data from file
				request = new Request();
				request.setFileName(fileNameIn);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);
				
				//get result notebook
				noteBook = response.getNoteBook();
						
				/*//print notebook
				
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(noteBook);*/
				
				return commandExecuteResult;
			}
			
			case ("CREATE_NOTE_COMMAND"):{
				if (date == null){
					throw new NullData("Data is equal null! Can't create note whith empty date.");
				}
				
				//create new record 
				request = new Request();
				request.setDate(date);
				request.setNoteBook(noteBook);
				request.setContent(content);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				//get result note
				note = response.getNote();
				
				return commandExecuteResult;
				
			}
			
			case("ADD_NOTE_TO_NOTEBOOK_COMMAND"):{
				if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}

				if ( note == null ){
					throw new EmptyNote ("Notebook does not exist");
				}
				
				//add record to notebook
				request = new Request();
				request.setNote(note);
				request.setNoteBook(noteBook);
				
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				/*//print result note
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(noteBook);*/
				
				return commandExecuteResult;
				
			}
			
			case("FIND_NOTES_BY_DATE"):{
				
				if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}
				//find records into notebook
				request = new Request();

				request.setNoteBook(noteBook);
				request.setDate(date);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				/*List<Note> foundedNotes = response.getNotes();
				
				//print founded notes
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(foundedNotes);*/
				
				//always return true because result is not important for further processing
				return true;
			}
			
			case("FIND_NOTES_BY_CONTENT"):{
				if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}
				//find records into notebook
				
				if (search.length() == 0) {
					throw new EmptyString ("String for searching is empty.");
				}
				
				request = new Request();
				
				request.setNoteBook(noteBook);
				request.setContent(search);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				/*List<Note> foundedNotes = response.getNotes();
				
				//print founded notes
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(foundedNotes);*/
				
				//always return true because result is not important for further processing
				return true;
			}
			
			case("UNLOAD_NOTEBOOK_INTO_FILE_COMMAND"):{
				if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}
				if (fileNameOut.length() == 0){
					throw new InvalidFileName("File for output note book is not determined.");
				}
				
				//unload data into file
				request = new Request();
				request.setFileName(fileNameOut);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);

				return commandExecuteResult;
			}
			
			default: {return false;}
		}
	}
	
	private boolean processResult(Response resp){
		if (resp.gerErrorMessage() != null){
			log.info(resp.gerErrorMessage());
			return false;
		}
		log.info(resp.getMessage());
		
		return true;
	}
}
