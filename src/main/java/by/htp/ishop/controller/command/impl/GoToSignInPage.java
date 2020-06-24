package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.controller.command.Command;

public class GoToSignInPage implements Command {

	private static final String SIGNIN = "WEB-INF/jsp/SignIn.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(SIGNIN);
		dispatcher.forward(request, response);

	}

}
