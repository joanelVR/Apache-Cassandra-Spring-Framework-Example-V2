package com.infosys.apachecassandra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.apachecassandra.entities.User;
import com.infosys.apachecassandra.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public User addUser(User newUser) {

		if(newUser.getPassword() == null) {
			return null;
		}
		
		for(User user : getAllUsers()) {
			if(user.getUsername().equals(newUser.getUsername())) {
				return null;
			}
		}
		
		return userRepo.save(newUser);
	}

	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findUserByUsername(username);
	}

	@Override
	public User getUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User loginUser(User user) {

		if(user.getPassword() == null) {
			return null;
		}
		
		return userRepo.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

}
