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

public class GoToCart implements Command {

	private static final String USER = "user";
	private static final String ORDER_ID = "id_order";

	private static final String SIGN_IN_PAGE = "Controller?command=go_to_SignIn";
	private static final String GROUP_PRODUCT_IN_CART = "groupProductCart";
	private static final String CART_PAGE = "WEB-INF/jsp/Cart.jsp";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute(USER) == null) {

			response.sendRedirect(SIGN_IN_PAGE);
			return;
		}

		User authorizedUser = (User) session.getAttribute(USER);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();

		try {

			int orderId = orderService.getOrderId(authorizedUser.getId());

			session.setAttribute(ORDER_ID, orderId);
			session.setAttribute(GROUP_PRODUCT_IN_CART, orderService.getProductInOrder(orderId));
System.out.println("22");
			RequestDispatcher dispatcher = request.getRequestDispatcher(CART_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			System.out.println("GO TO CART E");
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}
	}

}
