package by.htp.ishop.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.Address;
import by.htp.ishop.bean.OrderDetail;
import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.OrderService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class Checkout implements Command {

	private static final String COUNTRY = "Belarus";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String NUMBER_HOUSE = "numberHouse";
	private static final String PAYMENT = "typePayment";

	private static final String USER = "user";
	private static final String GROUP_PRODUCT_IN_CART = "groupProductCart";
	private static final String ORDER_ID = "id_order";
	private static final String GO_TO_ERROR_PAGE = "Controller?command=go_to_error_page";
	private static final String GO_TO_MAIN_PAGE = "WEB-INF/jsp/main_page.jsp";


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession(true);

		int productId = 0;
		int quantityProduct = 0;
		String country = request.getParameter(COUNTRY);
		String city = request.getParameter(CITY);
		String street = request.getParameter(STREET);
		String numberHouse = request.getParameter(NUMBER_HOUSE);
		String typePayment = request.getParameter(PAYMENT);
		String typeDelivery = "by courier";

		Address address = new Address();

		address.setCountry(country);
		address.setCity(city);
		address.setStreet(street);
		address.setNumberHouse(numberHouse);


		User authorizedUser = (User) session.getAttribute(USER);

		int orderId = (int) session.getAttribute(ORDER_ID);

		List<OrderDetail> groupOrderDetail = (List<OrderDetail>) session.getAttribute(GROUP_PRODUCT_IN_CART);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		OrderService orderService = serviceFactory.getOrderService();

		try {

			orderService.addAddressOrder(address, authorizedUser.getId());
			orderService.addPaymentOrder(typePayment, orderId);
			orderService.addDeliveryOrder(typeDelivery, orderId);

			for (OrderDetail orderDetail : groupOrderDetail) {

				orderService.changeQuantityProduct(orderDetail.getId(), productId, quantityProduct);
			}
			orderService.checkout(orderId);

			RequestDispatcher dispatcher = request.getRequestDispatcher(GO_TO_MAIN_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(GO_TO_ERROR_PAGE);

		}

	}

}
