package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.OrderService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;
import by.htp.ishop.service.UserService;

public class SignIn implements Command {

	private static final String USER_EMAIL = "email";
	private static final String USER_PASSWORD = "password";
	private static final String GO_TO_MAIN_PAGE = "WEB-INF/jsp/main_page.jsp";
	private static final String USER = "user";
	private static final String ORDER_ID = "id_order";
	private static final String GO_TO_SIGN_IN_PAGE = "Controller?command=Go_To_SignIn";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(true);

		String email = request.getParameter(USER_EMAIL);
		String password = request.getParameter(USER_PASSWORD);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {

			User authorizedUser = userService.signIn(email, password);

			if (authorizedUser != null) {

 
				session.setAttribute(USER, authorizedUser);
 
 
				RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_MAIN_PAGE);
				dispatcher.forward(request, response);

			} else {
				response.sendRedirect(GO_TO_SIGN_IN_PAGE);
			}
		} catch (ServiceException e) {
 			response.sendRedirect(GO_TO_SIGN_IN_PAGE);
		}

	}

}
