package by.epam.atl.task2.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import by.epam.atl.task2.exception.EmptyCommandList;
import by.epam.atl.task2.exception.EmptyNote;
import by.epam.atl.task2.exception.EmptyNoteBook;
import by.epam.atl.task2.exception.EmptyString;
import by.epam.atl.task2.exception.InvalidFileName;
import by.epam.atl.task2.exception.NullData;

public class MainApp {
	public static final Logger LOG = Logger.getRootLogger();

	public static void main(String[] args) {
		String fNameIn = "c:/data/notebook.xml";
		//String fNameIn = "";
		String fNameOut = "c:/data/notebook_result.xml";
		String testNote = "Note for test";
		String dateString = "28/04/2016";
		String stringForSearching = "note";
		Date date = null;
		
		String[] commandList = {"CREATE_NOTEBOOK_COMMAND",
								"LOAD_NOTEBOOK_FROM_FILE_COMMAND",
								"CREATE_NOTE_COMMAND",
								"ADD_NOTE_TO_NOTEBOOK_COMMAND",
								"FIND_NOTES_BY_DATE",
								"FIND_NOTES_BY_CONTENT",
								"UNLOAD_NOTEBOOK_INTO_FILE_COMMAND"};
		
		Executor exec = new Executor();
		exec.setCommandList(commandList);
		exec.setFileNameIn(fNameIn);
		exec.setFileNameOut(fNameOut);
		exec.setContent(testNote);
		exec.setSearch(stringForSearching);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			date = formatter.parse(dateString);
		} catch (java.text.ParseException e) {
			
			LOG.error("Exception:"+e+" Can't convert string to Data");
			return;
		}
		
		exec.setDate(date);
		
		exec.execute();

		
	}

}
