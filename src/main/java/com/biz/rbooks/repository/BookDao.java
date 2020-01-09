package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookVO;

public interface BookDao {

	@Select("SELECT * FROM tbl_books")
	public List<BookVO> selectAll();

	@Select("SELECT * FROM tbl_books WHERE b_code = #{b_code,jdbcType=VARCHAR}")
	public BookVO findByCode(String str_seq);
	
	@InsertProvider(type=BookSQL.class,method="insert_sql")
	public int insert(BookVO bookVO);

	@UpdateProvider(type=BookSQL.class,method="update_sql")
	public int update(BookVO bookVO);

	@Delete("DELETE FROM tbl_books WHERE b_code = #{b_code,jdbcType=VARCHAR}")
	public int delete(String b_code);

}
