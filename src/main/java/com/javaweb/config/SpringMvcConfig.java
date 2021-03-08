package com.javaweb.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName ="user-photos";		
		Path userPhotosDir = Paths.get(dirName);		
		String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();	
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+userPhotosPath+"/");
		//registry.addResourceHandler("/user-photos/**").addResourceLocations("/"+userPhotosPath);
	}

	
	
	
	
	
	

}
