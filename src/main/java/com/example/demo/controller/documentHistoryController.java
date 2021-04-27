package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.dao.documentHistoryRepo;
import com.example.demo.service.documentHistoryService;

@CrossOrigin
@Controller
@RestController
public class documentHistoryController {

	@Autowired
	private documentHistoryService service;
	
	@Autowired
	private documentHistoryRepo repo;
}
