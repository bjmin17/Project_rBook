package com.biz.rbooks.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.ReportDTO;

public interface ReportDao {

	// 모든 독서록리스트를 보여주는 메서드와 SQL문
	
	@Select("SELECT * FROM tbl_read_book")
	@Results(
	value = {
			
			@Result(property = "rb_bcode",column = "rb_bcode"),
			@Result(property = "b_name_list",column = "rb_bcode", javaType = List.class,
							many = @Many(select = "getBooks")
						
					)
	}
	)
	public List<ReportDTO> selectAll();
	
	@Select("SELECT * FROM tbl_books WHERE b_code = #{b_code}")
	public BookVO getBooks(String b_code);

	public ReportDTO findBySeq(String img_seq);

	// 특정 데이터 한개만 보여주는 메서드
	@Select("SELECT * FROM tbl_read_book WHERE rb_seq= #{rb_seq,jdbcType=VARCHAR}")
	public ReportDTO findByCode(String rb_seq);
	
	// ReportSQL 클래스에 있는 insert_sql 메서드를 이용해
	// 등록을 실현하는 코드
	@InsertProvider(type=ReportSQL.class,method="insert_sql")
	public int insert(ReportDTO reportDTO);
	
	// ReportSQL 클래스에 있는 update_sql메서드를 이용해
	// 수정을 실현하는 코드
	@UpdateProvider(type=ReportSQL.class,method="update_sql")
	public int update(ReportDTO reportDTO);

	// 코드 한개를 매개변수로 받아서 삭제를 실현하는 메서드
	@Delete("DELETE FROM tbl_read_book WHERE rb_seq = #{rb_seq,jdbcType=VARCHAR}")
	public int delete(String rb_seq);
}
