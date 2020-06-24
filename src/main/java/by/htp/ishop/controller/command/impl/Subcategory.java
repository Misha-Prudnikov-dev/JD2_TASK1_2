package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.controller.command.Command;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.service.ProductService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class Subcategory implements Command {

	private static final String CATEGORY_ID = "categoryId";
	private static final String GROUP_SUBCATEGORY = "groupSubcategory";
	private static final String SUBCATEGORY_PAGE = "WEB-INF/jsp/Subcategory.jsp";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter(CATEGORY_ID));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		try {

			request.setAttribute(GROUP_SUBCATEGORY, productService.getSubcategory(id));

			RequestDispatcher dispatcher = request.getRequestDispatcher(SUBCATEGORY_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}

	}

}
