package com.biz.rbooks.controller;

import javax.validation.Valid;

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
	
	// 유저 서비스를 사용하기 위해 생성자 메서드로 생성
	@Autowired
	public UserController(UserService uService) {
		super();
		this.uService = uService;
	}
	
	// 회원가입을 위해 있는 메서드
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
		 * <form> 태그만을 사용해서 <input>을 했을 경우
		 * name에 DTO의 값들을 맞게 지정해주면서 넘겨주기.
		 * 
		 */
		// 유저 DTO를 새로 생성
		UserDTO userDTO = new UserDTO();
		
		/*
		 * user/insert.jsp에 새로 생성된 userDTO를
		 * "userDTO"에 보내고
		 * 
		 * "BODY"에는 "JOIN"이라는 글자를 보내면서
		 * 홈 화면에서 사용
		 */
		model.addAttribute("userDTO",userDTO);
//		model.addAttribute("BODY","JOIN");
		
		
		return "user/insert";
	}
	
	// @Valid?
	// user/insert.jsp에서 <form:form>를 이용해서 값을 입력하는데
	// 이 때, 값들은 ModelAttribute("userDTO")에 저장된 값들이 실려오고
	// 이 메서드에서 "userDTO"에 있는 값들을 받아서 userDTO를 매개변수로 받고
	
	// BindingResult는 왜 쓰는지 모르겠어요
	// BindingResult 의 경우 ModelAttribute 을 이용해 매개변수를 Bean 에 binding 할 때 
	// 발생한 오류 정보를 받기 위해 선언해야 하는 애노테이션입니다.
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
			BindingResult bResult, Model model) {
		
		// 에러가 발생한다면??
		if(bResult.hasErrors()) {
			return "user/insert";
		} else {
			// 에러가 발생하지 않으면 유저서비스의 회원가입 메서드를 실행
			int ret = uService.userJoin(userDTO);
			return "redirect:/book/";
		}
		
	}
	
}
