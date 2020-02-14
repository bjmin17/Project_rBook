package com.biz.rbooks.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

public class UserDTO {

	// 사용자 id
	@NotNull
	@NotBlank
//	@Size(min=4,max=20)
	private String m_id;//	varchar2(20)
	
	// 사용자 password
	@NotNull
	@NotBlank
//	@Size(min=8,max=20)
	private String m_password;//	nvarchar2(125)
	// 접속일자
	private String m_login_date;//	nvarchar2(10)
	// 기타
	private String m_rem;//	nvarchar2(125)

}
