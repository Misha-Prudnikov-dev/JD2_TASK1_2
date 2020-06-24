package by.htp.ishop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ishop.bean.Address;
import by.htp.ishop.bean.Basket;
import by.htp.ishop.bean.User;
import by.htp.ishop.controller.command.Command;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class Order implements Command {

	private static final String COUNTRY = "Belarus";
	private static final String CITY = "city";
	private static final String STREET = "street";
	private static final String NUMBER_HOUSE = "numberHouse";
	private static final String PAYMENT = "typePayment";
	private static final String DELIVERY = "by courier";

	private static final String USER = "user";
	private static final String GROUP_PRODUCT_IN_CART = "groupProductCart";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);

		String country = request.getParameter(COUNTRY);
		String city = request.getParameter(CITY);
		String street = request.getParameter(STREET);
		String numberHouse = request.getParameter(NUMBER_HOUSE);
		String typePayment = request.getParameter(PAYMENT);

		Address address = new Address();

		address.setCountry(country);
		address.setCity(city);
		address.setStreet(street);
		address.setNumberHouse(numberHouse);


		User authorizedUser = (User) session.getAttribute(USER);

		Basket basket = (Basket) session.getAttribute(GROUP_PRODUCT_IN_CART);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

	}

}
