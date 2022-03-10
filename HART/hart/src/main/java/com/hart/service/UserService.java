package com.hart.service;

import java.util.List;

import com.hart.entity.User;

public interface UserService {
	
	public String saveUser(User user);
    public List<User> getAllUsers();
	public User updateUser(User user);
	public User getUserbyid(String id);
	public String deleteUser(String id);
	
	
}
