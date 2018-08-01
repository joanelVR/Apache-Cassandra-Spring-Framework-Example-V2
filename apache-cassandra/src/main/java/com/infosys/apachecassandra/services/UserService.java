package com.infosys.apachecassandra.services;

import java.util.List;

import com.infosys.apachecassandra.entities.User;

public interface UserService {

	public User addUser(User newUser);
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	public User getUserById(Long id);
	public User updateUser(User user);
	public User loginUser(User user);
	public void deleteUser(Long id);
}
