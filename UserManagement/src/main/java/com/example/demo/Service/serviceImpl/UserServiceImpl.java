package com.example.demo.Service.serviceImpl;

import java.util.List;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepo;

	@Override
	public User addUser(User user) {
		if(user!=null)
		{
			if(user.getRole()==null)
				user.setRole("USER");

			return userRepo.saveAndFlush(user);
			
		}
		return null;
	}

	@Override
	public boolean loginUser(String username, String password) {
		
		User user1 = userRepo.validateUser(username, password);
		System.out.println("User: "+ user1.getUsername());
		if(user1!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public List<User> getAllUsers() {
	
		List<User> userList = userRepo.findAll();
		
		if(userList!=null & userList.size() >0)
		{
			return userList;
		}
		else
			return null;
	}


	@Override
	public boolean petNameExist(String petName)
	{
		User user = userRepo.findByPetName(petName);
		if(user!=null)
			return true;
	return false;
	}


	@Override
	public boolean forgetPassword(String petName, String username,String newPassword)
	{
		User user = userRepo.findBypetNameAnduserName(petName,username);
		if(user!=null)
		{
			System.out.println("user="+user);
			user.setPassword(newPassword);
			userRepo.saveAndFlush(user);
			return true;
		}
		return false;
	}


	@Override
	public String getUserRole(String username, String password) {
		User userLists=userRepo.findByUsernameAndPassword(username, password);
		return userLists.getRole();
	}

	@Override
	public User getUser(String username,String password)
	{
		return userRepo.findByUsernameAndPassword(username, password);

	}
	
}














