# 글 수정 페이지

***

![글 수정 페이지](https://user-images.githubusercontent.com/105349816/182625972-fcc98450-a4a3-4e5b-bfb2-1ba07542b514.JPG)

***

글 수정, 삭제는 본인만 할 수 있음
세션에 로그인 돼어있는 Id(memName)와 글을 작성한 Id(memName2)를 비교하여 버튼생성 여부 확인

![수정 삭제 버튼](https://user-images.githubusercontent.com/105349816/182631295-f9d16a3f-8c72-4a40-b8c5-3a6f24313339.JPG)

```java

<c:if test="${memName == memName2 }">
	<div>
		<a
		href="<c:url value='/board/boardUpdate?boardNo=${vo.boardNo }&bfileNo=${AtcVo.bfileNo}'/>">
			<button class="btn btn-large btn-blue btn-effect mt30"
			id="writeBoard">수정</button>
		</a>
		<form id="boardDelete"
		action="<c:url value='/board/boardDelete'/>"
		style="display: inline-block;">
			<input type="hidden" name="boardNo" value="${vo.boardNo }">
			<input type="hidden" name="btypeNo" value="${vo.btypeNo }">
			<button class="btn btn-large btn-blue btn-effect mt30"
			id="boardDeleteBt" type="button">삭제</button>
		</form>
	</div>
</c:if>

```

***

# 컨트롤러

페이지를 보여주는 컨트롤러 get방식

```java

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

```

수정버튼을 눌렀을 때 컨트롤러 post방식

```java

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

		if(boardVo.getBtypeNo()==3) {
			return "redirect:/board/qna?btypeNo=3";
		}else if(boardVo.getBtypeNo()==8) {
			return "redirect:/board/freeBoard?btypeNo=8";
		}else if(boardVo.getBtypeNo()==5) {
			return "redirect:/board/QuestionBoard?btypeNo=5";
		}else if(boardVo.getBtypeNo()==6) {
			return "redirect:/board/shareBoard?btypeNo=6";
		}else if(boardVo.getBtypeNo()==7) {
			return "redirect:/board/requestBoard?btypeNo=7";
		}
		return "/";
	}

```
