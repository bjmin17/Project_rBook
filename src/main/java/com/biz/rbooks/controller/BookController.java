package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.PageDTO;
import com.biz.rbooks.service.BookService;
import com.biz.rbooks.service.PageService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SessionAttributes("bookVO")
@RequestMapping(value="/book")
@Controller
public class BookController {
	
	private final BookService bService;
	private final PageService pService;
	
	String searchField = null;
	String search = null;
	
	// bookVO 생성자 생성
	@ModelAttribute("bookVO")
	public BookVO bookVO() {
		BookVO bookVO = new BookVO();
		return bookVO;
	}
	
	
	@Autowired
	public BookController(BookService bService, PageService pService) {
		super();
		this.bService = bService;
		this.pService = pService;
	}

	// 도서정보 전체 리스트 보여주는 코드
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String list(
			@RequestParam(value="currentPageNo",required = false,defaultValue = "1") int currentPageNo,
			Model model, SessionStatus sStatus, 
			@RequestParam(value = "searchField",required = false, defaultValue = "allList") String searchField, 
			@RequestParam(value = "search",required = false, defaultValue = "") String search) {

		List<BookVO> bookListPagiNation = null;
		PageDTO pageDTO = null;
		long totalCount = 0;
		
		search = search.trim();
		
//		String searchField = null;
		if(searchField.equalsIgnoreCase("allList")) {
			// 제목+저자
			totalCount = bService.selectSearchAllTotal(search);
			pageDTO = pService.getPagination(totalCount, currentPageNo);
			
			bookListPagiNation = bService.selectAllSearch(search, pageDTO);
			
		} else if(searchField.equalsIgnoreCase("title")) {
			// 제목으로만 검색했을 때
			totalCount = bService.selectSearchTitleTotal(search);
			pageDTO = pService.getPagination(totalCount, currentPageNo);
			bookListPagiNation = bService.selectTitle(search, pageDTO);
			
		} else if(searchField.equalsIgnoreCase("auth")) {
			// 저자로 검색했을 때
			totalCount = bService.selectSearchAuthTotal(search);
			pageDTO = pService.getPagination(totalCount, currentPageNo);
			bookListPagiNation = bService.selectAuth(search, pageDTO);
			
		} else if(search.trim() == null || search.isEmpty()) {
			// 빈칸 입력하거나 띄어쓰기만 했을 경우
//			bookListPagiNation = bService.selectAllSearch(searchField, search);
			totalCount = bService.totalCount();
			pageDTO = pService.getPagination(totalCount, currentPageNo);
			bookListPagiNation = bService.selectAllSearch(search, pageDTO);
			
		} else {
			// 나머지
//			bookListPagiNation = bService.selectAllSearch(searchField, search);
			totalCount = bService.totalCount();
			pageDTO = pService.getPagination(totalCount, currentPageNo);
			bookListPagiNation = bService.selectAllSearch(search, pageDTO);
		}
		
		if(totalCount < 1) {
			totalCount = 1;
			pageDTO = pService.getPagination(totalCount, currentPageNo);
			bookListPagiNation = bService.selectAllSearch(search, pageDTO);
		}
		
//		bookListPagiNation = bService.selectPagination(pageDTO);
//		
//		// 서비스로부터 가져와서 bookList에 담고
//		List<BookVO> bookList = bService.selectAll();
//		log.debug("리스트 : " + bookList.toString());
		
		// bookList에 담은 값을 "bookList"라는 값에 담으며 jsp파일에 보내줌
		model.addAttribute("bookList",bookListPagiNation);
		
		model.addAttribute(pageDTO);
		model.addAttribute("BODY","BOOK");
		model.addAttribute("search", search);
		
		sStatus.setComplete();
		return "home";
		
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(@RequestParam(value="strText",required = false,defaultValue = "") String strText, Model model) {
		
		List<BookVO> bookList = bService.findByBNames(strText);
		
		model.addAttribute("bookList",bookList);
		model.addAttribute("BODY","SEARCH");
		
		return "include/book-body";
		
	}
	
	// 도서정보 등록 메서드, jsp의 form에 설정된 modelAttribute의 값을
	// 가져와서bookVO 에 담는다
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute("bookVO") BookVO bookVO , Model model) {
		
		
		model.addAttribute("bookVO",bookVO);
		
		return "book/insert";
				
	}
	
	// 도서정보 등록 메서드로 서비스에서 구현한 인서트 메서드를 실행하고 그 적용된 값을
	// bookVO에 담으면서 "bookVO"라는 이름으로 jsp로 보냄
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute("bookVO") BookVO bookVO , Model model, String s,
			SessionStatus sStatus) {
		
		// 등록 메서드 구현
		int ret = bService.insert(bookVO);
		
		model.addAttribute("bookVO",bookVO);
		
		sStatus.setComplete();
		return "redirect:/book/";
				
	}
	
	// 도서정보 상세보기 메서드, 주소 값의 ?id= 에서 id 값을 
	// @RequestParam 으로 가져오고 그 값을 str_seq에 담는다.
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@RequestParam("id") String str_seq, @ModelAttribute BookVO bookVO, Model model) {
		// str_seq로 특정 도서의 상세정보를 불러오는 서비스 구현
		// 그 결과값을 bookVO에 담는다
		bookVO = bService.getBook(str_seq);
		
		// 서비스를 통해서 가져온 bookVO를
		// "book"이라는 이름으로 jsp로 보낸다.
		model.addAttribute("book", bookVO);
		return "book/view";
	}
	
	// 도서정보 수정 메서드, 주소 값의 id를 가져와서
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, @ModelAttribute("bookVO") BookVO bookVO, Model model) {
		
		// 특정 도서의 정보를 가져오는 메서드를 실현하고
		// 그 결과 값을 bookVO에 담는다.
		bookVO = bService.getBook(id);
		// bookVO를 "bookVO"라는 값으로 jsp파일에 보낸다.
		model.addAttribute("bookVO", bookVO);
		
		
		return "book/insert";
	}
	
	// 도서정보 수정 메서드, 
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("bookVO") BookVO bookVO, Model model,
			SessionStatus sStatus) {
		
		// 원래 코드 값을 저장하는 변수
		String originBCode = bookVO.getB_code(); 
		
		// 북서비스의 업데이트를 실현한다.
		int ret = bService.update(bookVO);
		
		
		// 서비스에서 코드를 변경할 경우,
		// 코드가 바뀌지 않도록 원래 코드 값을 저장해주기
		bookVO.setB_code(originBCode);
		
		// 세션의 값을 없애주는 코드
		sStatus.setComplete();
		return "redirect:/book/";
	}
	
	
	// 도서정보 삭제 메서드,
	// id 값을 가져와서 그 값을 str_Seq에 담고
	// 그에 해당하는 데이터를 삭제하기
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") String str_Seq) {
		// 서비스의 delete 메서드를 실행하기 
		int ret = bService.delete(str_Seq);
		return "redirect:/book/";
	}
	
}
