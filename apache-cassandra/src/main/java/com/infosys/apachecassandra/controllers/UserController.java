package com.infosys.apachecassandra.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.apachecassandra.entities.User;
import com.infosys.apachecassandra.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(value="/register", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@Valid @RequestBody User newUser) {
		return userService.addUser(newUser);
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PatchMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@Valid @RequestBody User updatedUser) {
		return userService.updateUser(updatedUser);
	}
	
	@GetMapping(value="/id/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping(value="/username/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	
	@PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public User loginUser(@RequestBody User user) {
		return userService.loginUser(user);
	}
	
	@DeleteMapping(value="/delete/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@RequestBody User user) {
		userService.deleteUser(user.getId());
	}
}
