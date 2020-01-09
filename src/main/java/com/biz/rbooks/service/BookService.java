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

	@Autowired
	public BookService(BookDao bDao) {
		super();
		this.bDao = bDao;
	}
	
	public List<BookVO> selectAll(){
		return bDao.selectAll();
	}

	public int insert(BookVO bookVO) {

		return bDao.insert(bookVO);
	}

	
	
	
	public int update(BookVO bookVO) {
		log.debug("컨트롤러부터 받아온 업데이트 코드" + bookVO.getB_code());
		
		return bDao.update(bookVO);
	}

	public BookVO getBook(String str_seq) {

		BookVO bookVO = bDao.findByCode(str_seq);
		return bookVO;
	}

	public int delete(String b_code) {
		
		return bDao.delete(b_code);
	}
	
	
	
	
}
