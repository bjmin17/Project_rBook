package com.biz.rbooks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.rbooks.domain.UserDTO;
import com.biz.rbooks.service.UserService;

@RequestMapping(value="/user")
@Controller
public class UserController {

	private final UserService uService;

	@Autowired
	public UserController(UserService uService) {
		super();
		this.uService = uService;
	}
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(Model model) {
		
		/*
		 * insert.jsp에서 spring form tag를 사용하면서
		 * <form:form modelAttribute="userDTO">를 사용했기 때문에
		 * modelAttribute를 설정해두었는데
		 * 해당 Attribute를 전달해주지 않으면
		 * form을 열 때, 오류가 발생을 한다.
		 * 때문에 비어있는(초기화만된) userDTO를
		 * model에 실어서 렌더링에 보낸다.
		 * 
		 */
		// 유저 DTO를 새로 생성
		UserDTO userDTO = new UserDTO();
		
		model.addAttribute("userDTO",userDTO);
		model.addAttribute("BODY","JOIN");
		
		
		return "user/insert";
	}
	
	// @Valid?
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute("userDTO")  UserDTO userDTO,
			BindingResult bResult, Model model) {
		
		if(bResult.hasErrors()) {
			return "user/insert";
		} else {
			int ret = uService.userJoin(userDTO);
			return "redirect:/book/";
		}
		
	}
	
}
