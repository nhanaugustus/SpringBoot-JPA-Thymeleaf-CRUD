package com.javaweb;

import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		System.out.println("class path :"+System.getProperty("java.class.path"));
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
