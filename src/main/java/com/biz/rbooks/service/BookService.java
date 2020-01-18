package com.biz.rbooks.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.PageDTO;
import com.biz.rbooks.repository.BookDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

	protected final BookDao bDao;

	// 다오 생성자
	@Autowired
	public BookService(BookDao bDao) {
		super();
		this.bDao = bDao;
	}
	
	// 모든 도서 리스트를 보여주기
	public List<BookVO> selectAll(){
		return bDao.selectAll();
	}

	// 도서정보를 등록을 하는 메서드
	public int insert(BookVO bookVO) {
		
		// 날짜를 생성하고
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		
		String curDate = sd.format(date);
		
		// 현재 등록날짜가 비어 있으면 오늘 날짜를 입력한 채로 다오로 보냄
		if(bookVO.getB_year().isEmpty()) {
			bookVO.setB_year(curDate);
		}
		return bDao.insert(bookVO);
	}

	// 수정하는 메서드
	public int update(BookVO bookVO) {
		
		// 날짜를 생성하고
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		
		String curDate = sd.format(date);
		
		// 현재 등록날짜가 비어 있으면 오늘 날짜를 입력한 채로 다오로 보냄
		if(bookVO.getB_year().isEmpty()) {
			bookVO.setB_year(curDate);
		}
		return bDao.update(bookVO);
	}

	// 한개의 상세정보를 보여주기 위한 메서드
	public BookVO getBook(String str_seq) {

		// 컨트롤러로 부터 받은 매개변수를 이용해 시퀀스로 찾아오기
		BookVO bookVO = bDao.findByCode(str_seq);
		return bookVO;
	}

	// 	삭제를 실행하는 다오와 연결하는 메서드
	public int delete(String b_code) {
		
		return bDao.delete(b_code);
	}

	public List<BookVO> selectPagination(PageDTO pageDTO) {
		List<BookVO> bookVOList = bDao.selectPagination(pageDTO);
		return bookVOList;
	}

	public long totalCount() {
		return bDao.bookTotalCount();
	}

	public List<BookVO> findByBNames(String strText) {

		List<String> names = Arrays.asList(strText.split(" "));
		
		List<BookVO> bookList = bDao.findByBNames(names);
		
		return bookList;
	}
	
	
	
	
}
