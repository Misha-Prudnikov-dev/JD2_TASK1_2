package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;
import by.htp.ishop.service.UserService;

public class Registration implements Command {
	
	private static final String USER_LOGIN = "login";
	private static final String USER_PASSWORD = "password";
	private static final String USER_NAME = "name";
	private static final String USER_SURNAME = "surname";
	private static final String USER_PHONE = "phone";
	private static final String USER_EMAIL = "email";
	private static final String USER_GENDER = "gender";
	
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";
	private static final String GO_TO_MAIN = "Controller?command=go_to_main";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String login = request.getParameter(USER_LOGIN);
		String name = request.getParameter(USER_NAME);
		String surname = request.getParameter(USER_SURNAME);
		String phone = request.getParameter(USER_PHONE);
		String gender = request.getParameter(USER_GENDER);
		String email = request.getParameter(USER_EMAIL);
		String password = request.getParameter(USER_PASSWORD);

		User user = new User();

		user.setName(name);
		user.setSurname(surname);
		user.setLogin(login);
		user.setEmail(email);
		user.setPhone(phone);
		user.setGender(gender);


		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {

			userService.registration(user, password);

			response.sendRedirect(GO_TO_MAIN);

		}  catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}
		
 
	}

}
