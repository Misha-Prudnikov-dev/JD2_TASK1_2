package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.bean.Address;
import by.htp.ishop.bean.Basket;
import by.htp.ishop.bean.OrderDetail;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.DAOException;

public interface OrderService {

	void createOrder(int userId) throws ServiceException;

	void addProductToOrder(int orderId, int productId, int quantityProduct) throws ServiceException;

	List<OrderDetail> getProductInOrder(int orderId) throws ServiceException;

	void removeProductFromOrder(User user, int productId, int quantityProduct, int orderId) throws ServiceException;

	int getOrderId(int userId) throws ServiceException;

	void addAddressOrder(Address address, int userId) throws ServiceException;

	void addPaymentOrder(String typePayment, int orderId) throws ServiceException;

	void addDeliveryOrder(String typeDelivery, int orderId) throws ServiceException;

	void changeQuantityProduct(int orderDetailId, int productId, int quantityProduct) throws ServiceException;

	void checkout(int orderId) throws ServiceException;
}
