package by.htp.ishop.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.htp.ishop.bean.Category;
import by.htp.ishop.service.ProductService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class StoreContextListener implements ServletContextListener {

	private static final String GROUP_CATEGORY = "groupCategory";

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ServletContext servletContext = sce.getServletContext();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		ProductService productService = serviceFactory.getProductService();

		try {

			servletContext.setAttribute(GROUP_CATEGORY, productService.getCategory());
		} catch (ServiceException e) {

		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
