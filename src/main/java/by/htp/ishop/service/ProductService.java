package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.bean.Category;
import by.htp.ishop.bean.Product;
import by.htp.ishop.bean.Subcategory;
import by.htp.ishop.dao.DAOException;

public interface ProductService {

	 List<Product> findProductBySubcategory(int id) throws ServiceException;

	 List<Subcategory> getSubcategory(int id) throws ServiceException;
	 
	 Product getProductById(int id) throws ServiceException;
	 
	 List<Category> getCategory() throws ServiceException;

	 void addProductToFavorites(int userId,int productId) throws ServiceException;

	 void removeProductToFavorites(int userId,int productId) throws ServiceException;

	 List<Product> getProductFavorites(int userId)throws ServiceException;


}
