package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.ProductService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class Favorites implements Command {

	private static final String FAVORITES_PAGE = "WEB-INF/jsp/Favorites.jsp";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";
	private static final String GROUP_PRODUCT_FAVORITES = "groupProductFavorites";
	private static final String USER = "user";
	private static final String SIGN_IN_PAGE = "Controller?command=go_to_SignIn";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute(USER) == null) {

			response.sendRedirect(SIGN_IN_PAGE);
			return;
		}

		User authorizedUser = (User) session.getAttribute(USER);

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ProductService productService = serviceFactory.getProductService();

			request.setAttribute(GROUP_PRODUCT_FAVORITES, productService.getProductFavorites(authorizedUser.getId()));

			RequestDispatcher dispatcher = request.getRequestDispatcher(FAVORITES_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}
	}

}
