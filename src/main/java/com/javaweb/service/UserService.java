package com.javaweb.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Role;
import com.javaweb.entity.User;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;


@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<User> listAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) roleRepository.findAll();
	}
	
	public void save(User user) {
		encodePassword(user);
		userRepository.save(user);
	}
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	
	
	
	public User get(Integer id) {
		try {
			return userRepository.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
}

