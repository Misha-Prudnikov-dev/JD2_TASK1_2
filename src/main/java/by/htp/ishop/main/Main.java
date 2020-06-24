package by.htp.ishop.main;

import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.DAOFactory;
import by.htp.ishop.dao.ProductDAO;
import by.htp.ishop.dao.UserDAO;

public class Main {

	public static void main(String[] args) {

		DAOFactory daoFactory = DAOFactory.getInstance();
		
		UserDAO userDao= daoFactory.getUserDAO();
		
		ProductDAO product = daoFactory.getProductDAO();
		
		try {
			product.getProductById(2);
			//product.findProductBySubcategory(1);
			System.out.println("3");
		} catch (DAOException e1) {
			 
			System.out.println("Not Found Product");

		}

		try {
			userDao.signIn("sashagur@list.ru", "*123456789*");
			System.out.println("4");
		} catch (DAOException e) {
			System.out.println("Not Found");
		}
	}
}
