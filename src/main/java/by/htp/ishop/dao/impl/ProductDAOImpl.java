package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.htp.ishop.bean.Category;
import by.htp.ishop.bean.Manufacturer;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.ProductDetail;
import by.htp.ishop.bean.ProductImage;
import by.htp.ishop.bean.Stock;
import by.htp.ishop.bean.Subcategory;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.ProductDAO;
import by.htp.ishop.dao.connection.ConnectionPool;
import by.htp.ishop.dao.connection.ConnectionPoolRuntimeException;

public class ProductDAOImpl implements ProductDAO {

	private static final String SQL_TABLE_CATEGORY_TITLE = "title_categories";
	private static final String SQL_TABLE_CATEGORY_ID = "id_categories";

	private static final String SQL_TABLE_SUBCATEGORY_TITLE = "title_subcategories";
	private static final String SQL_TABLE_SUBCATEGORY_ID = "id_subcategories";

	private static final String SQL_TABLE_PRODUCT_TITLE = "title_products";
	private static final String SQL_TABLE_PRODUCT_ID = "id_products";
	private static final String SQL_TABLE_PRODUCT_DESCRIPTION = "description_products";
	private static final String SQL_TABLE_PRODUCT_YEAR = "year_products";
	private static final String SQL_TABLE_PRODUCT_CODE_PRODUCT = "code_product_products";
	private static final String SQL_TABLE_PRODUCT_PRICE_UNIT = "price_products";
	private static final String SQL_TABLE_PRODUCT_QUANTITY = "quantity_products";
	private static final String SQL_TABLE_PRODUCT_PRODUCT_DETAILS_ID = "product_details_id_product_details";
	private static final String SQL_TABLE_PRODUCT_MANUFACTURER_ID = "manufacturer_id_manufacturer";

	private static final String SQL_TABLE_MANUFACTURER_COUNTRY = "country_manufacturer";
	private static final String SQL_TABLE_MANUFACTURER_TITLE = "title_manufacturer";
	private static final String SQL_TABLE_MANUFACTURER_ID = "id_manufacturer";
	private static final String SQL_TABLE_MANUFACTURER_DESCRIPTION = "description_manufacturer";

	private static final String SQL_TABLE_PRODUCT_DETAIL_ID = "id_product_details";
	private static final String SQL_TABLE_PRODUCT_DETAILS_COLOR = "color_product_details";
	private static final String SQL_TABLE_PRODUCT_DETAIL_SIZE = "size_product_details";
	private static final String SQL_TABLE_PRODUCT_DETAIL_WEIGHT = "weight_product_details";

	private static final String SQL_TABLE_PRODUCT_IMAGE_ID = "id_product_image";
	private static final String SQL_TABLE_PRODUCT_IMAGE = "image_product_image";
	private static final String SQL_TABLE_PRODUCT_IMAGEL_PRODUCT_ID = "products_id_products";

	private static final String SELECT_TITLE = "SELECT * FROM products WHERE title = ?";
	private static final String SELECT_SUBCATEGORY = "SELECT * FROM products,product_image WHERE subcategories_id_subcategories = ? "
			+ "AND products_id_products=id_products";
	private static final String SELECT_MANUFACTURER = "SELECT * FROM manufacturer WHERE  id = ?";
	private static final String SELECT_PRODUCT_DETAIL = "SELECT * FROM product_details WHERE  id = ?";
	private static final String SELECT_ALL_SUBCATEGORY = "SELECT * FROM subcategories WHERE categories_id_categories = ?";
	private static final String SELECT_ALL_CATEGORY = "SELECT * FROM categories";
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id_products = ?";
	private static final String INSERT_PRODUCT_IMAGE = "INSERT INTO product_image(image,products_id) VALUE(?,?)";

	private static final String SELECT_PRODUCT_DATA = "SELECT * "
			+ "FROM products,product_image,manufacturer,product_details "
			+ " WHERE mydb.products.id_products=? AND mydb.product_image.products_id_products=id_products "
			+ "AND mydb.manufacturer.id_manufacturer=mydb.products.manufacturer_id_manufacturer "
			+ "AND mydb.products.product_details_id_product_details=mydb.product_details.id_product_details";

