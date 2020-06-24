package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.ProductService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class GroupProductBySubcategory implements Command {

	private static final String SUBCATEGORY_ID = "subcategory";
	private static final String GROUP_PRODUCT_PAGE = "WEB-INF/jsp/GroupProduct.jsp";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";
	private static final String GROUP_PRODUCT = "groupProduct";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int subcategoryId = Integer.parseInt(request.getParameter(SUBCATEGORY_ID));

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ProductService productService = serviceFactory.getProductService();

			request.setAttribute(GROUP_PRODUCT, productService.findProductBySubcategory(subcategoryId));

			RequestDispatcher dispatcher = request.getRequestDispatcher(GROUP_PRODUCT_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}
	}

}
