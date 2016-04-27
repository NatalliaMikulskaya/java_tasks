package by.epam.atl.task2.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.command.impl.AddNoteToNoteBook;
import by.epam.atl.task2.command.impl.CreateNoteBook;
import by.epam.atl.task2.command.impl.FindNotesByContent;
import by.epam.atl.task2.command.impl.FindNotesByDate;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper(){
		commands.put(CommandName.ADD_NOTE_TO_NOTEBOOK_COMMAND, new AddNoteToNoteBook());
		commands.put(CommandName.ADD_NOTEBOOK_COMMAND, new CreateNoteBook());
		commands.put(CommandName.FIND_NOTES_BY_DATE, new FindNotesByDate());
		commands.put(CommandName.FIND_NOTES_BY_CONTENT, new FindNotesByContent());
	}
	
	public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	
}
