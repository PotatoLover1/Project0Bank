package com.example.services;

import java.sql.SQLException;
import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.exceptions.UsernameAlreadyExistsException;
import com.example.models.User;

public class UsersS {
	private UserDao uDao;
	
	public UsersS(UserDao u) {
		this.uDao = u;
		
	}
	
	public User signUp(String first, String last, String email, String password) throws UsernameAlreadyExistsException{
		User u = new User(first, last, email, password);
		
		try {
			uDao.createUser(u);
		}catch(SQLException e) {
			throw new UsernameAlreadyExistsException();
		}
		return u;
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		
		User u = uDao.getUserByUsername(username);
		
		if(u.getId() == 0) {
			throw new UserDoesNotExistException();
		}
		else if(!u.getPassword().equals(password)) {
			throw new InvalidCredentialsException();
		}
		else {
			return u;
		}
	}
	
}
