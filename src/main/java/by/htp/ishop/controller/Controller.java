package by.htp.ishop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LAST_REQUEST = "lastRequest";
	private static final String COMMAND = "command";

	private static final CommandProvider commandProvider = new CommandProvider();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName;
		Command command;

		commandName = request.getParameter(COMMAND);
		command = commandProvider.getCommand(commandName);
		command.execute(request, response);

		request.getSession(true).setAttribute(LAST_REQUEST, request.getRequestURI() + "?" + request.getQueryString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName;
		Command executionCommand;

		commandName = request.getParameter(COMMAND);
		executionCommand = commandProvider.getCommand(commandName);
		executionCommand.execute(request, response);

	}

}
