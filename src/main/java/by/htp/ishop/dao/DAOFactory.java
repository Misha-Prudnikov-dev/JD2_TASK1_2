package by.htp.ishop.dao;

import by.htp.ishop.dao.OrderDAO;
import by.htp.ishop.dao.ProductDAO;
import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.dao.impl.OrderDAOImpl;
import by.htp.ishop.dao.impl.ProductDAOImpl;
import by.htp.ishop.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	public static DAOFactory getInstance() {
		return instance;
	}

	private DAOFactory() {
	}

	private UserDAO userDAO = new UserDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	private OrderDAO orderDAO = new OrderDAOImpl();

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

}
