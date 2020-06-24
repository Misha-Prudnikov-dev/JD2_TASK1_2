package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ishop.bean.Address;
import by.htp.ishop.bean.Basket;
import by.htp.ishop.bean.Order;
import by.htp.ishop.bean.OrderDetail;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.ProductImage;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.OrderDAO;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.DAOFactory;
import by.htp.ishop.dao.ProductDAO;
import by.htp.ishop.dao.connection.ConnectionPool;
import by.htp.ishop.dao.connection.ConnectionPoolRuntimeException;

public class OrderDAOImpl implements OrderDAO {

	private static final String SQL_TABLE_ORDER_DETAIL_ID = "id_order_details";

	private static final String SQL_TABLE_ORDER_ID = "id_order";
	private static final String SQL_TABLE_COLUMN_STATUS = "status_order";

	private static final String SQL_TABLE_ORDER_DETAIL_PRODUCT_QUANTITY = "quantity_order_details";
	private static final String SQL_TABLE_ORDER_DETAIL_PRODUCTS_ID = "products_id_products";

	private static final String SQL_TABLE_PRODUCT_TITLE = "title_products";
	private static final String SQL_TABLE_PRODUCT_ID = "id_products";
	private static final String SQL_TABLE_PRODUCT_DESCRIPTION = "description_products";
	private static final String SQL_TABLE_PRODUCT_YEAR = "year_products";
	private static final String SQL_TABLE_PRODUCT_CODE_PRODUCT = "code_product_products";
	private static final String SQL_TABLE_PRODUCT_PRICE_UNIT = "price_products";
	private static final String SQL_TABLE_PRODUCT_QUANTITY = "quantity_products";

	private static final String SQL_TABLE_PRODUCT_IMAGE = "image_product_image";

	private static final String STATUS_ORDER_CART = "CART";
	private static final String STATUS_ORDER_ORDER = "ORDER";
	private static final String STATUS_ORDER_BOUGHT = "BOUGHT";

	private static final String SELECT_ORDER_DETAILS_PRODUCT = "SELECT * FROM mydb.order_details JOIN mydb.products "
			+ "ON mydb.order_details.products_id_products=mydb.products.id_products JOIN mydb.product_image ON mydb.product_image.products_id_products=mydb.products.id_products WHERE mydb.order_details.order_id_order = ?";
	
	private static final String SELECT_ORDER_ID = "SELECT * FROM order WHERE users_id_users = ? ";
	private static final String SELECT_PRODUCT_IN_ORDER = " SELECT * FROM mydb.order WHERE mydb.order.users_id_users = ? AND mydb.order.status_order=?";

	private static final String DELETE_PRODUCT_ID_IN_ORDER_DETAIL = "DELETE  FROM order_details WHERE products_id_products = ? AND order_id_order = ?";

	private static final String UPDATE_ORDER = "UPDATE mydb.order SET mydb.order.status_order = ? WHERE mydb.order.id_order = ?";


	private static final String INSERT_PRODUCT_ID_AND_QUANTITY_PRODUCT_AND_ORDER_ID = "INSERT INTO "
			+ "order_details(products_id_products,order_id_order,quantity_order_details) VALUES(?,?,?) ";

	private static final String INSERT_USER_ID_AND_STATUS = "INSERT INTO order(users_id_users,status_order) VALUES(?,?)";
	private static final String INSERT_ADDRESS_USER = "INSERT INTO addresses(country_addresses,city_addresses,street_addresses,numberHouse_addresses,users_id_users) VALUES(?,?,?,?,?)";
	private static final String INSERT_DELIVERY_ORDER = "INSERT INTO  delivery(type_delivery,order_id_order) VALUES(?,?)";
	private static final String INSERT_PAYMENT_ORDER = "INSERT INTO  payment(type_payment,order_id_order) VALUES(?,?)";

	private static final String UPDATE_QUANTITY_PRODUCT = "UPDATE products JOIN order_details "
			+ "ON mydb.products.id_products=mydb.order_details.products_id_products "
			+ "SET mydb.products.quantity_products = (mydb.products.quantity_products-mydb.order_details.quantity_products) "
			+ "WHERE id_order_details = ?";

