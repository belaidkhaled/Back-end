package com.example.demo;




import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.documentController;

@SpringBootApplication
public abstract class BackEndGedApplication   {
	
	public static void main(String[] args) {
		new File (documentController.UploadDirectory).mkdir();
		SpringApplication.run(BackEndGedApplication.class, args);
		
	}
}
