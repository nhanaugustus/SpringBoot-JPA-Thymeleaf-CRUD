package com.javaweb.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.entity.User;
import com.javaweb.service.UserService;

@RestController
public class UserRestController {
	
	
	@Autowired
	private UserService service;
	
	@GetMapping("/api/users")
	public List<User> listAll() {
		return service.listAll();
	}
	
	@PostMapping("/api/create")
	public void creat(@RequestBody User user) {
		service.save(user);
	}

}
