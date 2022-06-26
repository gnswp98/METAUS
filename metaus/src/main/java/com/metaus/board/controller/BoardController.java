package com.metaus.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metaus.board.model.BoardAtcVO;
import com.metaus.board.model.BoardService;
import com.metaus.board.model.BoardVO;
import com.metaus.common.ConstUtil;
import com.metaus.common.FileUploadUtil;
import com.metaus.common.PaginationInfo;
import com.metaus.common.SearchVO;
import com.metaus.member.model.MemberService;
import com.metaus.member.model.MemberVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private static final Logger logger
	=LoggerFactory.getLogger(BoardController.class);
	
	private final BoardService boardService;
	private final FileUploadUtil fileUploadUtil;
	private final MemberService memberService;
	
	@RequestMapping("/notice")
	public String notice(@ModelAttribute SearchVO searchVo,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		logger.info("notice 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("notice 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("notice 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("notice 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("notice 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("notice 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		
		return "/board/notice";
	}
	
	@RequestMapping("/faq")
	public String faq(@ModelAttribute BoardVO boardVo,
			HttpSession session,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		String memId = (String)session.getAttribute("memId");
		logger.info("FAQ 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		
		List<BoardVO>list = boardService.selectBoardFaq(boardVo);
		logger.info("FAQ 목록 조회 결과, list.size={}", list.size());
		
		model.addAttribute("list", list);
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId", memId);
		
		return "/board/faq";
	}
	
	@RequestMapping("/news")
	public String news(@ModelAttribute SearchVO searchVo,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		logger.info("news 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("news 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("news 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("news 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("news 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("news 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		
		return "/board/news";
	}
	
	@RequestMapping("/freeBoard")
	public String freeBoard(@ModelAttribute SearchVO searchVo,
			HttpSession session,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		String memId = (String)session.getAttribute("memId");
		logger.info("freeBoard 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("freeBoard 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("freeBoard 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("freeBoard 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("freeBoard 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("freeBoard 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId", memId);
		
		return "/board/freeBoard";
	}
	
	@RequestMapping("/QuestionBoard")
	public String QuestionBoard(@ModelAttribute SearchVO searchVo,
			HttpSession session,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		String memId = (String)session.getAttribute("memId");
		logger.info("QuestionBoard 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("QuestionBoard 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("QuestionBoard 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("QuestionBoard 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("QuestionBoard 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("QuestionBoard 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId", memId);
		
		return "/board/QuestionBoard";
	}
	
	@RequestMapping("/qna")
	public String qna(@ModelAttribute SearchVO searchVo,
			HttpSession session,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		String memId = (String)session.getAttribute("memId");
		logger.info("qna 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("qna 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("qna 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("qna 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("qna 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("qna 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		logger.info("searchVo.getRecordCountPerPage={}", searchVo.getRecordCountPerPage());
		logger.info("pagingInfo.getRecordCountPerPage={}", pagingInfo.getRecordCountPerPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId", memId);
		
		return "/board/qna";
	}


	@RequestMapping("/shareBoard")
	public String shareBoard(@ModelAttribute SearchVO searchVo,
			HttpSession session,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		String memId = (String)session.getAttribute("memId");
		logger.info("shareBoard 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("shareBoard 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("shareBoard 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("shareBoard 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("shareBoard 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("shareBoard 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId", memId);
		
		return "/board/shareBoard";
	}
	
	@RequestMapping("/requestBoard")
	public String requestBoard(@ModelAttribute SearchVO searchVo,
			HttpSession session,
			@RequestParam(defaultValue = "0")int btypeNo, Model model) {
		String memId = (String)session.getAttribute("memId");
		logger.info("requestBoard 페이지 - 게시판 종류 btypeNo={}", btypeNo);
		logger.info("requestBoard 페이지, searchVo={}", searchVo);
		
		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(ConstUtil.BLOCKSIZE);
		pagingInfo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		logger.info("t ={}",pagingInfo.getCurrentPage());
		
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		logger.info("t2={}",pagingInfo.getFirstRecordIndex());
		logger.info("t3={}",searchVo.getFirstRecordIndex());
		searchVo.setRecordCountPerPage(ConstUtil.RECORD_COUNT);
		searchVo.setBtypeNo(btypeNo);
		
		List<Map<String, Object>>list = boardService.selectBoard(searchVo);
		logger.info("requestBoard 목록 조회 결과, list.size={}", list.size());
		List<BoardAtcVO> atcList = boardService.selectBoardAtc();
		int totalRecord=boardService.selectTotalRecord(btypeNo);
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("requestBoard 목록 조회-레코드 개수, totalRecord={}", totalRecord);
		logger.info("requestBoard 목록 조회-pagingInfo, pagingInfo.getFirstPage={}", pagingInfo.getFirstPage());
		logger.info("requestBoard 목록 조회-pagingInfo, pagingInfo.getLastPage={}", pagingInfo.getLastPage());
		
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("list",list);
		model.addAttribute("atcList",atcList);
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId", memId);
		
		return "/board/requestBoard";
	}
	
	@GetMapping("/boardWrite")
	public String boardWrite_get(@RequestParam(defaultValue = "0") int btypeNo,
			HttpSession session,
			Model model) {
		logger.info("커뮤니티 글 작성 페이지, 파라미터 btypeNo={}",btypeNo);
		
		String memId = (String)session.getAttribute("memId");
		
		logger.info("커뮤니티 글 작성 페이지, 파라미터 memId={}",memId);
		
		model.addAttribute("btypeNo", btypeNo);
		model.addAttribute("memId",memId);
		return "/board/boardWrite";
	}
	
	@PostMapping("/boardWrite")
	public String boardWrite_post(@RequestParam int btypeNo,
			@RequestParam String memId,
			@ModelAttribute BoardVO boardVo, 
			@ModelAttribute BoardAtcVO boardAtcVo,
			HttpServletRequest request, 
			Model model) {
		logger.info("커뮤니티 글 작성 페이지, 파라미터 btypeNo={}", btypeNo);
		
		MemberVO memVo = memberService.selectByUserid(memId);
		
		String fileName = "", originFileName = "";
		
		try {
			List<Map<String, Object>> fileList = fileUploadUtil.fileUpload(request, ConstUtil.UPLOAD_FILE_FLAG);

			for (Map<String, Object> fileMap : fileList) {
				originFileName = (String) fileMap.get("originalFileName");
				fileName = (String) fileMap.get("fileName");
			}
			logger.info("파일 업로드 성공, fileName={}", fileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		boardVo.setMemNo(memVo.getMemNo());
		boardVo.setBtypeNo(btypeNo);
		int cnt = boardService.insertBoard(boardVo);
		logger.info("글 작성 결과 조회, cnt={}", cnt);
		
		boardAtcVo.setBfileFilename(fileName);
		boardAtcVo.setBfileOriginname(originFileName);
		boardAtcVo.setBoardNo(boardVo.getBoardNo());
		
		int upload = boardService.insertBoardAtc(boardAtcVo);
		logger.info("파일 업로드 결과 조회, upload={}", upload);
		
		if(cnt>0) {
			logger.info("글 작성 성공!");
		}else {
			logger.info("글 작성 실패!");
		}
		
		
		if(btypeNo==3) {
			return "redirect:/board/qna?btypeNo=3";
		}else if(btypeNo==8) {
			return "redirect:/board/freeBoard?btypeNo=8";
		}else if(btypeNo==5) {
			return "redirect:/board/QuestionBoard?btypeNo=5";
		}else if(btypeNo==6) {
			return "redirect:/board/shareBoard?btypeNo=6";
		}else if(btypeNo==7) {
			return "redirect:/board/requestBoard?btypeNo=7";
		}
		return "/";
	}
	
	
	@RequestMapping("/readCountUp")
	public String readCountUp(@RequestParam(defaultValue = "0") int boardNo,
			HttpSession session,
			@RequestParam(defaultValue = "0") int btypeNo,
			Model model) {
		String memName=(String)session.getAttribute("memName");
		logger.info("게시글 조회수 증가, 파라미터 boardNO={}, btypeNo={}", boardNo, btypeNo);
		
		int cnt = boardService.updateBoardReadCount(boardNo);
		logger.info("게시글 조회수 증가 결과, 파라미터 cnt={}", cnt);
		
		logger.info("게시글 상세조회 - 파라미터 boardNo={}", boardNo);
		
		BoardVO vo = boardService.selectBoardDetail(boardNo);
		logger.info("게시글 상세조회 결과, vo={}", vo);
		
		BoardAtcVO AtcVo = boardService.selectBoardAtcByNo(boardNo);
		logger.info("게시글 상세조회 파일 결과, AtcVo={}", AtcVo);
		
		MemberVO memVo = memberService.selectByMemNo(vo.getMemNo());
		
		model.addAttribute("memName",memName);
		model.addAttribute("memVo", memVo);
		model.addAttribute("vo", vo);
		model.addAttribute("AtcVo", AtcVo);
		
		
		model.addAttribute("boardNo", boardNo);
		model.addAttribute("btypeNo", btypeNo);
		
		return "/board/boardDetail";
	}
	
	@GetMapping("/boardUpdate")
	public String boardUpdate_get(@RequestParam int boardNo,
		@RequestParam int bfileNo, 
		Model model) {
		logger.info("글 수정 페이지, 파라미터 boardNo={}, bfileNo={}", boardNo, bfileNo);
	
		BoardVO vo = boardService.selectByBoardNo(boardNo);
		BoardAtcVO AtcVo = boardService.selectBoardAtcByNo(bfileNo);
		
		model.addAttribute("vo", vo);
		model.addAttribute("AtcVo", AtcVo);
		
		return "/board/boardUpdate";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdate_post(@ModelAttribute BoardVO boardVo,
			HttpSession session,
			HttpServletRequest request,
			@ModelAttribute BoardAtcVO boardAtcVo,
			Model model) {
		String memName=(String)session.getAttribute("memName");
		logger.info("글 수정 페이지, 파라미터 boardVo={}, boardAtcVo={}", boardVo, boardAtcVo);
		logger.info("글 수정 페이지, 파라미터 memName={}", memName);
		
		int boardResult = boardService.updateBoard(boardVo);
		logger.info("글 내용 수정 결과, boardResult={}", boardResult);
		
		
		String fileName = "", originFileName = "";
		
		try {
			List<Map<String, Object>> fileList = fileUploadUtil.fileUpload(request, ConstUtil.UPLOAD_FILE_FLAG);

			for (Map<String, Object> fileMap : fileList) {
				originFileName = (String) fileMap.get("originalFileName");
				fileName = (String) fileMap.get("fileName");
			}
			logger.info("파일 업로드 성공, fileName={}", fileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		boardAtcVo.setBfileFilename(fileName);
		boardAtcVo.setBfileOriginname(originFileName);
		boardAtcVo.setBoardNo(boardVo.getBoardNo());
		
		int boardAtcResult = boardService.updateBoardAtc(boardAtcVo);
		logger.info("글 파일 수정 결과, boardAtcResult={}", boardAtcResult);
		
		MemberVO memVo = memberService.selectByMemNo(boardVo.getMemNo());
		
		model.addAttribute("memName",memName);
		model.addAttribute("memVo", memVo);
		
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("boardAtcVo", boardAtcVo);
		
		model.addAttribute("boardNo", boardVo.getBoardNo());
		model.addAttribute("btypeNo", boardVo.getBtypeNo());
				
		return "redirect:/board/boardDetail";
	}
}
