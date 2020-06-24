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

public class ProductInfo implements Command {

	private static final String PRODUCT_ID = "productId";
	private static final String PRODUCT_INFO = "productInfo";
	private static final String PRODUCT_INFO_PAGE = "WEB-INF/jsp/ProductInfo.jsp";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ProductService productService = serviceFactory.getProductService();

			//productService.getProductById(productId);

			request.setAttribute(PRODUCT_INFO, productService.getProductById(productId));

			RequestDispatcher dispatcher = request.getRequestDispatcher(PRODUCT_INFO_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}
	}

}
