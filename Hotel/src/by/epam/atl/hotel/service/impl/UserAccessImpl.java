package by.epam.atl.hotel.service.impl;

import by.epam.atl.hotel.bean.User;
import by.epam.atl.hotel.service.UserAccess;

public class UserAccessImpl implements UserAccess {

	@Override
	public boolean isUserAllowedSeeAllRooms(User user) {

		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

	@Override
	public boolean isUserAllowedSeeCloseRooms(User user) {
		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

	@Override
	public boolean isUserAllowedSeeOpenRooms(User user) {
		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

	@Override
	public boolean isUserAllowedDoBooking(User user) {
		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

	@Override
	public boolean isUserAllowedSeeAllBooking(User user) {
		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

	@Override
	public boolean isUserAllowedCloseRooms(User user) {
		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

	@Override
	public boolean isUserAllowedOpenRooms(User user) {
		if (user == null) {
			return false;
		}

		if (user.isBanned()) {
			return false;
		}

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

}
