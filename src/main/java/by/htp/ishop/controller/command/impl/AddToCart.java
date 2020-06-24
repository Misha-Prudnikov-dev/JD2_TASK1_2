package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.OrderService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class AddToCart implements Command {

	private static final String PRODUCT_ID = "productId";
	private static final String QUANTITY_PRODUCT = "quantityProduct";

	private static final String USER = "user";
	private static final String ORDER_ID = "id_order";

	private static final String SIGN_IN_PAGE = "Controller?command=go_to_SignIn";

	private static final String LAST_REQUEST = "lastRequest";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);

		int productId = Integer.parseInt(request.getParameter(PRODUCT_ID));
		int quantityProduct = Integer.parseInt(request.getParameter(QUANTITY_PRODUCT));

		if (session.getAttribute(USER) == null) {

			response.sendRedirect(SIGN_IN_PAGE);
			return;
		}

		User authorizedUser = (User) session.getAttribute(USER);

		
			
		
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();

		try {
int orderId = orderService.getOrderId(authorizedUser.getId());
			orderService.addProductToOrder(orderId, productId, quantityProduct);

			if (session.getAttribute(LAST_REQUEST) != null) {

				response.sendRedirect(session.getAttribute(LAST_REQUEST).toString());
			}

		} catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}

	}

}