	private static final String INSERT_PRODUCT_ID_USER_ID = "INSERT INTO favorites(users_id_users,products_id_products) VALUE(?,?)";
	private static final String SELECT_FAVORITES_USER_ID = "SELECT * FROM mydb.favorites JOIN mydb.products "
			+ "ON mydb.favorites.products_id_products=mydb.products.id_products JOIN mydb.product_image "
			+ "ON mydb.product_image.products_id_products=mydb.products.id_products  WHERE  users_id_users = ?";

	private static final String DELETE_PRODUCT_IN_FAVORITES = "DELETE  FROM favorites WHERE products_id_products = ? AND users_id_users = ?";

	@Override
	public boolean changeProductTitle(Product product, String title) {

		return false;
	}

	@Override
	public boolean changeProductPrice(Double price) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeProductDescription(String description) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> findByTitleProduct(String title) throws DAOException {
		return null;
	}

	@Override
	public List<Product> findByParametrProduct(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Product> findProductBySubcategory(int id) throws DAOException {

		List<Product> groupProductSubcategory = new ArrayList<Product>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ConnectionPool cp = null;

		try {

			Product product = null;

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();
			ps = con.prepareStatement(SELECT_SUBCATEGORY);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();
				ProductImage productImage = new ProductImage();

				product.setTitle(rs.getString(SQL_TABLE_PRODUCT_TITLE));
				product.setId(rs.getInt(SQL_TABLE_PRODUCT_ID));
				product.setDescription(rs.getString(SQL_TABLE_PRODUCT_DESCRIPTION));
				product.setYear(rs.getInt(SQL_TABLE_PRODUCT_YEAR));
				product.setCode(rs.getString(SQL_TABLE_PRODUCT_CODE_PRODUCT));
				product.setPrice(rs.getDouble(SQL_TABLE_PRODUCT_PRICE_UNIT));
				product.setQuantity(rs.getInt(SQL_TABLE_PRODUCT_QUANTITY));

				productImage.setId(rs.getInt(SQL_TABLE_PRODUCT_IMAGE_ID));
				productImage.setImage(rs.getString(SQL_TABLE_PRODUCT_IMAGE));

				product.setProductImage(productImage);

				groupProductSubcategory.add(product);

			}
		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DAOException(e);

		} finally {
			cp.closeConnection(con, ps, rs);
		}

		return groupProductSubcategory;
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addImageProduct(Product product) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool cp = null;

		try {

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();

			ps = con.prepareStatement(INSERT_PRODUCT_IMAGE);

			ps.setString(1, product.getProductImage().getImage());
			ps.setInt(2, product.getId());

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {

			cp.closeConnection(con, ps, rs);
		}

	}

	@Override
	public List<Subcategory> getSubcategory(int id) throws DAOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ConnectionPool cp = null;

		Subcategory subcategory = null;

		List<Subcategory> groupSubcategory = new ArrayList<Subcategory>();

		try {

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();

			ps = con.prepareStatement(SELECT_ALL_SUBCATEGORY);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				subcategory = new Subcategory();

				subcategory.setTitle(rs.getString(SQL_TABLE_SUBCATEGORY_TITLE));
				subcategory.setId(rs.getInt(SQL_TABLE_SUBCATEGORY_ID));

				groupSubcategory.add(subcategory);

			}

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {

			cp.closeConnection(con, ps, rs);
		}

		return groupSubcategory;
	}

	@Override
	public Product getProductById(int id) throws DAOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ConnectionPool cp = null;

		Product product = new Product();

