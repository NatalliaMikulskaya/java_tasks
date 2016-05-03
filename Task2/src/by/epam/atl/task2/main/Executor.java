package by.epam.atl.task2.main;

import java.util.Date;
import java.util.List;

import by.epam.atl.task2.bean.Note;
import by.epam.atl.task2.bean.NoteBook;
import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;
import by.epam.atl.task2.controller.Controller;
import by.epam.atl.task2.view.NoteBookConsoleView;

public class Executor {
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
	
	public void execute(){
		Controller controller = new Controller();
		
		
		if (commandList.length == 0){
			return;
		}
		
		boolean previousCommandResult = true;
		int i=0;
		
		//process in case previous command execute successful and command array is not ended
		while (previousCommandResult && (i < commandList.length )) {
			
			if (commandList[i].length() == 0) {
				return; //stop processing if command is missed
			}
			
			previousCommandResult = execCommand(controller, commandList[i]);
			
			i++;
		}

	}

	/*
	 * Execute particular command
	 */
	private boolean execCommand(Controller controller, String commandName){
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
					return false;
				}
				
				//load data from file
				request = new Request();
				request.setFileName(fileNameIn);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				
				commandExecuteResult = processResult(response);
				
				//get result notebook
				noteBook = response.getNoteBook();
						
				//print notebook
				
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(noteBook);
				
				return commandExecuteResult;
			}
			
			case ("CREATE_NOTE_COMMAND"):{
				if (date == null){
					return false;
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
					System.err.println("notebook does not exist");
					return false;
				}

				if ( note == null ){
					return false;
				}
				
				//add record to notebook
				request = new Request();
				request.setNote(note);
				request.setNoteBook(noteBook);
				
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				//print result note
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(noteBook);
				
				return commandExecuteResult;
				
			}
			
			case("FIND_NOTES_BY_DATE"):{
				
				if (noteBook == null) {
					return false;
				}
				//find records into notebook
				request = new Request();

				request.setNoteBook(noteBook);
				request.setDate(date);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				List<Note> foundedNotes = response.getNotes();
				
				//print founded notes
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(foundedNotes);
				
				//always return true because result is not important for further processing
				return true;
			}
			
			case("FIND_NOTES_BY_CONTENT"):{
				if (noteBook == null) {
					return false;
				}
				//find records into notebook
				
				if (search.length() == 0) {
					return true; //always return true because result is not important for further processing
				}
				
				request = new Request();
				
				request.setNoteBook(noteBook);
				request.setContent(search);
				request.setCommandName(commandName);
				
				response = controller.doAction(request);
				commandExecuteResult = processResult(response);
				
				List<Note> foundedNotes = response.getNotes();
				
				//print founded notes
				ntb_console = new NoteBookConsoleView();
				ntb_console.print(foundedNotes);
				
				//always return true because result is not important for further processing
				return true;
			}
			
			case("UNLOAD_NOTEBOOK_INTO_FILE_COMMAND"):{
				if (noteBook == null) {
					return false;
				}
				if (fileNameOut.length() == 0){
					return false;
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
			System.err.println(resp.gerErrorMessage());
			return false;
		}
		System.out.println(resp.getMessage());
		
		return true;
	}
}
