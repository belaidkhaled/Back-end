package com.example.demo;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.controller.userController;
import com.example.demo.data.entity.user;
import com.example.demo.service.userService;


@SpringBootApplication
public abstract class BackEndGedApplication   {
	
	@Autowired
	private userService service;
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndGedApplication.class, args);
			}
	
	@Bean
    public void CommandLineRunnerBean() {
			List<user> response=null;
			response = service.listAll();
			service.setIndex(5);
			
	}
}




/*
List<user> response=null;
		@Autowired
		private userService service;
		try {
			  response = service.listAll();
			} catch(EntityNotFoundException e) {
				statusCode=HttpStatus.GONE;
			} catch(Exception e) {
				e.printStackTrace();
				statusCode=HttpStatus.INTERNAL_SERVER_ERROR;
			}
		



*/