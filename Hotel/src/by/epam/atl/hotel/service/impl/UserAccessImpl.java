package by.epam.atl.hotel.service.impl;

import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.UserAccess;

public class UserAccessImpl implements UserAccess {

	@Override
	public boolean isUserAllowedSeeAllRooms(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAndStaffAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedSeeCloseRooms(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAndStaffAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedSeeOpenRooms(User user) {
		
		if (canCheckUserAccess(user)) {
			return allAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedDoBooking(User user) {
		
		if (canCheckUserAccess(user)) {
			return allAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedSeeAllBooking(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAndStaffAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedCloseRooms(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedOpenRooms(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedAddRooms(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedDeleteRooms(User user) {

		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		
		return false;
	}

	@Override
	public boolean isUserAllowedUpdateRooms(User user) {
		
		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		
		return false;
	}
	
	@Override
	public boolean isUserAllowedAddUsers(User user) {
		
		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		return false;
	}

	@Override
	public boolean isUserAllowedDeleteUsers(User user) {
		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		return false;
	}
	
	@Override
	public boolean isUserAllowedUpdateUsers(User user) {
		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		return false;
	}

	@Override
	public boolean isUserAllowedBanUsers(User user) {
		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		return false;
	}
	
	@Override
	public boolean isUserAllowedCancelBooking(User user) {
		if (canCheckUserAccess(user)) {
			return onlyAdministratorAllowed(user);
		}
		return false;
	}

	//private methods
	/*
	 * get permission only administrator
	 */
	private boolean onlyAdministratorAllowed(User user){
		switch (user.getType()){
		case ADMINISTRATOR:{

			return true;
		}
		case STAFF:{
			return false;
		}
		case EXTERNAL_USER:{
			return false;
		}
		default:{
			return false;
		}
		}
	}
	
	/*
	 * get permission to administrator and staff
	 */
	private boolean onlyAdministratorAndStaffAllowed(User user){
		switch (user.getType()){
		case ADMINISTRATOR:{

			return true;
		}
		case STAFF:{
			return true;
		}
		case EXTERNAL_USER:{
			return false;
		}
		default:{
			return false;
		}
		}
	}
	
	/*
	 * get permission to all registered users
	 */
	private boolean allAllowed(User user){
		switch (user.getType()){
		case ADMINISTRATOR:{

			return true;
		}
		case STAFF:{
			return true;
		}
		case EXTERNAL_USER:{
			return true;
		}
		default:{
			return false;
		}
		}
	}

	/*
	 * If user not exists or banned or not registered he can do nothing 
	 */
	private boolean canCheckUserAccess(User user){
		if (user == null) {
			return false;
		}

		if (user.getUserID() == 0){
			return false;
		}
		
		if (user.isBanned()) {
			return false;
		}
		return true;
	}

}
