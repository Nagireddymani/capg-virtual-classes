package com.springbootrest.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootrest.demo.models.User;
import com.springbootrest.demo.repository.DaoRepo;

@Service
public class ServiceRepo {

	@Autowired
	DaoRepo daoRepo;
	
//	private Map<Integer,User> userList;
//	
//	@PostConstruct
//	public void addSomeUsers()
//	{
//		userList=new HashMap<>();
//		User user1=new User(101,"Manikanta","manikanta@gmail.com",9999999999L);
//		User user2=new User(102,"Ashritha" ,"ashritha@gmail.com" ,9999999998L);
//		User user3=new User(103,"Ravali"   ,"ravali@gmail.com"   ,9999999997L);
//		User user4=new User(104,"Ushasri"  ,"ushasri@gmail.com"  ,9999999996L);
//		
//		userList.put(user1.getUserId(),user1);
//		userList.put(user2.getUserId(),user2);
//		userList.put(user3.getUserId(),user3);
//		userList.put(user4.getUserId(),user4);
//		
//	}
//	public Map<Integer, User> getUsers() {
//		return userList;
//	}
	
	
	public List<User> getUserList()
	{
//		List<User> allUsers=new ArrayList<>();
//		userList.forEach((k,v)->allUsers.add(v));
		
		return  daoRepo.findAll();
	}

	public User getUser(int userId)
	{
		//return userList.get(userId);
	      return daoRepo.getOne(userId);
	}
	
	public User addUser(User user)
	{
//        userList.put(user.getUserId(),user);
//        return user;
		return daoRepo.save(user);
	}
	
	public boolean deleteUser(int userId)
	{
	    
//		if(userList.containsKey(userId))
//		{
//			userList.remove(userId);
//			return true;
//		}
//		else
//		{
//			return false;
//		}
		daoRepo.deleteById(userId);
		return !daoRepo.existsById(userId);
		
	}

	public User updateUser(User user)
	{
//		User oldUserData=userList.get(user.getUserId());
//		oldUserData.setUserName(user.getUserName());
//		oldUserData.setEmail(user.getEmail());
//		oldUserData.setPhoneNumber(user.getPhoneNumber());
//		
		User newUser=daoRepo.getOne(user.getUserId());
		newUser.setUserName(user.getUserName());
		newUser.setEmail(user.getEmail());
		newUser.setPhoneNumber(user.getPhoneNumber());
		
		return daoRepo.save(newUser);
	}
}
