package by.htp.ishop.dao;

import by.htp.ishop.bean.User;

public interface UserDAO {

	 User signIn(String login, String password) throws DAOException ;

	 void registration(User user,String userPassword)throws DAOException;
	
	 void quickRegistration(String email,String userPassword)throws DAOException;
	
	 void changeUserName(int id,String name)throws DAOException;
	
	 void changeUserSurname(int id,String surname)throws DAOException;
	
	 void changeUserLogin(int id,String login)throws DAOException;
	
	 void changeUserPassword(int id,String password,String newPassword)throws DAOException;
	
	 void changeUserEmail(int id,String email)throws DAOException;
	 
	 void changeUserStatus(int id,String status)throws DAOException;

	
	




}
