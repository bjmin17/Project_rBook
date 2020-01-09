package com.biz.rbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.rbooks.service.UserService;

@RequestMapping(value="/user")
@Controller
public class UserController {

	UserService uService;

	public UserController(UserService uService) {
		super();
		this.uService = uService;
	}
	
	
	
	
}
