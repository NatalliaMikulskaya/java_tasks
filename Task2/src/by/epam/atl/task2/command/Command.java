package by.epam.atl.task2.command;

import by.epam.atl.task2.bean.Request;
import by.epam.atl.task2.bean.Response;

public interface Command {
	Response execute(Request request);
}
