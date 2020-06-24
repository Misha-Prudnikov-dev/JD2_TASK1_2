package by.htp.ishop.service.impl;

import java.util.List;

import by.htp.ishop.bean.Address;
import by.htp.ishop.bean.Basket;
import by.htp.ishop.bean.OrderDetail;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.OrderDAO;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.DAOFactory;
import by.htp.ishop.service.OrderService;
import by.htp.ishop.service.ServiceException;

public class OrderServiceImpl implements OrderService {

	@Override
	public void createOrder(int userId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {

			orderDAO.createOrder(userId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void addProductToOrder(int orderId, int productId, int quantityProduct) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {

			orderDAO.addProductToOrder(orderId, productId, quantityProduct);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<OrderDetail> getProductInOrder(int orderId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		List<OrderDetail> groupOrderDetail = null;

		try {

			groupOrderDetail = orderDAO.getProductInOrder(orderId);

			return groupOrderDetail;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void removeProductFromOrder(User user, int productId, int quantityProduct, int orderId)
			throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {
			orderDAO.removeProductFromOrder(user, productId, quantityProduct, orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

	}

	@Override
	public int getOrderId(int userId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		int orderId = 0;

		try {
			orderId = orderDAO.getOrderId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

		return orderId;
	}

	@Override
	public void addAddressOrder(Address address, int userId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {
			orderDAO.addAddressOrder(address, userId);
			;
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

	}

	@Override
	public void addPaymentOrder(String typePayment, int orderId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {
			orderDAO.addPaymentOrder(typePayment, orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

	}

	@Override
	public void addDeliveryOrder(String typeDelivery, int orderId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {
			orderDAO.addDeliveryOrder(typeDelivery, orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

	}

	@Override
	public void changeQuantityProduct(int orderDetailId, int productId, int quantityProduct) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {
			orderDAO.changeQuantityProduct(orderDetailId, productId, quantityProduct);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

	}

	@Override
	public void checkout(int orderId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoFactory.getOrderDAO();

		try {
			orderDAO.checkout(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);

		}

	}

}
