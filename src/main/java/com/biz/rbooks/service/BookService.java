package com.biz.rbooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.rbooks.domain.BookVO;
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
	
	// 모든 도서 리스트를 보여주는 다오와 연결하는 메서드
	public List<BookVO> selectAll(){
		return bDao.selectAll();
	}

	// 등록을 실행하는 다오와 연결하는 메서드
	public int insert(BookVO bookVO) {

		return bDao.insert(bookVO);
	}

	// 수정을 실행하는 다오와 연결하는 메서드
	public int update(BookVO bookVO) {
		log.debug("컨트롤러부터 받아온 업데이트 코드" + bookVO.getB_code());
		
		return bDao.update(bookVO);
	}

	// 특정 데이터 한개의 정보를 가져와서 BookVO 형태로 가져오는 메서드
	public BookVO getBook(String str_seq) {

		BookVO bookVO = bDao.findByCode(str_seq);
		return bookVO;
	}

	// 	삭제를 실행하는 다오와 연결하는 메서드
	public int delete(String b_code) {
		
		return bDao.delete(b_code);
	}
	
	
	
	
}
