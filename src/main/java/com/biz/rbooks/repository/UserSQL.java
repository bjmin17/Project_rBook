package com.biz.rbooks.repository;

import org.apache.ibatis.jdbc.SQL;

public class UserSQL {

	
	// 등록 실행하는 SQL
	public String insert_sql() {
		
		return new SQL() {{
			
			INSERT_INTO("tbl_member");
			
			INTO_COLUMNS("M_ID");
			INTO_COLUMNS("M_PASSWORD");
			INTO_COLUMNS("M_LOGIN_DATE");
			INTO_COLUMNS("M_REM");

			INTO_VALUES("#{m_id,jdbcType=VARCHAR}");
			INTO_VALUES("#{m_password,jdbcType=VARCHAR}");
			INTO_VALUES("#{m_login_date,jdbcType=VARCHAR}");
			INTO_VALUES("#{m_rem,jdbcType=VARCHAR}");
		}}.toString();
	}
	
	// 회원정보 수정을 한다면 사용할 메서드
	public String update_sql() {
		return "";
				
	}
}
