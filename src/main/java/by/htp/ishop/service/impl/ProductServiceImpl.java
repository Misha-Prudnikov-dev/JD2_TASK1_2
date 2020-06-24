package by.htp.ishop.service.impl;

import java.util.List;

import by.htp.ishop.bean.Category;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.Subcategory;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.DAOFactory;
import by.htp.ishop.dao.ProductDAO;
import by.htp.ishop.service.ProductService;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.ServiceFactory;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findProductBySubcategory(int id) throws ServiceException {

		List<Product> getGroupProduct;

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		try {
			getGroupProduct = productDAO.findProductBySubcategory(id);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}

		return getGroupProduct;
	}

	@Override
	public List<Subcategory> getSubcategory(int id) throws ServiceException {

		List<Subcategory> getGroupSubcategory;

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		try {
			getGroupSubcategory = productDAO.getSubcategory(id);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}

		return getGroupSubcategory;
	}

	@Override
	public Product getProductById(int id) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productdao = daoFactory.getProductDAO();

		Product product;

		try {
			product = productdao.getProductById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return product;
	}

	@Override
	public List<Category> getCategory() throws ServiceException {

		List<Category> getGroupCategory;

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		try {
			getGroupCategory = productDAO.getCategory();

		} catch (DAOException e) {

			throw new ServiceException(e);

		}

		return getGroupCategory;
	}

	@Override
	public void addProductToFavorites(int userId, int productId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		try {
			productDAO.addProductToFavorites(userId, productId);

		} catch (DAOException e) {
			throw new ServiceException(e);

		}
	}

	@Override
	public void removeProductToFavorites(int userId, int productId) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		try {
			productDAO.removeProductToFavorites(userId, productId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Product> getProductFavorites(int userId) throws ServiceException {

		List<Product> getGroupProductFavorites;

		DAOFactory daoFactory = DAOFactory.getInstance();
		ProductDAO productDAO = daoFactory.getProductDAO();

		try {

			getGroupProductFavorites = productDAO.getProductFavorites(userId);

		} catch (DAOException e) {
			throw new ServiceException(e);

		}

		return getGroupProductFavorites;
	}

}
