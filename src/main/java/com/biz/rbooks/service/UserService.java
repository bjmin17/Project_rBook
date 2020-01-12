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

	// 암호화를 위해서 BCryptPasswordEncoder를 이용
	private final BCryptPasswordEncoder PasswordEncoder;
	private final UserDao uDao;

	@Autowired
	public UserService(BCryptPasswordEncoder passwordEncoder, UserDao uDao) {
		super();
		this.PasswordEncoder = passwordEncoder;
		this.uDao = uDao;
	}

	// 회원가입 처리
	public int userJoin(UserDTO userDTO) {
	
		// 컨트롤러로 부터 받은 jsp파일에서 부터 가져온
		// 사용자가 직접 입력한 비밀번호가 담긴 dto를 받고
		// 그 안에 담긴 비밀번호를 가져와서
		// 암호화를 수행
		String cryptText = PasswordEncoder.encode(userDTO.getM_password());
		
		// 암호화된 비밀번호를 다시 dto에 담아서 Dao로 보낸다
		userDTO.setM_password(cryptText);
		return uDao.userInsert(userDTO);
	}
	
	/*
	 * id 중복검사
	 * 이미 해당 id가 있으면 true를 return하고
	 * 없으면 false return
	 */
//	public int userIdCheck(String u_id) {
//		UserDTO userDTO = uDao.findById(u_id);
//		return 0;
//		// u_id로 불러온 userDTO가 있고, 그 id가
//		// 매개변수에 있는 u_id와 같다면
//		if(userDTO != null && userDTO.getM_id().equalsIgnoreCase(u_id)) {
//			return true;
//		}
//		return false;
//	}
	
	/*
	 * 로그인처리
	 * 회원id와 password를 전달받아서
	 * 처리
	 */
	public UserDTO userLoginCheck(UserDTO userDTO) {
		
		// 사용자가 입력한 id
		String inU_id = userDTO.getM_id();
		
		// 사용자가 로그인하면서 입력한 평문상태의 비밀번호
		String inU_pass = userDTO.getM_password();
		
		// 사용자가 입력한 id로

		// DB에 저장된 것 중에 일치하는
		// 데이터를 userRDTO에 담아준다.
		UserDTO userRDTO = uDao.findById(inU_id);
		// 회원정보가 없을 경우
		if(userRDTO == null) {
			// 메서드 종료
			return null;
		}
		
		// 회원 id가 존재하는 경우
		if(userRDTO != null) {
			// 암호화된 비밀번호 가져오기
			String cryptPassword = userRDTO.getM_password();
			// 직접 입력한 비밀번호(inU_pass)와 암호화된 비밀번호(cryptPassword)비교하기
			if(PasswordEncoder.matches(inU_pass, cryptPassword)) {
				// 일치한다면 그 데이터 DTO를 보내주기
				return userRDTO;
			}
		}
		return null;
	}
	
	// 유저 정보를 가져오기위한 메서드
	public UserDTO getUser(String u_id) {
		
		return uDao.findById(u_id);
	}
	
}
