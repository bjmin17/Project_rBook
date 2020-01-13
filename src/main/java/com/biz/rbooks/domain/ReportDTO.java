package com.biz.rbooks.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReportDTO {

	// 독서록
	private long rb_seq;//	number
	private String rb_bcode;//	varchar2(20)
	private String rb_id;//	varchar2(20 byte)
	private String rb_date;//	varchar2(10)
	private String rb_stime;//	varchar2(10)
	private String rb_rtime;//	number(10,3)
	private String rb_subject;//	nvarchar2(20)
	private String rb_text;//	nvarchar2(400)
	
	// 도서정보에 등록된 도서명을 가져오기 위해 생성
	// 한개만 가져올테니 List가 아닌
	private List<BookVO> b_name_list;
	
	private int rb_star;//	number

}
