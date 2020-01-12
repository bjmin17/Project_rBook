package com.biz.rbooks.repository;

import org.apache.ibatis.jdbc.SQL;

public class ReportSQL {

	
	// 등록 실행하는 SQL
	public String insert_sql() {
		
		return new SQL() {{
			
			INSERT_INTO("tbl_read_book");
			
			INTO_COLUMNS("RB_SEQ");
			INTO_COLUMNS("RB_BCODE");
			INTO_COLUMNS("RB_ID");
			INTO_COLUMNS("RB_DATE");
			INTO_COLUMNS("RB_STIME");
			INTO_COLUMNS("RB_RTIME");
			INTO_COLUMNS("RB_SUBJECT");
			INTO_COLUMNS("RB_TEXT");
			INTO_COLUMNS("RB_STAR");

			INTO_VALUES("seq_read_book.NEXTVAL");
			INTO_VALUES("#{rb_bcode,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_id,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_date,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_stime,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_rtime,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_subject,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_text,jdbcType=VARCHAR}");
			INTO_VALUES("#{rb_star,jdbcType=VARCHAR}");
		}}.toString();
	}
	
	// 수정을 실행할 sql문
	public String update_sql() {
		
		return new SQL() {{
			UPDATE("tbl_read_book");
			WHERE("rb_seq = #{rb_seq,jdbcType=VARCHAR}");
			SET("rb_bcode = #{rb_bcode,jdbcType=VARCHAR}");
			SET("rb_id = #{rb_id,jdbcType=VARCHAR}");
			SET("rb_date = #{rb_date,jdbcType=VARCHAR}");
			SET("rb_stime = #{rb_stime,jdbcType=VARCHAR}");
			SET("rb_rtime = #{rb_rtime,jdbcType=VARCHAR}");
			SET("rb_subject = #{rb_subject,jdbcType=VARCHAR}");
			SET("rb_text = #{rb_text,jdbcType=VARCHAR}");
			SET("rb_star = #{rb_star,jdbcType=VARCHAR}");
			
			
		}}.toString();
	}
}
