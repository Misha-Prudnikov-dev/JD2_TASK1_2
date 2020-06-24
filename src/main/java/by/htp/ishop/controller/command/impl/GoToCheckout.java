package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.controller.command.Command;

public class GoToCheckout implements Command {

	private static final String CHECKOUT_PAGE = "WEB-INF/jsp/Checkout.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(CHECKOUT_PAGE);
		dispatcher.forward(request, response);

	}

}
