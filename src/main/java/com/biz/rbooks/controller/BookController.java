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
import com.biz.rbooks.service.BookService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SessionAttributes("bookVO")
@RequestMapping(value="/book")
@Controller
public class BookController {
	
	BookService bService;
	
	@ModelAttribute("bookVO")
	public BookVO bookVO() {
		BookVO bookVO = new BookVO();
		return bookVO;
	}
	
	@Autowired
	public BookController(BookService bService) {
		super();
		this.bService = bService;
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String list(Model model) {
		List<BookVO> bookList = bService.selectAll();
		log.debug("리스트 : " + bookList.toString());
		model.addAttribute("bookList",bookList);
		return "home";
		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute("bookVO") BookVO bookVO , Model model) {
		
		
		model.addAttribute("bookVO",bookVO);
		
		return "book/insert";
				
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute("bookVO") BookVO bookVO , Model model, String s,
			SessionStatus sStatus) {
		
		int ret = bService.insert(bookVO);
		
		model.addAttribute("bookVO",bookVO);
		
		sStatus.setComplete();
		return "redirect:/book/";
				
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@RequestParam("id") String str_seq, @ModelAttribute BookVO bookVO, Model model) {
		
		bookVO = bService.getBook(str_seq);
		
		model.addAttribute("book", bookVO);
		return "book/view";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, @ModelAttribute("bookVO") BookVO bookVO, Model model) {
		log.debug("변경할 도서 코드 : " + id);
		
		bookVO = bService.getBook(id);
		log.debug("받아온 VO 정보 : " + bookVO.toString());
		model.addAttribute("bookVO", bookVO);
		
		return "book/insert";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("bookVO") BookVO bookVO, Model model,
			SessionStatus sStatus) {
		String originBCode = bookVO.getB_code(); 
		log.debug("바꾼 도서 코드 : " + bookVO.getB_code());
		log.debug("바꾼 VO 정보 : " + bookVO.toString());
		int ret = bService.update(bookVO);
		
		log.debug("변경한 도서 코드 : " + bookVO.getB_code());
		bookVO.setB_code(originBCode);
		sStatus.setComplete();
		return "redirect:/book/";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") String str_Seq) {
		log.debug("삭제할 도서 코드: " + str_Seq);
		int ret = bService.delete(str_Seq);
		return "redirect:/book/";
	}
	
}
