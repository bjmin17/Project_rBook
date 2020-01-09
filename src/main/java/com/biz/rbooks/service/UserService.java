package com.biz.rbooks.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.UserDTO;

@Service
public class UserService {

	
	BCryptPasswordEncoder PasswordEncoder;
	
	/*
	 * 회원가입처리 : insert
	 * 최초가입자는 관리자로
	 */
	public int userJoin(UserDTO userDTO) {
	
		return 0;
	}
	
	/*
	 * id 중복검사
	 * 이미 해당 id가 있으면 true를 return하고
	 * 없으면 false return
	 */
	public boolean userIdCheck(String u_id) {
		
		return true;
	}
	
	/*
	 * 로그인처리
	 * 회원id와 password를 전달받아서
	 * 일치하면 true를 return
	 * 그렇지 않으면 false return
	 */
	public boolean userLoginCheck(UserDTO userDTO) {
		
		return true;
	}
	
	
	public UserDTO getUser(String u_id) {
		
		return null;
	}
	
}
