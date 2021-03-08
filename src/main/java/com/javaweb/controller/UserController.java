package com.javaweb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaweb.entity.Role;
import com.javaweb.entity.User;
import com.javaweb.service.UserService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "/users")
	public String listAll(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers",listUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		User user = new User();
		user.setEnabled(true);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user",user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user,RedirectAttributes redirectAttributes,@RequestParam("image")MultipartFile multipartFile) throws IOException{		
		if(!multipartFile.isEmpty()) {	
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setPhotos(fileName);		
	        userService.save(user);	
			String uploadDir = "user-photos/"+user.getId();
			Path uploadPath = Paths.get(uploadDir);
			System.out.println(uploadPath);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }        
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }      	    
		}
        userService.save(user);	
		redirectAttributes.addFlashAttribute("message","The user has been saved successfully!");
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") Integer id,Model model,RedirectAttributes redirectAttributes) {
		User user = userService.get(id);
	    List<Role> listRoles = userService.listRoles();
	    System.out.println("edit user :"+user.toString());
		model.addAttribute("user",user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		User user = userService.get(id);
		userService.delete(user);
	return "redirect:/users";
	}

}
