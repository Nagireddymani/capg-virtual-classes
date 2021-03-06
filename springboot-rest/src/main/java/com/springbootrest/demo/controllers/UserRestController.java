package com.springbootrest.demo.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootrest.demo.models.User;
import com.springbootrest.demo.services.ServiceRepo;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	ServiceRepo serviceRepo;
	
	@GetMapping("/message")
	public String showMessage()
	{
		return "Hello Capgemini";
	}
	
	@GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
		if(serviceRepo.getUserList().isEmpty())
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<User>>(serviceRepo.getUserList(),HttpStatus.OK);
    }
	
	@GetMapping("/users/id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
		User user=serviceRepo.getUser(userId);
		if(user==null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		//User user=serviceRepo.getUser(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user)
	{
		serviceRepo.addUser(user);
		if(serviceRepo.getUser(user.getUserId())!=null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		if(serviceRepo.getUser(user.getUserId())==null)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		User newUser=serviceRepo.updateUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/id/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int userId)
	{
		System.out.println("\n"+serviceRepo.deleteUser(userId)+"\n");
		if(serviceRepo.deleteUser(userId))
		{
			return new ResponseEntity<User>(HttpStatus.OK);	
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);	
	}
	
}
