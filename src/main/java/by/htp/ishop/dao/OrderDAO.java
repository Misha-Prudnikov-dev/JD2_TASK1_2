package by.htp.ishop.dao;

import java.util.List;

import by.htp.ishop.bean.Address;
import by.htp.ishop.bean.Basket;
import by.htp.ishop.bean.Order;
import by.htp.ishop.bean.OrderDetail;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.User;

public interface OrderDAO {
	
	void createOrder(int userId)throws DAOException;
	
	void addProductToOrder(int orderId,int productId,int quantityProduct)throws DAOException;
	
	void blockQuantityProductInStock(int productId,int quantity)throws DAOException;
	
	List<OrderDetail>  getProductInOrder(int orderId)throws DAOException;
	 
	 int getOrderId(int userId)throws DAOException;
	
	void removeProductFromOrder(User user,int productId, int quantityProduct,int orderId)throws DAOException;
	
	void unblockQuantityProductInStock(int productId,int quantity)throws DAOException;

	void addAddressOrder(Address address,int userId)throws DAOException;
	
	void addPaymentOrder(String typePayment,int orderId)throws DAOException;
	
	void addDeliveryOrder(String typeDelivery,int orderId)throws DAOException;
	
	void changeQuantityProduct(int orderDetailId,int productId,int quantityProduct)throws DAOException;
	
	void checkout(int orderId)throws DAOException;
}
