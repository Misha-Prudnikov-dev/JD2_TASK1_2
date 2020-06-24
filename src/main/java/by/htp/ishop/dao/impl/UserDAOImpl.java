package by.htp.ishop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ishop.bean.Role;
import by.htp.ishop.bean.User;
import by.htp.ishop.dao.DAOException;
import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.dao.connection.ConnectionPool;
import by.htp.ishop.dao.connection.ConnectionPoolRuntimeException;

public class UserDAOImpl implements UserDAO {

	private static final String SQL_TABLE_COLUMN_ID = "id_users";
	private static final String SQL_TABLE_COLUMN_NAME = "name_users";
	private static final String SQL_TABLE_COLUMN_SURNAME = "surname_users";
	private static final String SQL_TABLE_COLUMN_LOGIN = "login_users";
	private static final String SQL_TABLE_COLUMN_EMAIL = "email_users";
	private static final String SQL_TABLE_COLUMN_PHONE = "phone_users";
	private static final String SQL_TABLE_COLUMN_GENDER = "gender_users";
	private static final int TABLE_ROLE_ID = 1;

	private static final String SELECT_Email_AND_PASSWORD = "SELECT * FROM users WHERE email_users = ?  AND   password_users = ?";
	private static final String INSERT_DATA_USER = "INSERT INTO users(name_users,surname_users,email_users,phone_users,login_users,password_users,gender_users,status_users,roles_id_roles) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_Email_AND_PASSWORD = "INSERT INTO users(password,email) VALUES(?,?)";
	private static final String UPDATE_USER_NAME = "UPDATE users SET name = ? WHERE id = ?";

	public User signIn(String userEmail, String userPassword) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		User user = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_Email_AND_PASSWORD);

			ps.setString(1, userEmail);
			ps.setString(2, userPassword);

			rs = ps.executeQuery();

			if (!rs.next()) {

				return null;
			}
			user = new User();

			user.setId(rs.getInt(SQL_TABLE_COLUMN_ID));
			user.setName(rs.getString(SQL_TABLE_COLUMN_NAME));
			user.setSurname(rs.getString(SQL_TABLE_COLUMN_SURNAME));
			user.setLogin(rs.getString(SQL_TABLE_COLUMN_LOGIN));
			user.setPhone(rs.getString(SQL_TABLE_COLUMN_PHONE));
			user.setEmail(rs.getString(SQL_TABLE_COLUMN_EMAIL));
			user.setGender(rs.getString(SQL_TABLE_COLUMN_GENDER));

		} catch (ConnectionPoolRuntimeException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
		return user;

	}

	public void registration(User user, String userPassword) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ConnectionPool connectionPool = null;

		Role role = new Role();

		role.setId(TABLE_ROLE_ID);
		user.setRole(role);

		try {

			connectionPool = ConnectionPool.getInstance();
			con = connectionPool.takeConnection();

			ps = con.prepareStatement(INSERT_DATA_USER);

			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPhone());

			ps.setString(5, user.getLogin());
			ps.setString(6, userPassword);

			ps.setString(7, user.getGender());
			ps.setString(8, user.getStatus());

			ps.setInt(9, user.getRole().getId());

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
	public void changeUserName(int id, String name) throws DAOException {

	
	}

	@Override
	public void changeUserSurname(int id, String surname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeUserLogin(int id, String login) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeUserPassword(int id, String password, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeUserEmail(int id, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void quickRegistration(String userEmail, String userPassword) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ConnectionPool connectionPool = null;

		try {

			connectionPool = ConnectionPool.getInstance();

			con = connectionPool.takeConnection();

			ps = con.prepareStatement(INSERT_Email_AND_PASSWORD);

			ps.executeQuery(INSERT_Email_AND_PASSWORD);

			ps.setString(1, userEmail);
			ps.setString(2, userPassword);

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
	public void changeUserStatus(int id, String status) {
		// TODO Auto-generated method stub

	}

}
