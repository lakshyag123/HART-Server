package com.hart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hart.entity.User;
import com.hart.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public String saveUser(User user) {
		User existingUser =userRepository.findById(user.getMobilenumber()).orElse(null);
		
		if(existingUser==null)
		{
			String bcryptpassword=bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(bcryptpassword);
			userRepository.save(user);
			return "user created";
		}
		return "user already exists";
    }
	@Override
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	@Override
	public User getUserbyid(String id) {
		
		User existingUser =userRepository.findById(id).orElse(null);
		
		return existingUser;
	}
	@Override
	public User updateUser(User user) {
		
		try {
			  User existingUser =userRepository.findById(user.getMobilenumber()).orElse(null);
			  existingUser.setHostelnumber(user.getHostelnumber());
			  existingUser.setBatch(user.getBatch()); existingUser.setName(user.getName());
			  existingUser.setRoomnumber(user.getRoomnumber()); 
			  return userRepository.save(existingUser);
			 
		} catch (Exception e) {
			System.out.print(user.getMobilenumber());
			System.out.print("error");
			return user;
		}
	}
	@Override
	public String deleteUser(String id) {
		try {
			userRepository.deleteById(id);
			return "delete user : "+id;
		} catch (Exception e) {
			return "User not found";
		}
		
	}
	
}
