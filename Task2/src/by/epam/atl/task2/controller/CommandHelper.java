package by.epam.atl.task2.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.atl.task2.command.Command;
import by.epam.atl.task2.command.impl.AddNoteToNoteBook;
import by.epam.atl.task2.command.impl.CreateNoteBook;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper(){
		commands.put(CommandName.ADD_NOTE_TO_NOTEBOOK_COMMAND, new AddNoteToNoteBook());
		commands.put(CommandName.ADD_NOTEBOOK_COMMAND, new CreateNoteBook());

	}
	
	public Command getCommand(String commandName){
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	
}