	@Override
	public void createOrder(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_USER_ID_AND_STATUS);


			ps.setInt(1, userId);
			ps.setString(2, STATUS_ORDER_CART);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

	}

	@Override
	public void addProductToOrder(int orderId, int productId, int quantityProduct) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_PRODUCT_ID_AND_QUANTITY_PRODUCT_AND_ORDER_ID);

			ps.setInt(1, productId);
			ps.setInt(2, orderId);
			ps.setInt(3, quantityProduct);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

	}

	@Override
	public void removeProductFromOrder(User user, int productId, int quantityProduct, int orderId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(DELETE_PRODUCT_ID_IN_ORDER_DETAIL);

			ps.setInt(1, productId);
			ps.setInt(2, orderId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public List<OrderDetail> getProductInOrder(int orderId) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		List<OrderDetail> groupOrderDetail = new ArrayList<OrderDetail>();

		try {

			OrderDetail orderDetail = null;
			Product product = null;
			ProductImage productImage = null;

			con = connectionPool.takeConnection();

			ps = con.prepareStatement(SELECT_ORDER_DETAILS_PRODUCT);

			ps.setInt(1, orderId);

			rs = ps.executeQuery();

			if (rs == null) {

				return null;
			}

			while (rs.next()) {

				orderDetail = new OrderDetail();
				product = new Product();
				productImage = new ProductImage();

				orderDetail.setId(rs.getInt(SQL_TABLE_ORDER_DETAIL_ID));
				orderDetail.setQuantity(rs.getInt(SQL_TABLE_ORDER_DETAIL_PRODUCT_QUANTITY));

				product.setId(rs.getInt(SQL_TABLE_PRODUCT_ID));
				product.setCode(rs.getString(SQL_TABLE_PRODUCT_CODE_PRODUCT));
				product.setDescription(rs.getString(SQL_TABLE_PRODUCT_DESCRIPTION));
				product.setTitle(rs.getString(SQL_TABLE_PRODUCT_TITLE));
				product.setPrice(rs.getDouble(SQL_TABLE_PRODUCT_PRICE_UNIT));
				product.setQuantity(rs.getInt(SQL_TABLE_PRODUCT_QUANTITY));
				product.setYear(rs.getInt(SQL_TABLE_PRODUCT_YEAR));

				productImage.setImage(rs.getString(SQL_TABLE_PRODUCT_IMAGE));


				product.setProductImage(productImage);
				orderDetail.setProduct(product);

				groupOrderDetail.add(orderDetail);

			}

			return groupOrderDetail;

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public int getOrderId(int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		int orderId = 0;

		try {

			con = connectionPool.takeConnection();

			ps = con.prepareStatement(SELECT_PRODUCT_IN_ORDER);
			ps.setInt(1, userId);
			ps.setString(2, STATUS_ORDER_CART);

			rs = ps.executeQuery();

			if (rs == null) {
				return 0;
			}

			while (rs.next()) {
				orderId = rs.getInt(SQL_TABLE_ORDER_ID);
			}

			return orderId;

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void blockQuantityProductInStock(int productId, int quantity) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unblockQuantityProductInStock(int productId, int quantity) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAddressOrder(Address address, int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_ADDRESS_USER);

			ps.setString(1, address.getCountry());
			ps.setString(2, address.getCity());
			ps.setString(3, address.getStreet());
			ps.setString(4, address.getNumberHouse());
			ps.setInt(5, userId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void addPaymentOrder(String typePayment, int orderId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_PAYMENT_ORDER);

			ps.setString(1, typePayment);
			ps.setInt(2, orderId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void addDeliveryOrder(String typeDelivery, int orderId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_DELIVERY_ORDER);

			ps.setString(1, typeDelivery);
			ps.setInt(2, orderId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void changeQuantityProduct(int orderDetailId, int productId, int quantityProduct) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(UPDATE_QUANTITY_PRODUCT);

			ps.setInt(1, orderDetailId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

	@Override
	public void checkout(int orderId) throws DAOException {


		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_PAYMENT_ORDER);

			ps.setString(1, STATUS_ORDER_ORDER);
			ps.setInt(2, orderId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);

		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}

}
