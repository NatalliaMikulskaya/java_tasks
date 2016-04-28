package by.epam.atl.task2.main;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import by.epam.atl.task2.bin.Note;
import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.controller.Controller;
import by.epam.atl.task2.view.NoteBookConsoleView;


public class MainApp {

	public static void main(String[] args) {
		String fname = "c:/data/notebook.xml";
		String test_note = "Note for test";
		Date date = null;
		
		
		Controller controller = new Controller();
		
		//create empty notebook
		Request request = new Request();
		request.setCommandName("CREATE_NOTEBOOK_COMMAND");
		
		Response response = controller.doAction(request);
		
		if (response.gerErrorMessage() != null){
			System.err.println(response.gerErrorMessage());
			System.exit(0);
		}
		System.out.println(response.getMessage());
		
		//load data from file
		request = new Request();
		request.setFileName(fname);
		request.setCommandName("LOAD_NOTEBOOK_FROM_FILE_COMMAND");
		response = controller.doAction(request);
		if (response.gerErrorMessage() != null){
			System.err.println(response.gerErrorMessage());
			System.exit(0);
		}
		System.out.println(response.getMessage());
		
		//get result notebook
		NoteBook ntb = response.getNoteBook();
				
		//print notebook
		NoteBookConsoleView ntb_console = new NoteBookConsoleView();
		
		ntb_console.print(ntb);
		
		//create new record 
		String dt = "28/04/2016";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			date = formatter.parse(dt);
		} catch (java.text.ParseException e) {
			
			System.err.println("Can't convert string to Data");
			System.exit(0);
		}
		request = new Request();
		request.setDate(date);
		request.setNoteBook(ntb);
		request.setContent(test_note);
		request.setCommandName("CREATE_NOTE_COMMAND");
		
		response = controller.doAction(request);
		if (response.gerErrorMessage() != null){
			System.err.println(response.gerErrorMessage());
			System.exit(0);
		}
		
		System.out.println(response.getMessage());
		Note nt = response.getNote();
				
		
		//add record to notebook
		request = new Request();
		request.setNote(nt);
		request.setNoteBook(ntb);
		
		request.setCommandName("ADD_NOTE_TO_NOTEBOOK_COMMAND");
		
		response = controller.doAction(request);
		if (response.gerErrorMessage() != null){
			System.err.println(response.gerErrorMessage());
			System.exit(0);
		}
		
		System.out.println(response.getMessage());
		
		ntb_console.print(ntb);
		
	}

}
