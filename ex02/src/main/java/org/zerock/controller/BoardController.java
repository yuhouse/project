package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list 요청 실행");
//		
//		List<BoardVO> list=service.getList();
//		model.addAttribute("list",list);
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri,Model model) {
		log.info("list 요청 실행"+cri);
		
		List<BoardVO> list=service.getList(cri);
		model.addAttribute("list",list);
		
		int total=service.getTotal(cri);
		log.info("전체갯수:"+total);
		
		PageDTO pageDTO=new PageDTO(cri,total);
		model.addAttribute("pageMaker",pageDTO);
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		log.info("등록 요청 실행:"+board);
		service.register(board);
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get","/modify"})
	public void get(Long bno,Model model,
			@ModelAttribute("cri") Criteria cri) {
		log.info("/get(글읽기) 또는 /modify(글수정) 요청");
		
		BoardVO board=service.get(bno);
		model.addAttribute("board",board);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,RedirectAttributes rttr,
			@ModelAttribute("cri") Criteria cri) {
		log.info("modify:"+board);
		log.info("cri:"+cri);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
//		rttr.addAttribute("pageNum",cri.getPageNum());
//		rttr.addAttribute("amount",cri.getAmount());
//		rttr.addAttribute("type",cri.getType());
//		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list"+cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(Long bno,RedirectAttributes rttr,
			@ModelAttribute("cri") Criteria cri) {		
		log.info("삭제처리요청..."+bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
//		rttr.addAttribute("pageNum",cri.getPageNum());
//		rttr.addAttribute("amount",cri.getAmount());
//		rttr.addAttribute("type",cri.getType());
//		rttr.addAttribute("keyword",cri.getKeyword());
		
		return "redirect:/board/list"+cri.getListLink();
	}
}
