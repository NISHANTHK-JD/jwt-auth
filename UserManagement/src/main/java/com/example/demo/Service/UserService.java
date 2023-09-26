package com.example.demo.Service;

import java.util.List;

import com.example.demo.Model.User;

public interface UserService 
{
	public User addUser(User user);
	
	public boolean loginUser(String username, String password);
	
	public List<User> getAllUsers();


	boolean petNameExist(String petName);

	boolean forgetPassword(String petName, String username, String newPassword);

    String getUserRole(String username, String password);

	User getUser(String username, String password);
}
