package com.biz.rbooks.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.rbooks.domain.PageDTO;
import com.biz.rbooks.domain.ReportDTO;
import com.biz.rbooks.domain.UserDTO;
import com.biz.rbooks.service.PageService;
import com.biz.rbooks.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SessionAttributes("reportDTO")
@RequestMapping(value="/report")
@Controller
public class ReportController {

	private final ReportService rService;
	private final PageService pService;
	
	@ModelAttribute("reportDTO")
	public ReportDTO reportDTO() {
		ReportDTO reportDTO = new ReportDTO();
		return reportDTO;
	}

	@Autowired
	public ReportController(ReportService rService, PageService pService) {
		super();
		this.rService = rService;
		this.pService = pService;
	}
	
	// 독서록정보 전체 리스트 보여주는 코드
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String list(
			@RequestParam(value="currentPageNo",required = false,defaultValue = "1") int currentPageNo,
			Model model, SessionStatus sStatus, 
			HttpSession httpSession) {
		// 서비스로부터 가져와서 bookList에 담고
//		List<ReportDTO> reportList = rService.selectAll();
		String userId = null;
		long totalCount = 0;
		
		if(httpSession != null) {
			UserDTO userDTO = (UserDTO) httpSession.getAttribute("userDTO");
			userId = userDTO.getM_id();
		}
		totalCount = rService.totalCount(userId);
		
		PageDTO pageDTO = pService.getPagination(totalCount, currentPageNo);
//		List<ReportDTO> reportPagination = rService.selectPagination(pageDTO);
		
		List<ReportDTO> reportList = rService.selectAll(userId, pageDTO);
		// bookList에 담은 값을 "bookList"라는 값에 담으며 jsp파일에 보내줌
		model.addAttribute("reportList",reportList);
//		model.addAttribute("BNAME",reportList.get(0)?.getBNameList().get(0).getB_name());
		if(pageDTO != null) {
			model.addAttribute(pageDTO);
		}
		model.addAttribute("BODY","REPORT");
		
		sStatus.setComplete();
		return "home";
	}
	// 독서록정보 등록 메서드, jsp의 form에 설정된 modelAttribute의 값을
	// 가져와서 reportDTO에 담는다
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(@ModelAttribute("reportDTO") ReportDTO reportDTO, Model model) {
		
		
//		model.addAttribute("reportDTO",reportDTO);
		
		return "report/insert";
				
	}
	
	// 독서록정보 등록 메서드로 서비스에서 구현한 인서트 메서드를 실행하고 그 적용된 값을
	// reportDTO담으면서 "reportDTO"라는 이름으로 jsp로 보냄
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute("reportDTO") ReportDTO reportDTO , Model model, String s,
			SessionStatus sStatus, HttpSession httpSession) {
		
		UserDTO userDTO = (UserDTO) httpSession.getAttribute("userDTO");
		reportDTO.setRb_id(userDTO.getM_id());
		
//		 도서정보 불러온 값을 rDTO에 저장, 도서명 나오기 위해
//		ReportDTO rDTO = rService.findByBCode(reportDTO.getRb_seq()+"");
		
		// 등록 메서드 구현
		int ret = rService.insert(reportDTO);
//		reportDTO.setB_name(rDTO.getB_name());
		
		
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
		
		
//		ReportDTO rDTO = rService.findByBCode(reportDTO.getRb_bcode());
//		log.debug("도서명 가져오기 위한 DTO : " + rDTO.toString());
//		
//		reportDTO.setB_name(rDTO.getB_name());
		
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
		
		// 독서록서비스의 업데이트를 실현한다.
		int ret = rService.update(reportDTO);
		
		// 세션의 값을 없애주는 코드
		sStatus.setComplete();
		return "redirect:/report/";
	}
	
	// 독서록정보 삭제 메서드,
	// jsp파일로부터 rb_seq로 지정된 id 값을 가져와서 그 값을 str_Seq에 담고
	// 그에 해당하는 데이터를 삭제하기
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") String str_Seq) {
		// 서비스의 delete 메서드를 실행하기 
		int ret = rService.delete(str_Seq);
		return "redirect:/report/";
	}
}