		try {

			Manufacturer manufacturer = new Manufacturer();
			ProductDetail productDetail = new ProductDetail();
			ProductImage productImage = new ProductImage();

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();

			ps = con.prepareStatement(SELECT_PRODUCT_DATA);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				product.setTitle(rs.getString(SQL_TABLE_PRODUCT_TITLE));
				product.setId(rs.getInt(SQL_TABLE_PRODUCT_ID));
				product.setDescription(rs.getString(SQL_TABLE_PRODUCT_DESCRIPTION));
				product.setYear(rs.getInt(SQL_TABLE_PRODUCT_YEAR));
				product.setCode(rs.getString(SQL_TABLE_PRODUCT_CODE_PRODUCT));
				product.setPrice(rs.getDouble(SQL_TABLE_PRODUCT_PRICE_UNIT));
				product.setQuantity(rs.getInt(SQL_TABLE_PRODUCT_QUANTITY));

				manufacturer.setCountry(rs.getString(SQL_TABLE_MANUFACTURER_COUNTRY));
				manufacturer.setDescription(rs.getString(SQL_TABLE_MANUFACTURER_DESCRIPTION));
				manufacturer.setId(rs.getInt(SQL_TABLE_MANUFACTURER_ID));
				manufacturer.setTitle(rs.getString(SQL_TABLE_MANUFACTURER_TITLE));

				productDetail.setId(rs.getInt(SQL_TABLE_PRODUCT_DETAIL_ID));
				productDetail.setColor(rs.getString(SQL_TABLE_PRODUCT_DETAILS_COLOR));
				productDetail.setSize(rs.getString(SQL_TABLE_PRODUCT_DETAIL_SIZE));
				productDetail.setWeight(rs.getString(SQL_TABLE_PRODUCT_DETAIL_WEIGHT));

				productImage.setId(rs.getInt(SQL_TABLE_PRODUCT_IMAGE_ID));
				productImage.setImage(rs.getString(SQL_TABLE_PRODUCT_IMAGE));


				product.setManufacturer(manufacturer);
				product.setProductDetail(productDetail);
				product.setProductImage(productImage);

			}

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {

			cp.closeConnection(con, ps, rs);
		}

		return product;
	}

	@Override
	public List<Category> getCategory() throws DAOException {
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ConnectionPool cp = null;

		Category category = null;

		List<Category> groupCategory = new ArrayList<Category>();

		try {

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();

			ps = con.prepareStatement(SELECT_ALL_CATEGORY);

			rs = ps.executeQuery();

			while (rs.next()) {

				category = new Category();

				category.setTitle(rs.getString(SQL_TABLE_CATEGORY_TITLE));
				category.setId(rs.getInt(SQL_TABLE_CATEGORY_ID));

				groupCategory.add(category);

			}

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {

			cp.closeConnection(con, ps, rs);
		}

		return groupCategory;
	}

	@Override
	public void addProductToFavorites(int userId, int productId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool cp = null;

		try {

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();

			ps = con.prepareStatement(INSERT_PRODUCT_ID_USER_ID);

			ps.setInt(1, userId);
			ps.setInt(2, productId);

			ps.executeUpdate();

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {

			cp.closeConnection(con, ps, rs);
		}

	}

	@Override
	public List<Product> getProductFavorites(int userId) throws DAOException {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ConnectionPool cp = null;

		Product product = null;

		List<Product> groupProductFavorites = new ArrayList<Product>();

		try {

			Manufacturer manufacturer = new Manufacturer();
			ProductDetail productDetail = new ProductDetail();
			ProductImage productImage = null;

			cp = ConnectionPool.getInstance();
			con = cp.takeConnection();

			ps = con.prepareStatement(SELECT_FAVORITES_USER_ID);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();
				productImage = new ProductImage();

				product.setTitle(rs.getString(SQL_TABLE_PRODUCT_TITLE));
				product.setId(rs.getInt(SQL_TABLE_PRODUCT_ID));
				product.setDescription(rs.getString(SQL_TABLE_PRODUCT_DESCRIPTION));
				product.setYear(rs.getInt(SQL_TABLE_PRODUCT_YEAR));
				product.setCode(rs.getString(SQL_TABLE_PRODUCT_CODE_PRODUCT));
				product.setPrice(rs.getDouble(SQL_TABLE_PRODUCT_PRICE_UNIT));
				product.setQuantity(rs.getInt(SQL_TABLE_PRODUCT_QUANTITY));


				 productImage.setId(rs.getInt(SQL_TABLE_PRODUCT_IMAGE_ID));
				 productImage.setImage(rs.getString(SQL_TABLE_PRODUCT_IMAGE));

				
				groupProductFavorites.add(product);

			}

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {

			cp.closeConnection(con, ps, rs);
		}

		return groupProductFavorites;

	}

	@Override
	public void removeProductToFavorites(int userId, int productId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(DELETE_PRODUCT_IN_FAVORITES);

			ps.setInt(1, productId);
			ps.setInt(2, userId);

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
