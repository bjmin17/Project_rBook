package com.biz.rbooks.repository;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import com.biz.rbooks.domain.UserDTO;

public interface UserDao {

	//@Param
	@Select("SELECT * FROM tbl_MEMBER WHERE m_id = #{m_id,jdbcType=VARCHAR}")
	public UserDTO findById(String m_id);
	
	public int userCount();
	
	@InsertProvider(type = UserSQL.class, method="insert_sql")
	public int userInsert(UserDTO userDTO);

	
}
