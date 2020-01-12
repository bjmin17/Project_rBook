package com.biz.rbooks.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.ReportDTO;
import com.biz.rbooks.repository.BookDao;
import com.biz.rbooks.repository.ReportDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportService {

	protected final ReportDao rDao;
	protected final BookDao bDao;

	// 독서록 다오와 도서정보 다오를 사용하기 위해 생성자 메서드 생성
	@Autowired
	public ReportService(ReportDao rDao, BookDao bDao) {
		super();
		this.rDao = rDao;
		this.bDao = bDao;
	}
	
	// 독서록을 모두 보여주는 메서드
	public List<ReportDTO> selectAll(){
		return rDao.selectAll();
	}
	
	// 등록을 하는 메서드
	public int insert(ReportDTO reportDTO) {

		// 현재 날짜 생성해서
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		String curDate = sd.format(date);
		
		// 컨트롤러로 부터 전달받은 dto에 값을 저장
		reportDTO.setRb_date(curDate);
		
		return rDao.insert(reportDTO);
	}

	// 수정을 실행하는 다오와 연결하는 메서드
	public int update(ReportDTO reportDTO) {
		
		return rDao.update(reportDTO);
	}

	// 특정 데이터 한개의 정보를 가져와서 ReportDTO 형태로 가져오는 메서드
	public ReportDTO getBook(String str_seq) {

		ReportDTO reportDTO = rDao.findByCode(str_seq);
		return reportDTO;
	}

	// 	삭제를 실행하는 다오와 연결하는 메서드
	public int delete(String rb_seq) {
		
		return rDao.delete(rb_seq);
	}
}
