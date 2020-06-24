package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.controller.command.Command;

public class SignOut implements Command {

	private static final String SESSION_USER = "user";
	private static final String GO_TO_MAIN = "Controller?command=go_to_main";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		session.removeAttribute(SESSION_USER);
		response.sendRedirect(GO_TO_MAIN);
	}

}
