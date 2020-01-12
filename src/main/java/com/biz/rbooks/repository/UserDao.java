package com.biz.rbooks.repository;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.UserDTO;

public interface UserDao {

	//@Param
	// 매개변수로 받은 아이디에 해당하는 값을 DB로부터 가져오기 위한 메서드
	@Select("SELECT * FROM tbl_MEMBER WHERE m_id = #{m_id,jdbcType=VARCHAR}")
	public UserDTO findById(String m_id);
	
	// DB에 있는 회원가입 된 수를 체크해서
	// 관리자나 일반 사용자로 사용하기 위한 메서드이지만
	// 미구현
	public int userCount();
	
	// 유저 서비스로 부터 userDTO 형태로 값을 전달받아서 DB에 값을 입력하기 위해 존재
	@InsertProvider(type = UserSQL.class, method="insert_sql")
	public int userInsert(UserDTO userDTO);

	
}
