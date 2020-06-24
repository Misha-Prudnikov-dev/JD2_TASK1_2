package by.htp.ishop.service;

import by.htp.ishop.bean.User;

public interface UserService {

	User signIn(String email,String password)throws ServiceException;
	
	void registration(User user,String password)throws ServiceException;
}
