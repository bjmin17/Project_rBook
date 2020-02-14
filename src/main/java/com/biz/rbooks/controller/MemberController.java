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
	 * 목표였던 것
	 * DB에 사용자 저장하기
	 * password부분을 암호화하기
	 * spring form tag를 활용한 유효성 검사
	 */
	
	// 
//	@RequestMapping(value="/join",method=RequestMethod.GET)
//	public String join(Model model) {
//		model.addAttribute("BODY","LOGIN");
//		return "home";
//	}
	
	// 로그인 눌렀을 때, 로그인 화면이 나오도록 이어주는 메서드
	// LOGIN_MSG는 왜 쓰는지 모르겠
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(@RequestParam(value="LOGIN_MSG",required = false,
			defaultValue = "0") String msg, Model model) {
		
		// 매개변수에서 받은 @RequestParam의 ("LOGIN_MSG")를 msg에 담고
		// 그 값을 다시 "LOGIN_MSG"에 담아주는 코드
		model.addAttribute("LOGIN_MSG",msg);
		
		return "member/login";
	}
	
	// login의 POST 방식으로 받았을 때, 활성화되는 메서드
	// login에서 보내주는 userDTO에 있는 값들을 매개변수로 받는다.
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute UserDTO userDTO, Model model,
			HttpSession httpSession) {
		
		// member 폴더의 login.jsp에서 가져온 userDTO 내의
		// id와 password를 가지고 
		// 유저 서비스에 있는 로그인 체크하는 메서드를 실행한다.
		UserDTO userLogin = uService.userLoginCheck(userDTO);
		
		// 로그인 체크가 완료되면 userLogin은 null값이 아니기 때문에
		// if문 내의 조건이 true가 되며 진행된다.
		if(userLogin != null) {
			// 왜 씀?
			// 최대허용시간을 세팅하는 코드로
			// 10*60은 600초로 최대허용시간은 10분이 된다.
			httpSession.setMaxInactiveInterval(10*60);
			
			// DB로부터 사용자 정보 가져오기
			userDTO = uService.getUser(userDTO.getM_id());
			httpSession.setAttribute("userDTO", userDTO);
		} else {
			// httpSession에 저장된 "userDTO" 값을 제거해주는 코드
			httpSession.removeAttribute("userDTO");
			// 로그인 체크에서 아이디나 비밀번호가 일치하지 않았기 때문에
			// "LOGIN_MSG"에 "FAIL"이라는 문자열 값을 담아서 보내준다.
			model.addAttribute("LOGIN_MSG","FAIL");
			return "redirect:/member/login";
		}
		
		return "redirect:/report/";
	}
	
	// 로그아웃을 실행할때 쓰는 메서드,
	// 기존에 저장되어있었던 httpSession의 값을 매개변수로 가져와서
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession httpSession) {
		// 이건 왜?
		httpSession.setAttribute("userDTO", null);
		// 로그아웃을 위해 저장되어있는 값을 remove 해준다 
		httpSession.removeAttribute("userDTO");
		
		return "redirect:/book/";
	}
	
	
	
}
