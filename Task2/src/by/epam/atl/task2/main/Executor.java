package by.epam.atl.task2.main;

import java.lang.invoke.MethodHandles;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.controller.Controller;
import by.epam.atl.task2.view.NoteBookConsoleView;

public class Executor {
		
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	private String[] commandList;
	private String fileNameIn = "";
	private String fileNameOut = "";
	private Date date = null;
	private String content = "";
	private String search = "";
	
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
	
	public void execute() {
		Controller controller = new Controller();
		
		
		if (commandList.length == 0){
			LOG.error("Empty list of command! Nothig to execute.");
			//throw new EmptyCommandList("Empty list of command!");
			return;
			
		}
		
		boolean previousCommandResult = true;
		int i=0;
		
		//process in case previous command execute successful and command array is not ended
		while (previousCommandResult && (i < commandList.length )) {
			
			if (commandList[i].length() == 0) {
				LOG.trace("Empty command in position "+(i+1)+"! It was skipped.");
				continue; //skip if command is missed
			}
			
			previousCommandResult = execCommand(controller, commandList[i]);
			
			i++;
		}

	}

	/*
	 * Execute particular command
	 */
	private boolean execCommand(Controller controller, String commandName) {
		Request request;
		Response response;
		boolean commandExecuteResult = false;
		NoteBookConsoleView ntb_console;
		
		switch(commandName){
			case("CREATE_NOTEBOOK_COMMAND"):{

				LOG.info("Start command "+commandName);
				//create empty notebook
				request = new Request();
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);
				
				noteBook = response.getNoteBook();
				LOG.info("End command "+commandName);
				return commandExecuteResult;
			}
			
			case ("LOAD_NOTEBOOK_FROM_FILE_COMMAND"):{
				LOG.info("Start command "+commandName);
				/*if (fileNameIn.length() == 0){
					throw new InvalidFileName("File for loading notebook is not determined.");
				}*/
				
				//load data from file
				request = new Request();
				request.setFileName(fileNameIn);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);
				
				if (commandExecuteResult){
					//get result notebook
					noteBook = response.getNoteBook();
						
					//print notebook
				
					ntb_console = new NoteBookConsoleView();
					ntb_console.print(noteBook);
				}
				LOG.info("End command "+commandName);
				return commandExecuteResult;
			}
			
			case ("CREATE_NOTE_COMMAND"):{
				LOG.info("Start command "+commandName);
				/*if (date == null){
					throw new NullData("Data is equal null! Can't create note whith empty date.");
				}*/
				
				//create new record 
				request = new Request();
				request.setDate(date);
				//request.setNoteBook(noteBook);
				request.setContent(content);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				//get result note
				note = response.getNote();
				LOG.info("End command "+commandName);
				return commandExecuteResult;
				
			}
			
			case("ADD_NOTE_TO_NOTEBOOK_COMMAND"):{
				LOG.info("Start command "+commandName);
				/*if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}

				if ( note == null ){
					throw new EmptyNote ("Notebook does not exist");
				}*/
				
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
				LOG.info("End command "+commandName);
				return commandExecuteResult;
				
			}
			
			case("FIND_NOTES_BY_DATE"):{
				LOG.info("Start command "+commandName);
				/*if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}*/
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
				LOG.info("End command "+commandName);
				return true;
			}
			
			case("FIND_NOTES_BY_CONTENT"):{
				LOG.info("Start command "+commandName);
				/*if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}*/
				//find records into notebook
				
				/*if (search.length() == 0) {
					throw new EmptyString ("String for searching is empty.");
				}*/
				
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
				
				LOG.info("End command "+commandName);
				//always return true because result is not important for further processing
				return true;
			}
			
			case("UNLOAD_NOTEBOOK_INTO_FILE_COMMAND"):{
				LOG.info("Start command "+commandName);
				/*if (noteBook == null) {
					throw new EmptyNoteBook ("Notebook does not exist");
				}
				if (fileNameOut.length() == 0){
					throw new InvalidFileName("File for output note book is not determined.");
				}*/
				
				//unload data into file
				request = new Request();
				request.setFileName(fileNameOut);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);
				LOG.info("End command "+commandName);
				return commandExecuteResult;
			}
			
			default: {return false;}
		}
	}
	
	private boolean processResult(Response resp){
		if (resp.gerErrorMessage() != null){
			LOG.error(resp.gerErrorMessage());
			return false;
		}
		LOG.info(resp.getMessage());
		
		return true;
	}
}
