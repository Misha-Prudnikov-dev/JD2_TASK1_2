package by.htp.ishop.service.impl;

import by.htp.ishop.bean.User;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.DAOFactory;
import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.service.ServiceException;
import by.htp.ishop.service.UserService;
import by.htp.ishop.service.validation.Validator;

public class UserServiceImpl implements UserService {

	@Override
	public User signIn(String email, String password) throws ServiceException {

		if (!Validator.emailAndPasswordValid(email, password)) {

			throw new ServiceException("Wrong Data");

		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDao = daoFactory.getUserDAO();

		User user = null;

		try {

			user = userDao.signIn(email, password);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public void registration(User user, String password) throws ServiceException {

		if (!Validator.userRegistrationValid(user, password)) {

			throw new ServiceException("Wrong Data");

		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDao = daoFactory.getUserDAO();

		try {

			userDao.registration(user, password);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
