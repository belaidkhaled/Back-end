package com.example.demo;






import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public abstract class BackEndGedApplication   {
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndGedApplication.class, args);
		
	}
}