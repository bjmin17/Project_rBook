package com.biz.rbooks.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.PageDTO;
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
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		
		String curDate = sd.format(date);
		String curTime = st.format(date);
		
		// 독서록 등록날짜가 비어있을 경우
		if(reportDTO.getRb_date().isEmpty()) {
			// 현재 날짜 입력
			reportDTO.setRb_date(curDate);
		}
		// 독서시작시간 비어있으면
		if(reportDTO.getRb_stime().isEmpty()) {
			// 현재 시간 입력
			reportDTO.setRb_stime(curTime);
		}
		
		return rDao.insert(reportDTO);
	}

	// 수정을 실행하는 다오와 연결하는 메서드
	public int update(ReportDTO reportDTO) {
		
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		String curDate = sd.format(date);
		String curTime = st.format(date);
		// 독서록 등록날짜가 비어있을 경우
		if(reportDTO.getRb_date().isEmpty()) {
			// 현재 날짜 입력
			reportDTO.setRb_date(curDate);
		}
		// 독서시작시간 비어있으면
		if(reportDTO.getRb_stime().isEmpty()) {
			// 현재 시간 입력
			reportDTO.setRb_stime(curTime);
		}
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

	public long totalCount() {
		return rDao.reportTotalCount();
	}

	public List<ReportDTO> selectPagination(PageDTO pageDTO) {
		List<ReportDTO> reportDTOList = rDao.selectPagination(pageDTO);
		return reportDTOList;
	}
}
