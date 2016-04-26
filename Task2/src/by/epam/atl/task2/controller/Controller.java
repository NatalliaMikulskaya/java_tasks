package by.epam.atl.task2.controller;

import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;
import by.epam.atl.task2.command.Command;

public class Controller {
	private final CommandHelper helper = new CommandHelper();

	public Controller(){}
	
	
	public Response doAction(Request request){
		
		String commandName = request.getCommandName();
		Command command = helper.getCommand(commandName);
		Response response = command.execute(request);
		return response;
		
	}
}
