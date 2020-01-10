package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookVO;

public interface BookDao {

	// 모든 도서리스트를 보여주는 메서드와 SQL문
	@Select("SELECT * FROM tbl_books")
	public List<BookVO> selectAll();

	// 특정 데이터 한개만 보여주는 메서드
	@Select("SELECT * FROM tbl_books WHERE b_code = #{b_code,jdbcType=VARCHAR}")
	public BookVO findByCode(String str_seq);
	
	// BookSQL 클래스에 있는 insert_sql 메서드를 이용해
	// 등록을 실현하는 코드
	@InsertProvider(type=BookSQL.class,method="insert_sql")
	public int insert(BookVO bookVO);

	// BookSQL 클래스에 있는 update_sql메서드를 이용해
	// 수정을 실현하는 코드
	@UpdateProvider(type=BookSQL.class,method="update_sql")
	public int update(BookVO bookVO);

	// 코드 한개를 매개변수로 받아서 삭제를 실현하는 메서드
	@Delete("DELETE FROM tbl_books WHERE b_code = #{b_code,jdbcType=VARCHAR}")
	public int delete(String b_code);

}
