package by.epam.atl.task2.command;

import by.epam.atl.task2.bin.Request;
import by.epam.atl.task2.bin.Response;

public interface Command {
	Response execute(Request request);
}
