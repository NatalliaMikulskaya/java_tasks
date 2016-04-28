package by.epam.atl.task2.main;

import by.epam.atl.task2.bin.NoteBook;
import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.controller.Controller;
import by.epam.atl.task2.view.NoteBookConsoleView;


public class MainApp {

	public static void main(String[] args) {
		
		String fname = "c:/data/notebook.xml";
		
		Controller controller = new Controller();
		
		//create empty notebook
		Request request = new Request();
		request.setCommandName("CREATE_NOTEBOOK_COMMAND");
		
		Response response = controller.doAction(request);
		
		if (response.gerErrorMessage() != null){
			System.err.println("Error ocured while create notebook");
			System.exit(0);
		}
		System.out.println(response.getMessage());
		
		//load data from file
		request = new Request();
		request.setFileName(fname);
		request.setCommandName("LOAD_NOTEBOOK_FROM_FILE_COMMAND");
		response = controller.doAction(request);
		if (response.gerErrorMessage() != null){
			System.err.println("Error ocured while load notebook");
			System.exit(0);
		}
		System.out.println(response.getMessage());
		
		//get result notebook
		NoteBook ntb = response.getNoteBook();
		System.out.println(ntb.getNoteBook().size());
		
		//print notebook
		NoteBookConsoleView ntb_console = new NoteBookConsoleView();
		
		ntb_console.print(ntb);

	}

}
