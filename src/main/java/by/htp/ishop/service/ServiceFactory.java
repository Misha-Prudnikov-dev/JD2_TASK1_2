package by.htp.ishop.service;

import by.htp.ishop.service.impl.OrderServiceImpl;
import by.htp.ishop.service.impl.ProductServiceImpl;
import by.htp.ishop.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final UserService userService = new UserServiceImpl();

	private final ProductService productService = new ProductServiceImpl();

	private final OrderService OrderService = new OrderServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public OrderService getOrderService() {
		return OrderService;
	}

}
