package com.biz.rbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rbooks.domain.BookVO;
import com.biz.rbooks.domain.ReportDTO;
import com.biz.rbooks.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value="/report")
@Controller
public class ReportController {

	private final ReportService rService;
	
	public ReportDTO reportDTO() {
		ReportDTO reportDTO = new ReportDTO();
		return reportDTO;
	}

	@Autowired
	public ReportController(ReportService rService) {
		super();
		this.rService = rService;
	}
	
	// 독서록정보 전체 리스트 보여주는 코드
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String list(Model model) {
		// 서비스로부터 가져와서 bookList에 담고
		List<ReportDTO> reportList = rService.selectAll();
		
		// bookList에 담은 값을 "bookList"라는 값에 담으며 jsp파일에 보내줌
		model.addAttribute("reportList",reportList);
		return "report/home";
	}
	// 독서록정보 등록 메서드, jsp의 form에 설정된 modelAttribute의 값을
	// 가져와서 reportDTO에 담는다
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute("reportDTO") ReportDTO reportDTO, Model model) {
		
		
		model.addAttribute("reportDTO",reportDTO);
		
		return "report/insert";
				
	}
	
	// 독서록정보 등록 메서드로 서비스에서 구현한 인서트 메서드를 실행하고 그 적용된 값을
	// reportDTO담으면서 "reportDTO"라는 이름으로 jsp로 보냄
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute("reportDTO") ReportDTO reportDTO , Model model, String s,
			SessionStatus sStatus) {
		
		// 등록 메서드 구현
		int ret = rService.insert(reportDTO);
		
		model.addAttribute("reportDTO",reportDTO);
		
		sStatus.setComplete();
		return "redirect:/report/";
				
	}
	
	// 독서록정보 상세보기 메서드, 주소 값의 ?id= 에서 id 값을 
	// @RequestParam 으로 가져오고 그 값을 str_seq에 담는다.
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@RequestParam("id") String str_seq, @ModelAttribute ReportDTO reportDTO, Model model) {
		// str_seq로 특정 도서의 상세정보를 불러오는 서비스 구현
		// 그 결과값을 bookVO에 담는다
		reportDTO = rService.getBook(str_seq);
		
		// 서비스를 통해서 가져온 bookVO를
		// "book"이라는 이름으로 jsp로 보낸다.
		model.addAttribute("reportDTO", reportDTO);
		return "report/view";
	}
	
	// 독서록정보 수정 메서드, 주소 값의 id를 가져와서
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String id, @ModelAttribute("reportDTO") ReportDTO reportDTO, Model model) {
		
		
		// 특정 독서록의 정보를 가져오는 메서드를 실현하고
		// 그 결과 값을 reportDTO에 담는다.
		reportDTO = rService.getBook(id);
		// bookVO를 "bookVO"라는 값으로 jsp파일에 보낸다.
		model.addAttribute("reportDTO", reportDTO);
		
		return "report/insert";
	}
	
	// 독서록정보 수정 메서드, 
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute("reportDTO") ReportDTO reportDTO, Model model,
			SessionStatus sStatus) {
		
		log.debug("report컨트롤러에서 받은 날짜 값 : " + reportDTO.getRb_date());
		
		// 원래 코드 값을 저장하는 변수
		long originRSeq = reportDTO.getRb_seq();
		
		// 독서록서비스의 업데이트를 실현한다.
		int ret = rService.update(reportDTO);
		
		// 서비스에서 코드를 변경할 경우,
		// 코드가 바뀌지 않도록 원래 코드 값을 저장해주기
		reportDTO.setRb_seq(originRSeq);
		
		// 세션의 값을 없애주는 코드
		sStatus.setComplete();
		return "redirect:/report/";
	}
	
	// 도서정보 삭제 메서드,
	// id 값을 가져와서 그 값을 str_Seq에 담고
	// 그에 해당하는 데이터를 삭제하기
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") String str_Seq) {
		log.debug("삭제할 도서 코드: " + str_Seq);
		// 서비스의 delete 메서드를 실행하기 
		int ret = rService.delete(str_Seq);
		return "redirect:/report/";
	}
}
