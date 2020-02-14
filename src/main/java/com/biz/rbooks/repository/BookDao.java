package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.PageDTO;
import com.biz.rbooks.domain.ReportDTO;

public interface BookDao {

	// 모든 도서리스트를 보여주는 메서드와 SQL문
	@Select("SELECT * FROM tbl_books")
	public List<BookVO> selectAll();

	// 특정 데이터 한개만 보여주는 메서드
	@Select("SELECT * FROM tbl_books WHERE b_code = #{b_code,jdbcType=VARCHAR}")
	public BookVO findByCode(String str_seq);
	
	@Select("SELECT * FROM tbl_read_book WHERE rb_bcode = #{rb_bcode}")
	public ReportDTO getBooks(String rb_bcode);
	
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

	/*
	 * offset부터 limit까지의 데이터만 추출하라
	 */
	@Select(BookSQL.pagination_sql)
	public List<BookVO> selectPagination(PageDTO pageDTO);

	@Select("SELECT count(*) FROM tbl_books ")
	public long bookTotalCount();

	@Select(BookSQL.findByBNames_sql)
	public List<BookVO> findByBNames(@Param("names") List<String> names);

	@Select(BookSQL.searchAllTotal)
	public List<BookVO> findByAll(@Param("bookVO") BookVO bookVO, @Param("pageDTO") PageDTO pageDTO);
//	@Select("SELECT * FROM tbl_books WHERE b_name LIKE '%' || #{b_name} || '%' OR b_auther LIKE '%' || #{b_auther} || '%' ")
//	public List<BookVO> findByAll(BookVO bookVO);

	@Select("SELECT count(*) FROM tbl_books WHERE b_name LIKE '%' || #{b_name} || '%' OR b_auther LIKE '%' || #{b_auther} || '%'")
	public long selectSearchAllTotal(String search);
	
	@Select(BookSQL.searchTitleTotal)
	public List<BookVO> findByTitle(BookVO bookVO, PageDTO pageDTO);

	@Select("SELECT count(*) FROM tbl_books WHERE b_name LIKE '%' || #{b_name} || '%' ")
	public long selectSearchTitleTotal(String search, PageDTO pageDTO);
	
	@Select(BookSQL.searchAuthTotal)
	public List<BookVO> findByAuth(BookVO bookVO);

	@Select("SELECT count(*) FROM tbl_books WHERE b_auther LIKE '%' || #{b_auther} || '%' ")
	public long selectSearchAuthTotal(String search);
	



	
}
