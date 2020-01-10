package com.biz.rbooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.UserDTO;
import com.biz.rbooks.repository.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	
	private final BCryptPasswordEncoder PasswordEncoder;
	private final UserDao uDao;

	@Autowired
	public UserService(BCryptPasswordEncoder passwordEncoder, UserDao uDao) {
		super();
		this.PasswordEncoder = passwordEncoder;
		this.uDao = uDao;
	}

	/*
	 * 회원가입처리 : insert
	 * 최초가입자는 관리자로
	 */
	public int userJoin(UserDTO userDTO) {
	
		// 회원가입된 유저가 없다면
		if(userDTO == null ) {
			
		}
		String cryptText = PasswordEncoder.encode(userDTO.getM_password());
		userDTO.setM_password(cryptText);
		return uDao.userInsert(userDTO);
	}
	
	/*
	 * id 중복검사
	 * 이미 해당 id가 있으면 true를 return하고
	 * 없으면 false return
	 */
	public boolean userIdCheck(String u_id) {
		UserDTO userDTO = uDao.findById(u_id);
		
		// u_id로 불러온 userDTO가 있고, 그 id가
		// 매개변수에 있는 u_id와 같다면
		if(userDTO != null && userDTO.getM_id().equalsIgnoreCase(u_id)) {
			return true;
		}
		return false;
	}
	
	/*
	 * 로그인처리
	 * 회원id와 password를 전달받아서
	 * 일치하면 true를 return
	 * 그렇지 않으면 false return
	 */
	public boolean userLoginCheck(UserDTO userDTO) {
		
		log.debug("유저체크 : " + userDTO.toString());
		// 사용자가 입력한 id
		String inU_id = userDTO.getM_id();
		log.debug("userDTO의 아이디 값 : " + userDTO.getM_id());
		
		// 사용자가 로그인하면서 입력한 평문상태의 비밀번호
		String inU_pass = userDTO.getM_password();
		
		// 사용자가 입력한 id로
		// Dao를 통해 등록되어있는 데이터를 찾아서
		// 그 값을 userRDTO에 담아준다.
		UserDTO userRDTO = uDao.findById(inU_id);
		log.debug("서비스, 로그인 체크에서 userRDTO 값 확인 : " + userRDTO.toString());
		log.debug("DB에 등록된 DTO 정보 : " + userRDTO.toString());
		// 회원정보가 없을 경우
		if(userRDTO == null) {
			return false;
		}
		
		// Dao를 통해서(SELECT) 조회한 id 
		String sU_id = userRDTO.getM_id();
		log.debug("userRDTO에 만들어진 아이디 값 : "+ sU_id);
		String sU_password = userRDTO.getM_password();
		// DB에 저장된 암호화된 상태의 사용자 비밀번호
		String cryptU_pass = userRDTO.getM_password();
		String cryptText = PasswordEncoder.encode(userDTO.getM_password());
		
		// 회원 id가 존재하는 경우
		if(sU_id.equalsIgnoreCase(inU_id)) {
			PasswordEncoder.matches(inU_pass, cryptU_pass);
			
//			userDTO = userRDTO;
			
			return true;
		} else {
			return false;
		}
	
	}
	
	
	public UserDTO getUser(String u_id) {
		
		return uDao.findById(u_id);
	}
	
}
