package by.epam.atl.hotel.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.hotel.bean.TypeUser;
import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.dao.DAOFactory;
import by.epam.atl.hotel.dao.UserDAO;
import by.epam.atl.hotel.dao.exception.DAOException;
import by.epam.atl.hotel.service.ServiceFactory;
import by.epam.atl.hotel.service.UserAccess;
import by.epam.atl.hotel.service.UserService;
import by.epam.atl.hotel.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
	private final UserAccess checkAccess = ServiceFactory.getInstance().getUserAccessService();
	private final UserDAO userDao = DAOFactory.getInstance().getUserDAO();
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass()); 
	
	@Override
	public User findUserByLogin(String login) throws ServiceException {
		User user;
		
		try {
			user = userDao.find(login);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return user;
	}
	
	@Override
	public User findUserByID(int id) throws ServiceException {
		User user;
		
		if (id <= 0) {
			throw new ServiceException("Illegal user id!");
		}
		
		try {
			user = userDao.find(id);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return user;
	}

	@Override
	public User findUserByLoginPassword(String login, String password) throws ServiceException {
		User user;
		
		if (login.isEmpty() || password.isEmpty()){
			throw new ServiceException("Login or password don't specified!");
		}
		
		try {
			user = userDao.find(login, password);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		return user;
	}

	@Override
	public boolean isUserEmailExists(String email) throws ServiceException {

		User user;
		
		if (email.isEmpty()){
			throw new ServiceException("Email doesn't specified!");
		}
		
		try {
			user = userDao.find(email);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
		
		if (user != null) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean isUserLoginExists(String login) throws ServiceException {
		try {
			return userDao.existLogin(login);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addUser(String login, String password, String name, TypeUser type, String email) throws ServiceException {
		
		if (login.isEmpty() ||
				password.isEmpty() ||
				email.isEmpty()){
			throw new ServiceException("Fields 'login', 'password' and 'email' has to be entered!");
		}

		try {
			User user = userDao.find(login);
			if (user != null) {
				throw new ServiceException("User whith login "+login+" already exists in database");
			}

			user = userDao.find(email);
			if (user != null){
				throw new ServiceException("User whith email "+email+" already exists in database");
			}
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}		
		
		User user = new User();
		user.setEmail(email);
		user.setUserLogin(login);
		user.setUserPassword(password);
		if (name.isEmpty() || name == null){
			user.setUserName(login);
		} else {
			user.setUserName(name);
		}
		
		if (type == null) {
			//type by default - External_user
			user.setType(TypeUser.EXTERNAL_USER);
		} else {
			user.setType(type);
		}

		return addUserIntoBase(user);
	}

	private boolean addUserIntoBase(User user) throws ServiceException{
		
		try {

			userDao.create(user);
			if (user.getUserID() != 0){
				return true;
			}else {
				return false;
			}

		}
		catch(DAOException e){
			throw new ServiceException(e);
		}
	}
	
	@Override
	public boolean deleteUser(User currentUser, User deleteUser) throws ServiceException {
		if (deleteUser.getUserID() == 0){
			throw new ServiceException("User not exists.");
		}

		if (currentUser.equals(deleteUser) || checkAccess.isUserAllowedDeleteUsers(currentUser)){

			try{
				userDao.delete(deleteUser);
				if (deleteUser.getUserID() == 0){
					return true;
				}
				return false;
			}
			catch(DAOException e){
				throw new ServiceException(e);
			}
		}else{
			throw new ServiceException("User not allowed delete other users.");
		}
		
	}

	@Override
	public int deleteUser(User currentUser, List<User> users) throws ServiceException {
		if (! checkAccess.isUserAllowedDeleteUsers(currentUser) ){
			throw new ServiceException("User not allowed to delete users.");
		}
		
		int processed = 0;
		int skipped = 0;
		
		for (User user : users){
			if (user == null) {
				skipped ++;
				continue;
			}
			
			if (deleteUser(currentUser, user)){
				processed ++;
			}

		}
		LOG.info("Operation 'delete users': were deleted "+processed+" users, were skipped "+ skipped+
				" users (because null or exists)");
		
		return processed;
	}

	@Override
	public void changeUserAccess(User currentUser, User userForChange, TypeUser type) throws ServiceException {
		if (! checkAccess.isUserAllowedUpdateUsers(currentUser) ){
			throw new ServiceException("User not allowed to change users.");
		}
		
		userForChange.setType(type);
		
		updateUserIntoBase(currentUser, userForChange);
		
	}

	@Override
	public void changeUserName(User currentUser, User userForChange, String name) throws ServiceException {
		if (name.isEmpty()){
			throw new ServiceException("User name can't be empty.");
		}
		
		if (currentUser.equals(userForChange) || checkAccess.isUserAllowedUpdateUsers(currentUser) ){
			
			userForChange.setUserName(name);
			
			updateUserIntoBase(currentUser, userForChange);
		} else {
			throw new ServiceException("User not allowed to change users.");
		}

	}

	@Override
	public void changeUserEmail(User currentUser, User userForChange, String email) throws ServiceException {
		if (email.isEmpty()){
			throw new ServiceException("User email can't be empty.");
		}
		
		//if email isn't changed do nothing
		if (userForChange.getEmail().equals(email)){
			return;
		}
		
		try{
			
			if (userDao.existEmail(email)){
				throw new ServiceException("New email already exists in database.");
			}
			
			userForChange.setEmail(email);
			updateUserIntoBase(currentUser, userForChange);
			
		}
		catch(DAOException e){
			throw new ServiceException("Email already exists");
		
		}
	}
	
	private void updateUserIntoBase(User currentUser, User changeUser) throws ServiceException {
		if (currentUser.equals(changeUser) || checkAccess.isUserAllowedUpdateUsers(currentUser) ){
			if (changeUser.getUserID() == 0){
				throw new ServiceException("User does not exists.");
			}

			try {
				userDao.update(changeUser);
			}
			catch (DAOException e){
				throw new ServiceException(e);
			}
		}else{
			throw new ServiceException("User not allowed to update users.");
		}
	}

	@Override
	public int banUsers(User currentUser, List<User> users) throws ServiceException {
		if (!checkAccess.isUserAllowedBanUsers(currentUser)){
			throw new ServiceException("User not allowed to ban users.");
		}

		int processed = 0;
		int skipped = 0;
		for (User user : users){
			if (user == null || user.getUserID() == 0 ){
				skipped ++;
				continue;
			}

			try {
				userDao.banUser(user);
				processed ++;
			}
			catch(DAOException e){
				throw new ServiceException(e);
			}
		}
		LOG.info("Operation 'ban users': were banned "+processed+" users, were skipped "+ skipped+
				" users (because null or not exists)");
		

		return processed;
	}

	@Override
	public int unbanUsers(User currentUser, List<User> users) throws ServiceException {
		if (!checkAccess.isUserAllowedBanUsers(currentUser)){
			throw new ServiceException("User not allowed to ban/unban users.");
		}

		int processed = 0;
		int skipped = 0;
		for (User user : users){
			if (user == null || user.getUserID() == 0 ){
				skipped ++;
				continue;
			}

			try {
				userDao.unbanUser(user);
				processed ++;
			}
			catch(DAOException e){
				throw new ServiceException(e);
			}
		}
		LOG.info("Operation 'ban users': were banned "+processed+" users, were skipped "+ skipped+
				" users (because null or not exists)");
		

		return processed;
	}
}
