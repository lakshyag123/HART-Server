package com.hart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hart.entity.User;
import com.hart.service.UserService;

import java.util.List;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/getallusers")
    public List<User> list(){
        return userService.getAllUsers();
    }
	
	@GetMapping("/user/{id}")
	public User getuser(@PathVariable String id) {
		return userService.getUserbyid(id);
	}
	
    @PostMapping("/adduser")
    public ResponseEntity<String> add(@Valid @RequestBody User user){
    	 String savedUser=userService.saveUser(user);
         return new ResponseEntity<String>(savedUser,HttpStatus.CREATED);
    }
    
    @PutMapping("/updateuser")
    public User update(@RequestBody User user) {
    	userService.updateUser(user);
        return user;
	}
    
    @DeleteMapping("/deleteuser/{id}")
    public String delete(@PathVariable String id) {
		return userService.deleteUser(id);
		
	}
}