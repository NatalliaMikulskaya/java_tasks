package by.epam.atl.task2.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.command.impl.AddNoteToNoteBook;
import by.epam.atl.task2.command.impl.CreateNoteBook;
import by.epam.atl.task2.command.impl.FindNotesByContent;
import by.epam.atl.task2.command.impl.FindNotesByDate;
import by.epam.atl.task2.command.impl.LoadNoteBookFromFile;
import by.epam.atl.task2.command.impl.UnloadNoteBookIntoFile;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper(){
		commands.put(CommandName.ADD_NOTE_TO_NOTEBOOK_COMMAND, new AddNoteToNoteBook());
		commands.put(CommandName.CREATE_NOTEBOOK_COMMAND, new CreateNoteBook());
		commands.put(CommandName.LOAD_NOTEBOOK_FROM_FILE_COMMAND, new LoadNoteBookFromFile());
		commands.put(CommandName.UNLOAD_NOTEBOOK_INTO_FILE_COMMAND, new UnloadNoteBookIntoFile());
		commands.put(CommandName.FIND_NOTES_BY_DATE, new FindNotesByDate());
		commands.put(CommandName.FIND_NOTES_BY_CONTENT, new FindNotesByContent());
	}
	
	public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	
}
