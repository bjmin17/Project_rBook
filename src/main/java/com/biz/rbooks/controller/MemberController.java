package com.biz.rbooks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biz.rbooks.domain.UserDTO;
import com.biz.rbooks.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes("bookVO")
@RequestMapping(value="/member")
@Controller
public class MemberController {

	private final UserService uService;

	// 서비스 사용하기 위해 생성자 생성
	@Autowired
	public MemberController(UserService uService) {
		super();
		this.uService = uService;
	}
	
	/*
	 * DB에 사용자 저장하기
	 * password부분을 암호화하기
	 * spring form tag를 활용한 유효성 검사
	 */
	
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		return null;
	}
	
	// 로그인 눌렀을 때, 
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@RequestParam(value="LOGIN_MSG",required = false,
			defaultValue = "0") String msg, Model model) {
		
		model.addAttribute("LOGIN_MSG",msg);
		model.addAttribute("BODY","LOGIN");
		
		return "member/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String BODY, @ModelAttribute UserDTO userDTO, Model model,
			HttpSession httpSession) {
		
		log.debug("멤버컨트롤러 유저DTO : " + userDTO.toString());
		log.debug("멤버컨트롤러 바디 : " + BODY);
		boolean loginOK = uService.userLoginCheck(userDTO);
		
		if(loginOK == true) {
			// 왜 씀?
			httpSession.setMaxInactiveInterval(10*60);
			
			// DB로부터 사용자 정보 가져오기
			userDTO = uService.getUser(userDTO.getM_id());
			httpSession.setAttribute("userDTO", userDTO);
		} else {
			httpSession.removeAttribute("userDTO");
			model.addAttribute("LOGIN_MSG","FAIL");
			return "redirect:/member/login";
		}
		
		return "redirect:/report/";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		httpSession.setAttribute("userDTO", null);
		httpSession.removeAttribute("userDTO");
		
		return "redirect:/report/";
	}
	
	
	
}
