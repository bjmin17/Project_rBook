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
public class BookVO {
	// 도서코드
	private String b_code;//	varchar2(20 byte)
	// 도서명
	private String b_name;//	nvarchar2(125 char)
	// 저자
	private String b_auther;//	nvarchar2(125 char)
	// 출판사
	private String b_comp;//	nvarchar2(125 char)
	// 출판연도
	private String b_year;//	varchar2(10 byte)
	// 가격
	private int b_iprice;//	number
	
	private List<ReportDTO> bookReportList; 
}
